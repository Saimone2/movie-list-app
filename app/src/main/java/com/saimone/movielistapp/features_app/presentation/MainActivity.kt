package com.saimone.movielistapp.features_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.saimone.movielistapp.features_app.presentation.main_list.components.MainScreen
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
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(route = Screen.MainScreen.route) {
                        MainScreen(navController = navController)
                    }
                }
            }
        }
    }
}