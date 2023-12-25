package com.saimone.movielistapp.features_app.domain.use_cases

import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import com.saimone.movielistapp.features_app.domain.util.OrderType
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito

class GetMoviesListTest {
    private val movieRepository = Mockito.mock<MovieRepository>()

    @AfterEach
    fun tearDown() = Mockito.reset(movieRepository)

    @Test
    fun `should return movies sorted by title in ascending`() = runTest {
        val orderType = OrderType.Ascending

        val movies = listOf(
            Movie(title = "B", releasedDate = "3 September 2020"),
            Movie(title = "A", releasedDate = "1 May 2015"),
            Movie(title = "C", releasedDate = "1 August 2014")
        )
        Mockito.`when`(movieRepository.getMoviesList()).thenReturn(flowOf(movies))

        val useCase = GetMoviesList(movieRepository)
        val result = useCase.invoke(MovieOrder.Title(orderType)).toList().flatten()

        val expected = listOf(
            Movie(title = "A", releasedDate = "1 May 2015"),
            Movie(title = "B", releasedDate = "3 September 2020"),
            Movie(title = "C", releasedDate = "1 August 2014")
        )
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `should return movies sorted by title in descending`() = runTest {
        val orderType = OrderType.Descending

        val movies = listOf(
            Movie(title = "B", releasedDate = "3 September 2020"),
            Movie(title = "A", releasedDate = "1 May 2015"),
            Movie(title = "C", releasedDate = "1 August 2014")
        )
        Mockito.`when`(movieRepository.getMoviesList()).thenReturn(flowOf(movies))

        val useCase = GetMoviesList(movieRepository)
        val result = useCase.invoke(MovieOrder.Title(orderType)).toList().flatten()

        val expected = listOf(
            Movie(title = "C", releasedDate = "1 August 2014"),
            Movie(title = "B", releasedDate = "3 September 2020"),
            Movie(title = "A", releasedDate = "1 May 2015")
        )
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `should return movies sorted by release date in ascending`() = runTest {
        val orderType = OrderType.Ascending

        val movies = listOf(
            Movie(title = "B", releasedDate = "3 September 2020"),
            Movie(title = "A", releasedDate = "1 May 2015"),
            Movie(title = "C", releasedDate = "1 August 2014")
        )
        Mockito.`when`(movieRepository.getMoviesList()).thenReturn(flowOf(movies))

        val useCase = GetMoviesList(movieRepository)
        val result = useCase.invoke(MovieOrder.ReleaseDate(orderType)).toList().flatten()

        val expected = listOf(
            Movie(title = "C", releasedDate = "1 August 2014"),
            Movie(title = "A", releasedDate = "1 May 2015"),
            Movie(title = "B", releasedDate = "3 September 2020")
        )
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `should return movies sorted by release date in descending`() = runTest {
        val orderType = OrderType.Descending

        val movies = listOf(
            Movie(title = "B", releasedDate = "3 September 2020"),
            Movie(title = "A", releasedDate = "1 May 2015"),
            Movie(title = "C", releasedDate = "1 August 2014")
        )
        Mockito.`when`(movieRepository.getMoviesList()).thenReturn(flowOf(movies))

        val useCase = GetMoviesList(movieRepository)
        val result = useCase.invoke(MovieOrder.ReleaseDate(orderType)).toList().flatten()

        val expected = listOf(
            Movie(title = "B", releasedDate = "3 September 2020"),
            Movie(title = "A", releasedDate = "1 May 2015"),
            Movie(title = "C", releasedDate = "1 August 2014")
        )
        Assertions.assertEquals(expected, result)
    }
}