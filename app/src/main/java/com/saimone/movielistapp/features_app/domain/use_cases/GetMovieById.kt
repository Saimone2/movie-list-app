package com.saimone.movielistapp.features_app.domain.use_cases

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository

class GetMovieById(
    private val repository: MovieRepository
) {
    fun execute(id: Int): Movie? {
        return repository.getMovieById(id)
    }
}