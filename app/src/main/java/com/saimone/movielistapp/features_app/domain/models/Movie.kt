package com.saimone.movielistapp.features_app.domain.models

data class Movie(
    val title: String,
    val description: String,
    val rating: Double,
    val duration: String,
    val genre: String,
    val releasedDate: String,
    val trailerLink: String
)
