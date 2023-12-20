package com.saimone.movielistapp.features_app.presentation.main_list

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.util.MovieOrder

data class MainListState(
    val movies: List<Movie> = emptyList(),
    val movieItemOrder: MovieOrder = MovieOrder.Title,
)