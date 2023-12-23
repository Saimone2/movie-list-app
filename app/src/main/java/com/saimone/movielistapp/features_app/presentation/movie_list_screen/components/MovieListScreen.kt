package com.saimone.movielistapp.features_app.presentation.movie_list_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListEvent
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListViewModel
import com.saimone.movielistapp.features_app.presentation.util.Screen

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(MovieListEvent.ToggleSortSection)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                    ),
                    contentPadding = PaddingValues(horizontal = 14.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.sort),
                        style = typography.labelMedium
                    )
                }
                AnimatedVisibility(
                    visible = state.isSortSectionVisible,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically(),
                ) {
                    SortSection(
                        viewModel = viewModel,
                        onSortChange = {
                            viewModel.onEvent(MovieListEvent.Order(it))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 6.dp, end = 6.dp, bottom = 14.dp, top = 6.dp)
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    content = {
                        item {
                            Text(
                                text = stringResource(id = R.string.movies),
                                modifier = Modifier.padding(horizontal = 6.dp),
                                style = typography.titleLarge
                            )
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