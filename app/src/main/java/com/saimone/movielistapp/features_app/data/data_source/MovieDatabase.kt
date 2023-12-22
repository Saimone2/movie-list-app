package com.saimone.movielistapp.features_app.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.saimone.movielistapp.R
import com.saimone.movielistapp.features_app.domain.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Movie::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null

        private const val DATABASE_NAME = "movies_db"

        fun getInstance(context: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        val initialMovies = listOf(
            Movie(
                title = "Tenet",
                description = "Armed with only one word, Tenet, and fighting for the survival of the entire world, a " +
                        "Protagonist journeys through a twilight world of international espionage on a mission that will " +
                        "unfold in something beyond real time.",
                rating = 7.8,
                duration = "2h 30 min",
                genre = "Action, Sci-Fi",
                releasedDate = "3 September 2020",
                trailerLink = "https://www.youtube.com/watch?v=LdOM0x0XDMo",
                imageRes = R.drawable.tenet
            ),
            Movie(
                title = "Spider-Man: Into the Spider-Verse",
                description = "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five " +
                        "spider-powered individuals from other dimensions to stop a threat for all realities.",
                rating = 8.4,
                duration = "1h 57min",
                genre = " Action, Animation, Adventure",
                releasedDate = "14 December 2018",
                trailerLink = "https://www.youtube.com/watch?v=tg52up16eq0",
                imageRes = R.drawable.spider_man
            ),
            Movie(
                title = "Knives Out",
                description = "A detective investigates the death of a patriarch of an eccentric, combative family.",
                rating = 7.9,
                duration = "2h 10min",
                genre = "Comedy, Crime, Drama",
                releasedDate = "27 November 2019",
                trailerLink = "https://www.youtube.com/watch?v=qGqiHJTsRkQ",
                imageRes = R.drawable.knives_out
            ),
            Movie(
                title = "Guardians of the Galaxy",
                description = "A group of intergalactic criminals must pull together to stop a fanatical warrior with " +
                        "plans to purge the universe.",
                rating = 8.0,
                duration = "2h 1min",
                genre = "Action, Adventure, Comedy",
                releasedDate = "1 August 2014",
                trailerLink = "https://www.youtube.com/watch?v=d96cjJhvlMA",
                imageRes = R.drawable.guardians_of_the_galaxy
            ),
            Movie(
                title = "Avengers: Age of Ultron",
                description = "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping " +
                        "program called Ultron, things go horribly wrong and it's up to Earth's mightiest heroes to stop the " +
                        "villainous Ultron from enacting his terrible plan.",
                rating = 7.3,
                duration = "2h 21min",
                genre = "Action, Adventure, Sci-Fi",
                releasedDate = "1 May 2015",
                trailerLink = "https://www.youtube.com/watch?v=tmeOjFno6Do",
                imageRes = R.drawable.avengers
            ),
        )

        private fun buildDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    CoroutineScope(Dispatchers.IO).launch {
                        instance?.let {
                            for (movie in initialMovies) {
                                it.movieDao.insertMovie(movie)
                            }
                        }
                    }
                }
            }).build()
        }
    }
}