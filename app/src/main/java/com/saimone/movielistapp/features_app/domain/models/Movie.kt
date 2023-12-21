package com.saimone.movielistapp.features_app.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val rating: Double,
    val duration: String,
    val genre: String,
    val releasedDate: String,
    val trailerLink: String,
    val imageRes: Int
) {
    constructor() : this(
        id = null,
        title = "",
        description = "",
        rating = 0.0,
        duration = "",
        genre = "",
        releasedDate = "",
        trailerLink = "",
        imageRes = 0
    )
}