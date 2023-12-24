package com.saimone.movielistapp.features_app.presentation.movie_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saimone.movielistapp.features_app.domain.use_cases.MovieUseCases
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import com.saimone.movielistapp.features_app.domain.util.OrderType
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

    private var getMoviesJob: Job? = null

    init {
        getMovies(MovieOrder.Title(OrderType.Ascending))
    }

    fun onEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.Order -> {
                getMovies(event.movieOrder)
            }

            is MovieListEvent.Filter -> {
                var filteredMovies = state.value.movies
                if (filterMovieTitle.value.text.isNotEmpty() && filterMovieReleaseDate.value.text.isNotEmpty()) {
                    filteredMovies = state.value.movies.filter { movie ->
                        movie.title.contains(
                            filterMovieTitle.value.text,
                            ignoreCase = true
                        ) && movie.releasedDate.contains(
                            filterMovieReleaseDate.value.text,
                            ignoreCase = true
                        )
                    }
                } else if (filterMovieTitle.value.text.isNotEmpty()) {
                    filteredMovies = state.value.movies.filter { movie ->
                        movie.title.contains(
                            filterMovieTitle.value.text,
                            ignoreCase = true
                        )
                    }
                } else if (filterMovieReleaseDate.value.text.isNotEmpty()) {
                    filteredMovies = state.value.movies.filter { movie ->
                        movie.releasedDate.contains(
                            filterMovieReleaseDate.value.text,
                            ignoreCase = true
                        )
                    }
                }
                _state.value = state.value.copy(
                    filteredMovies = filteredMovies
                )
            }

            is MovieListEvent.ToggleSortSection -> {
                _state.value = state.value.copy(
                    isSortSectionVisible = !state.value.isSortSectionVisible
                )
            }

            is MovieListEvent.ChangeReleaseDateFocus -> {
                _filterMovieReleaseDate.value = filterMovieReleaseDate.value.copy(
                    isHintVisible = !event.focusState.isFocused && filterMovieReleaseDate.value.text.isBlank()
                )
            }

            is MovieListEvent.ChangeTitleFocus -> {
                _filterMovieTitle.value = _filterMovieTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && filterMovieTitle.value.text.isBlank()
                )
            }

            is MovieListEvent.EnteredReleaseDate -> {
                _filterMovieReleaseDate.value = filterMovieReleaseDate.value.copy(
                    text = event.value
                )
            }

            is MovieListEvent.EnteredTitle -> {
                _filterMovieTitle.value = filterMovieTitle.value.copy(
                    text = event.value
                )
            }

            is MovieListEvent.ClearFilterSortState -> {
                getMovies(MovieOrder.Title(OrderType.Ascending))
                _state.value = state.value.copy(
                    isSortSectionVisible = !state.value.isSortSectionVisible
                )
                _filterMovieTitle.value = filterMovieTitle.value.copy(
                    text = ""
                )
                _filterMovieReleaseDate.value = filterMovieReleaseDate.value.copy(
                    text = ""
                )
            }
        }
    }

    private fun getMovies(movieItemOrder: MovieOrder) {
        getMoviesJob?.cancel()
        getMoviesJob = movieUseCases.getMoviesList(movieItemOrder)
            .onEach { movies ->
                _state.value = state.value.copy(
                    movies = movies,
                    filteredMovies = movies,
                    movieItemOrder = movieItemOrder
                )
            }
            .launchIn(viewModelScope)
    }
}