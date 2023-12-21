package com.saimone.movielistapp.features_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saimone.movielistapp.features_app.presentation.movie_detail_screen.components.MovieDetailScreen
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.components.MovieListScreen
import com.saimone.movielistapp.features_app.presentation.util.Screen
import com.saimone.movielistapp.ui.theme.MovieListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MovieListScreen.route
                ) {
                    composable(route = Screen.MovieListScreen.route) {
                        MovieListScreen(navController = navController)
                    }
                    composable(
                        route = Screen.MovieDetailScreen.route +
                                "?movieId={movieId}",
                        arguments = listOf(
                            navArgument(
                                name = "movieId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        MovieDetailScreen(navController = navController)
                    }
                }
            }
        }
    }
}