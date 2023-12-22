package com.saimone.movielistapp.features_app.domain.use_cases

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository

class ToggleWatchlisted(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: Movie) {
        repository.insertMovie(movie.copy(isWatchlisted = !movie.isWatchlisted))
    }
}