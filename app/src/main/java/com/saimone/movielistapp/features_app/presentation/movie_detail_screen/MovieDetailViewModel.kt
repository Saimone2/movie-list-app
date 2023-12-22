package com.saimone.movielistapp.features_app.presentation.movie_detail_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saimone.movielistapp.R
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

    private val _isWatchlisted = mutableStateOf(false)
    val isWatchlisted: State<Boolean> = _isWatchlisted

    private val _imageRes = mutableIntStateOf(R.drawable.ic_launcher_background)
    var imageRes: State<Int> = _imageRes

    private val _currentMovie = mutableStateOf(Movie())
    val currentMovie: MutableState<Movie> = _currentMovie

    init {
        savedStateHandle.get<Int>("movieId")?.let { movieId ->
            if (movieId != -1) {
                viewModelScope.launch {
                    movieUseCases.getMovieById(movieId)?.also { movie ->
                        _currentMovie.value = movie
                        _imageRes.intValue = movie.imageRes
                        _isWatchlisted.value = movie.isWatchlisted
                    }
                }
            }
        }
    }

    suspend fun onEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.ToggleWatchlisted -> {
                movieUseCases.toggleWatchlisted(currentMovie.value)

                currentMovie.value.id?.let {
                    movieUseCases.getMovieById(it)?.also { movie ->
                        _currentMovie.value = movie
                        _isWatchlisted.value = currentMovie.value.isWatchlisted
                    }
                }
            }
        }
    }
}