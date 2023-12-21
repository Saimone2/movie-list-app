package com.saimone.movielistapp.features_app.presentation.movie_list_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListViewModel
import com.saimone.movielistapp.features_app.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(40.dp),
                title = { Text(text = "") },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        IconButton(
                            onClick = {

                            }
                        ) {
                            Text(
                                text = "Sort",
                                style = LocalTextStyle.current.copy(
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                Text(
                    text = "Movies",
                    modifier = Modifier.padding(start = 6.dp, bottom = 4.dp),
                    style = LocalTextStyle.current.copy(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.sp
                    )
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    content = {
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        items(state.movies) { movie ->
                            MovieItem(
                                movie = movie,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
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