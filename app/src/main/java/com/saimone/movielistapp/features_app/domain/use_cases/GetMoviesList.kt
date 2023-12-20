package com.saimone.movielistapp.features_app.domain.use_cases

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMoviesList(
    private val repository: MovieRepository
) {
    fun execute(movieOrder: MovieOrder = MovieOrder.Title): Flow<List<Movie>> {
        return repository.getMoviesList().map { movies ->
            when (movieOrder) {
                is MovieOrder.Title -> {
                    movies.sortedBy { it.title.lowercase() }
                }

                is MovieOrder.ReleaseDate -> {
                    movies.sortedBy { it.releasedDate.lowercase() }
                }
            }
        }
    }
}