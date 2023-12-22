package com.saimone.movielistapp.features_app.presentation.movie_list_screen

import com.saimone.movielistapp.features_app.domain.util.MovieOrder

sealed class MovieListEvent {
    data class Order(val movieOrder: MovieOrder) : MovieListEvent()
}