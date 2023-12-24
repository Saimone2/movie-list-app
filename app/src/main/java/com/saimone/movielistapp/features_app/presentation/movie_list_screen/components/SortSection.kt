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
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saimone.movielistapp.R
import com.saimone.movielistapp.features_app.domain.util.MovieOrder
import com.saimone.movielistapp.features_app.domain.util.OrderType
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListEvent
import com.saimone.movielistapp.features_app.presentation.movie_list_screen.MovieListViewModel

@Composable
fun SortSection(
    viewModel: MovieListViewModel,
    onSortChange: (MovieOrder) -> Unit,
    modifier: Modifier = Modifier
) {
    val titleState = viewModel.filterMovieTitle.value
    val releaseDateState = viewModel.filterMovieReleaseDate.value

    val sortByTitle = remember { mutableStateOf(true) }
    val sortAscending = remember { mutableStateOf(true) }

    var movieOrder: MovieOrder

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
                            if (!sortByTitle.value) {
                                sortAscending.value = true
                                movieOrder = MovieOrder.Title(OrderType.Ascending)
                                onSortChange(movieOrder)
                            } else {
                                movieOrder = if (sortAscending.value) {
                                    MovieOrder.Title(OrderType.Descending)
                                } else {
                                    MovieOrder.Title(OrderType.Ascending)
                                }
                                onSortChange(movieOrder)
                                sortAscending.value = !sortAscending.value
                            }
                            sortByTitle.value = true
                        }
                    ) {
                        Icon(
                            imageVector = if (sortByTitle.value && sortAscending.value) {
                                Icons.Default.KeyboardArrowDown
                            } else {
                                Icons.Default.KeyboardArrowUp
                            },
                            contentDescription = stringResource(id = R.string.sort),
                            tint = if (sortByTitle.value) Color.Gray else Color.Transparent
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
                            if (sortByTitle.value) {
                                sortAscending.value = true
                                movieOrder = MovieOrder.ReleaseDate(OrderType.Ascending)
                                onSortChange(movieOrder)
                            } else {
                                movieOrder = if (sortAscending.value) {
                                    MovieOrder.ReleaseDate(OrderType.Descending)
                                } else {
                                    MovieOrder.ReleaseDate(OrderType.Ascending)
                                }
                                onSortChange(movieOrder)
                                sortAscending.value = !sortAscending.value
                            }
                            sortByTitle.value = false
                        }
                    ) {
                        Icon(
                            imageVector = if (!sortByTitle.value && sortAscending.value) {
                                Icons.Default.KeyboardArrowDown
                            } else {
                                Icons.Default.KeyboardArrowUp
                            },
                            contentDescription = stringResource(id = R.string.sort),
                            tint = if (!sortByTitle.value) Color.Gray else Color.Transparent
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