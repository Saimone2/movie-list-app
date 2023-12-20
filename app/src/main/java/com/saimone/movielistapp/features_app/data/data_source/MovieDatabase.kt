package com.saimone.movielistapp.features_app.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saimone.movielistapp.features_app.domain.models.Movie

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}