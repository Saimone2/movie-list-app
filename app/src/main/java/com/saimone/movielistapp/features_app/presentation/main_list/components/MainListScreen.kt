package com.saimone.movielistapp.features_app.presentation.main_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saimone.movielistapp.features_app.presentation.main_list.MainListViewModel
import com.saimone.movielistapp.features_app.presentation.util.Screen

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        content = {
            items(state.movies) { movie ->
                MovieItem(
                    movie = movie,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(
                                Screen.DetailScreen.route + "?movieId=${movie.id}"
                            )
                        }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}