package com.saimone.movielistapp.features_app.presentation.movie_detail_screen

sealed class MovieDetailEvent {
    data object ToggleWatchlisted : MovieDetailEvent()
}