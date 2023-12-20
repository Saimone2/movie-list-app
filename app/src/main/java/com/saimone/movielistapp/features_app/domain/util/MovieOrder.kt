package com.saimone.movielistapp.features_app.domain.util

sealed class MovieOrder {
    data object Title : MovieOrder()
    data object ReleaseDate : MovieOrder()
}