package com.saimone.movielistapp.features_app.presentation.movie_list_screen

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import com.saimone.movielistapp.features_app.domain.util.OrderType

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val filteredMovies: List<Movie> = emptyList(),
    val movieItemOrder: MovieOrder = MovieOrder.Title(OrderType.Ascending),
    val isSortSectionVisible: Boolean = false
)