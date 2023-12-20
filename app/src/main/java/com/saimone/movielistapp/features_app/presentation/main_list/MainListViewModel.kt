package com.saimone.movielistapp.features_app.presentation.main_list

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
class MainListViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MainListState())
    val state: State<MainListState> = _state

    private var getNotesJob: Job? = null

    init {
        getMovies(MovieOrder.Title)
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