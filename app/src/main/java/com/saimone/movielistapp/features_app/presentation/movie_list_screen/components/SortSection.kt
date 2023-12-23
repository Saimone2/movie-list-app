package com.saimone.movielistapp.features_app.presentation.movie_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListEvent
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListViewModel

@Composable
fun SortSection(
    viewModel: MovieListViewModel,
    onSortChange: (MovieOrder) -> Unit,
    modifier: Modifier = Modifier,
) {
    val titleState = viewModel.filterMovieTitle.value
    val releaseDateState = viewModel.filterMovieReleaseDate.value

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(240.dp)
                .background(Color.Transparent)
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Row(
                    modifier = Modifier.height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Descending",
                            tint = Color.Gray
                        )
                    }
                    FilterTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = titleState.text,
                        hint = titleState.hint,
                        onValueChange = {
                            viewModel.onEvent(MovieListEvent.EnteredTitle(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(MovieListEvent.ChangeTitleFocus(it))
                        },
                        isHintVisible = titleState.isHintVisible,
                        textStyle = typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        ),
                    )
                }
                Divider(thickness = 1.dp, color = Color.Gray)
                Row(
                    modifier = Modifier.height(40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Descending",
                            tint = Color.Gray
                        )
                    }
                    FilterTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = releaseDateState.text,
                        hint = releaseDateState.hint,
                        onValueChange = {
                            viewModel.onEvent(MovieListEvent.EnteredReleaseDate(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(MovieListEvent.ChangeReleaseDateFocus(it))
                        },
                        isHintVisible = releaseDateState.isHintVisible,
                        textStyle = typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        ),
                    )
                }
            }
        }
    }
}