package com.saimone.movielistapp.features_app.domain.use_cases

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GetMovieByIdTest {
    private val movieRepository = mock<MovieRepository>()

    @AfterEach
    fun tearDown() = Mockito.reset(movieRepository)

    @Test
    fun `should return the same movie as repository`() {
        runTest {
            val testMovie = Movie(id = 1)
            Mockito.`when`(movieRepository.getMovieById(1)).thenReturn(testMovie)

            val useCases = GetMovieById(repository = movieRepository)
            val actual = useCases.invoke(1)
            val expected = Movie(id = 1)

            Assertions.assertEquals(expected, actual)
        }
    }

    @Test
    fun `should return null if movie is not found`() {
        runTest {
            Mockito.`when`(movieRepository.getMovieById(1)).thenReturn(null)

            val useCase = GetMovieById(repository = movieRepository)
            val actual = useCase.invoke(1)

            Assertions.assertNull(actual)
        }
    }
}