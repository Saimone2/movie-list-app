package com.saimone.movielistapp.features_app.presentation.movie_detail_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saimone.movielistapp.R
import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.presentation.movie_detail_screen.MovieDetailEvent.ToggleWatchlisted
import com.saimone.movielistapp.features_app.presentation.movie_detail_screen.MovieDetailViewModel
import com.saimone.movielistapp.features_app.presentation.util.DateUtils
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uriHandler = LocalUriHandler.current

    val currentMovie: Movie = viewModel.currentMovie.value
    val imageRes: Int = viewModel.imageRes.value
    var isWatchlisted: Boolean = viewModel.isWatchlisted.value

    val buttonText = stringResource(
        if (isWatchlisted) R.string.remove_from_watchlist else R.string.add_to_watchlist
    ).uppercase()

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                navController.navigateUp()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = if(isSystemInDarkTheme()) Color.White else Color.Black
                            ),
                            contentPadding = PaddingValues(start = 4.dp, end = 12.dp),
                            modifier = Modifier
                                .height(40.dp)
                                .padding(horizontal = 12.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                modifier = Modifier.scale(1.4F),
                                contentDescription = stringResource(id = R.string.back_to_movies_list)
                            )
                            Text(
                                text = "Movies",
                                modifier = Modifier,
                                style = typography.labelMedium
                            )
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                        Divider(thickness = 0.5.dp, color = Color.Gray)
                    }
                })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Row {
                Image(
                    painterResource(id = imageRes),
                    contentDescription = currentMovie.title,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .width(130.dp)
                        .shadow(
                            elevation = 5.dp,
                            spotColor = Color.Black
                        )
                        .clip(RoundedCornerShape(4.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .width(130.dp)
                                .padding(top = 2.dp, end = 15.dp),
                            text = currentMovie.title,
                            style = typography.titleMedium
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        letterSpacing = 0.sp
                                    )
                                ) {
                                    append(currentMovie.rating.toString())
                                }
                                withStyle(
                                    style = typography.bodyMedium.toSpanStyle()
                                ) {
                                    append("/10")
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        modifier = Modifier.height(35.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if(isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                            contentColor = if(isSystemInDarkTheme()) Color.LightGray else Color.Gray
                        ),
                        contentPadding = PaddingValues(horizontal = 11.dp),
                        onClick = {
                            isWatchlisted = !isWatchlisted
                            scope.launch {
                                viewModel.onEvent(ToggleWatchlisted)
                            }
                        }
                    ) {
                        if (!isWatchlisted) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = stringResource(id = R.string.add).uppercase(),
                                modifier = Modifier.size(15.dp)
                            )
                        }
                        Text(
                            text = buttonText,
                            style = typography.titleSmall
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = if(isSystemInDarkTheme()) Color.White else Color.Black
                        ),
                        modifier = Modifier
                            .width(128.dp)
                            .height(35.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp),
                        border = BorderStroke(1.dp, if(isSystemInDarkTheme()) Color.White else Color.Black),
                        onClick = {
                            uriHandler.openUri(currentMovie.trailerLink)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.watch_trailer).uppercase(),
                            style = typography.titleSmall
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Divider(thickness = 0.5.dp, color = Color.Gray)
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.short_description),
                modifier = Modifier.padding(bottom = 10.dp),
                style = typography.titleMedium
            )

            Text(
                text = currentMovie.description,
                style = typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))
            Divider(thickness = 0.5.dp, color = Color.Gray)
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.details),
                modifier = Modifier.padding(bottom = 10.dp),
                style = typography.titleMedium
            )

            MovieTableRow(
                headerText = stringResource(id = R.string.genre),
                text = currentMovie.genre
            )

            MovieTableRow(
                headerText = stringResource(id = R.string.release_date),
                text = DateUtils.formatReleaseDate(currentMovie.releasedDate)
            )
        }
    }
}