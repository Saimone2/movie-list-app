package com.saimone.movielistapp.features_app.data.repository

import com.saimone.movielistapp.features_app.data.data_source.MovieDao
import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieDao: MovieDao
) : MovieRepository {
    override fun getMoviesList(): Flow<List<Movie>> {
        return movieDao.getMoviesList()
    }

    override suspend fun getMovieById(id: Int): Movie? {
        return movieDao.getMovieById(id)
    }

    override suspend fun insertMovie(movie: Movie) {
        return movieDao.insertMovie(movie)
    }
}