package com.saimone.movielistapp.features_app.presentation.movie_list_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean
) {
    val titleState = viewModel.filterMovieTitle.value
    val releaseDateState = viewModel.filterMovieReleaseDate.value

    val movieItemOrder = viewModel.state.value.movieItemOrder
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
                    modifier = Modifier.height(45.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (movieItemOrder is MovieOrder.ReleaseDate) {
                                onSortChange(MovieOrder.Title(OrderType.Ascending))
                            } else {
                                movieOrder = if (movieItemOrder.orderType is OrderType.Ascending) {
                                    MovieOrder.Title(OrderType.Descending)
                                } else {
                                    MovieOrder.Title(OrderType.Ascending)
                                }
                                onSortChange(movieOrder)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (movieItemOrder is MovieOrder.Title && movieItemOrder.orderType is OrderType.Ascending) {
                                Icons.Default.KeyboardArrowDown
                            } else {
                                Icons.Default.KeyboardArrowUp
                            },
                            contentDescription = stringResource(id = R.string.sort),
                            tint = if (movieItemOrder is MovieOrder.Title) {
                                if (isDarkTheme) {
                                    Color.White
                                } else {
                                    Color.DarkGray
                                }
                            } else {
                                Color.Transparent
                            }
                        )
                    }
                    FilterTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = titleState.text,
                        hint = titleState.hint,
                        onValueChange = {
                            viewModel.onEvent(MovieListEvent.EnteredTitle(it))
                            viewModel.onEvent(MovieListEvent.Filter(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(MovieListEvent.ChangeTitleFocus(it))
                        },
                        isHintVisible = titleState.isHintVisible,
                        textStyle = typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = if (isDarkTheme) Color.White else Color.DarkGray
                        )
                    )
                }
                Divider(thickness = 1.dp, color = Color.Gray)
                Row(
                    modifier = Modifier.height(45.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (movieItemOrder is MovieOrder.Title) {
                                onSortChange(MovieOrder.ReleaseDate(OrderType.Ascending))
                            } else {
                                movieOrder = if (movieItemOrder.orderType is OrderType.Ascending) {
                                    MovieOrder.ReleaseDate(OrderType.Descending)
                                } else {
                                    MovieOrder.ReleaseDate(OrderType.Ascending)
                                }
                                onSortChange(movieOrder)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (movieItemOrder is MovieOrder.ReleaseDate && movieItemOrder.orderType is OrderType.Ascending) {
                                Icons.Default.KeyboardArrowDown
                            } else {
                                Icons.Default.KeyboardArrowUp
                            },
                            contentDescription = stringResource(id = R.string.sort),
                            tint = if (movieItemOrder is MovieOrder.ReleaseDate) {
                                if (isDarkTheme) {
                                    Color.White
                                } else {
                                    Color.DarkGray
                                }
                            } else {
                                Color.Transparent
                            }
                        )
                    }
                    FilterTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = releaseDateState.text,
                        hint = releaseDateState.hint,
                        onValueChange = {
                            viewModel.onEvent(MovieListEvent.EnteredReleaseDate(it))
                            viewModel.onEvent(MovieListEvent.Filter(it))
                        },
                        onFocusChange = {
                            viewModel.onEvent(MovieListEvent.ChangeReleaseDateFocus(it))
                        },
                        isHintVisible = releaseDateState.isHintVisible,
                        textStyle = typography.bodyMedium.copy(
                            fontSize = 16.sp,
                            color = if (isDarkTheme) Color.White else Color.DarkGray
                        )
                    )
                }
            }
        }
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = {
                viewModel.onEvent(MovieListEvent.ClearFilterSortState)
            },
            modifier = Modifier
                .padding(top = 10.dp)
                .width(240.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp))
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                style = typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    color = if (isDarkTheme) Color.White else Color.Black
                )
            )
        }
    }
}