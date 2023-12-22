package com.saimone.movielistapp.features_app.presentation.movie_list_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saimone.movielistapp.R
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListEvent
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListViewModel
import com.saimone.movielistapp.features_app.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End,
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(MovieListEvent.Order(MovieOrder.ReleaseDate))
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            ),
                            contentPadding = PaddingValues(horizontal = 14.dp),
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.sort),
                                style = typography.labelMedium
                            )
                        }
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(top = 52.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.movies),
                    modifier = Modifier.padding(horizontal = 6.dp),
                    style = typography.titleLarge
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    content = {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        items(state.movies) { movie ->
                            MovieItem(
                                movie = movie,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        navController.navigate(
                                            Screen.MovieDetailScreen.route + "?movieId=${movie.id}"
                                        )
                                    }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                )
            }
        }
    )
}