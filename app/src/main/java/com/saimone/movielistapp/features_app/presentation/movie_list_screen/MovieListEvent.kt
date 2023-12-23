package com.saimone.movielistapp.features_app.presentation.movie_list_screen

import androidx.compose.ui.focus.FocusState
import com.saimone.movielistapp.features_app.domain.util.MovieOrder

sealed class MovieListEvent {
    data class Order(val movieOrder: MovieOrder) : MovieListEvent()
    data object ToggleSortSection : MovieListEvent()
    data class EnteredTitle(val value: String) :  MovieListEvent()
    data class ChangeTitleFocus(val focusState: FocusState) :  MovieListEvent()
    data class EnteredReleaseDate(val value: String) :  MovieListEvent()
    data class ChangeReleaseDateFocus(val focusState: FocusState) :  MovieListEvent()
}