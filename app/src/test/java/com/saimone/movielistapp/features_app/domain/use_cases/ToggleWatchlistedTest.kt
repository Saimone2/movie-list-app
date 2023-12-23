package com.saimone.movielistapp.features_app.domain.use_cases

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.mockito.Mockito

class ToggleWatchlistedTest {
    private val movieRepository = Mockito.mock<MovieRepository>()

    @AfterEach
    fun tearDown() = Mockito.reset(movieRepository)

    @Test
    fun `should toggle the watchlisted status of the movie`() {
        runTest {
            val movie = Movie(id = 1, isWatchlisted = false)
            val updatedMovie = movie.copy(isWatchlisted = true)
            Mockito.`when`(movieRepository.getMovieById(1)).thenReturn(movie)

            val useCase = ToggleWatchlisted(repository = movieRepository)

            useCase.invoke(movie)
            Mockito.verify(movieRepository).insertMovie(updatedMovie)
        }
    }
}