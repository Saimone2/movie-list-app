package com.saimone.movielistapp.features_app.presentation.movie_detail_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.use_cases.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _currentMovie = mutableStateOf(Movie())
    val currentMovie: MutableState<Movie> = _currentMovie

    init {
        savedStateHandle.get<Int>("movieId")?.let { movieId ->
            if (movieId != -1) {
                viewModelScope.launch {
                    movieUseCases.getMovieById(movieId)?.also { movie ->
                        _currentMovie.value = movie
                    }
                }
            }
        }
    }
}