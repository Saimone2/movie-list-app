package com.saimone.movielistapp.features_app.presentation.util

sealed class Screen(val route: String) {
    data object MovieListScreen : Screen("movies")
    data object MovieDetailScreen : Screen("movies_detail")
}