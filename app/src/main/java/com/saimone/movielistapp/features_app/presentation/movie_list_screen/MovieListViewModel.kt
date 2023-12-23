package com.saimone.movielistapp.features_app.presentation.movie_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saimone.movielistapp.features_app.domain.use_cases.MovieUseCases
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    private val _filterMovieTitle = mutableStateOf(
        MovieTextFieldState(
            hint = "Title"
        )
    )
    val filterMovieTitle: State<MovieTextFieldState> = _filterMovieTitle

    private val _filterMovieReleaseDate = mutableStateOf(
        MovieTextFieldState(
            hint = "Release date"
        )
    )
    val filterMovieReleaseDate: State<MovieTextFieldState> = _filterMovieReleaseDate

    private var getNotesJob: Job? = null

    init {
        getMovies(MovieOrder.Title)
    }

    fun onEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.Order -> {
                if (state.value.movieItemOrder::class == event.movieOrder::class) {
                    return
                }
                getMovies(event.movieOrder)
            }

            is MovieListEvent.ToggleSortSection -> {
                _state.value = state.value.copy(
                    isSortSectionVisible = !state.value.isSortSectionVisible
                )
            }

            is MovieListEvent.ChangeReleaseDateFocus -> {
                _filterMovieReleaseDate.value = _filterMovieReleaseDate.value.copy(
                    isHintVisible = !event.focusState.isFocused && _filterMovieReleaseDate.value.text.isBlank()
                )
            }

            is MovieListEvent.ChangeTitleFocus -> {
                _filterMovieTitle.value = _filterMovieTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && _filterMovieTitle.value.text.isBlank()
                )
            }

            is MovieListEvent.EnteredReleaseDate -> {
                _filterMovieReleaseDate.value = _filterMovieReleaseDate.value.copy(
                    text = event.value
                )
            }

            is MovieListEvent.EnteredTitle -> {
                _filterMovieTitle.value = _filterMovieTitle.value.copy(
                    text = event.value
                )
            }
        }
    }

    private fun getMovies(movieItemOrder: MovieOrder) {
        getNotesJob?.cancel()
        getNotesJob = movieUseCases.getMoviesList(movieItemOrder)
            .onEach { movies ->
                _state.value = state.value.copy(
                    movies = movies,
                    movieItemOrder = movieItemOrder
                )
            }
            .launchIn(viewModelScope)
    }
}