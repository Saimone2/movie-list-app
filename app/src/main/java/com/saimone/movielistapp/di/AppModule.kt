package com.saimone.movielistapp.di

import android.app.Application
import com.saimone.movielistapp.features_app.data.data_source.MovieDatabase
import com.saimone.movielistapp.features_app.data.repository.MovieRepositoryImpl
import com.saimone.movielistapp.features_app.domain.repository.MovieRepository
import com.saimone.movielistapp.features_app.domain.use_cases.GetMovieById
import com.saimone.movielistapp.features_app.domain.use_cases.GetMoviesList
import com.saimone.movielistapp.features_app.domain.use_cases.MovieUseCases
import com.saimone.movielistapp.features_app.domain.use_cases.ToggleWatchlisted
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return MovieDatabase.getInstance(app)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(db: MovieDatabase): MovieRepository {
        return MovieRepositoryImpl(db.movieDao)
    }

    @Singleton
    @Provides
    fun provideMovieUseCases(repository: MovieRepository): MovieUseCases {
        return MovieUseCases(
            getMoviesList = GetMoviesList(repository),
            getMovieById = GetMovieById(repository),
            toggleWatchlisted = ToggleWatchlisted(repository)
        )
    }
}