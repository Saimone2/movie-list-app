package com.saimone.movielistapp.features_app.presentation.movie_detail_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.saimone.movielistapp.R
import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.presentation.movie_detail_screen.MovieDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val currentMovie: Movie = viewModel.currentMovie.value
    val uriHandler = LocalUriHandler.current

    var isWatchlisted by remember { mutableStateOf(false) }
    val buttonText = if (isWatchlisted) "REMOVE FROM WATCHLIST" else "ADD TO WATCHLIST"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                actions = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable { navController.navigateUp() }
                                .padding(vertical = 8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                modifier = Modifier.scale(1.6F),
                                tint = Color.Black,
                                contentDescription = "Back to movies list"
                            )
                            Text(
                                text = "Movies",
                                style = LocalTextStyle.current.copy(
                                    fontSize = 17.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                        Divider(thickness = 0.5.dp, color = Color.Gray)
                    }
                })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                Row {
                    Image(
                        painterResource(id = R.drawable.tenet),
                        contentDescription = currentMovie.title,
                        modifier = Modifier
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
                                .padding(top = 7.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .width(130.dp)
                                    .padding(end = 15.dp),
                                text = currentMovie.title,
                                style = LocalTextStyle.current.copy(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 22.sp,
                                    letterSpacing = 0.3.sp
                                )
                            )

                            val text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 19.sp,
                                        letterSpacing = 0.sp
                                    )
                                ) {
                                    append(currentMovie.rating.toString())
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 13.sp,
                                        color = Color.Gray,
                                        letterSpacing = 0.sp
                                    )
                                ) {
                                    append("/10")
                                }
                            }
                            Text(text = text)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.Gray
                            ),
                            contentPadding = PaddingValues(horizontal = 10.dp),
                            onClick = {
                                isWatchlisted = !isWatchlisted
                            }
                        ) {
                            if (!isWatchlisted) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "ADD",
                                    modifier = Modifier.size(15.dp),
                                    tint = Color.Gray
                                )
                            }
                            Text(
                                text = buttonText,
                                style = LocalTextStyle.current.copy(
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier.width(120.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp),
                            border = BorderStroke(1.dp, Color.Black),
                            onClick = {
                                uriHandler.openUri(currentMovie.trailerLink)
                            }
                        ) {
                            Text(
                                text = "WATCH TRAILER",
                                style = LocalTextStyle.current.copy(
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                Divider(thickness = 0.5.dp, color = Color.Gray)
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Short description",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = currentMovie.description,
                    style = LocalTextStyle.current.copy(
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                Divider(thickness = 0.5.dp, color = Color.Gray)
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Details",
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = LocalTextStyle.current.copy(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    Modifier.width(300.dp)
                ) {
                    TableCell(
                        text = "Genre",
                        weight = .3f,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Medium,
                        fontColor = Color.Black
                    )
                    TableCell(
                        text = currentMovie.genre,
                        weight = .5f,
                        textAlign = TextAlign.Start
                    )
                }
                Row(
                    Modifier.width(300.dp)
                ) {
                    TableCell(
                        text = "Release date",
                        weight = .3f,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Medium,
                        fontColor = Color.Black
                    )
                    TableCell(
                        text = currentMovie.releasedDate,
                        weight = .5f,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    )
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    textAlign: TextAlign,
    fontWeight: FontWeight = FontWeight.Normal,
    fontColor: Color = Color.Gray
) {
    Text(
        text = text,
        Modifier
            .padding(end = 3.dp)
            .weight(weight)
            .padding(8.dp),
        textAlign = textAlign,
        style = LocalTextStyle.current.copy(
            fontSize = 15.sp,
            color = fontColor,
            letterSpacing = 0.sp,
            fontWeight = fontWeight
        )
    )
}