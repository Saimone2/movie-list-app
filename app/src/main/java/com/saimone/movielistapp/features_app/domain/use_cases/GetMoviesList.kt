package com.saimone.movielistapp.features_app.domain.use_cases

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import com.saimone.movielistapp.features_app.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMoviesList(
    private val repository: MovieRepository
) {
    operator fun invoke(
        movieOrder: MovieOrder = MovieOrder.Title(OrderType.Descending)
    ): Flow<List<Movie>> {
        return repository.getMoviesList().map { movies ->
            when (movieOrder.orderType) {
                is OrderType.Ascending -> {
                    when (movieOrder) {
                        is MovieOrder.Title -> movies.sortedBy { it.title.lowercase() }
                        is MovieOrder.ReleaseDate -> movies.sortedByDescending { it.releasedDate.lowercase() }
                    }
                }

                is OrderType.Descending -> {
                    when (movieOrder) {
                        is MovieOrder.Title -> movies.sortedByDescending { it.title.lowercase() }
                        is MovieOrder.ReleaseDate -> movies.sortedBy { it.releasedDate.lowercase() }
                    }
                }
            }
        }
    }
}