package com.saimone.movielistapp.features_app.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey val id: Int? = null,
    val title: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val duration: String= "",
    val genre: String = "",
    val releasedDate: String = "",
    val trailerLink: String = "",
    val imageRes: Int = 0,
    val isWatchlisted: Boolean = false
)