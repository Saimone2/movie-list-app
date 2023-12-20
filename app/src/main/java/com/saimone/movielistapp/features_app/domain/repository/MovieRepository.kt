package com.saimone.movielistapp.features_app.domain.repository

import com.saimone.movielistapp.features_app.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMoviesList(): Flow<List<Movie>>
    suspend fun getMovieById(id: Int): Movie?
}