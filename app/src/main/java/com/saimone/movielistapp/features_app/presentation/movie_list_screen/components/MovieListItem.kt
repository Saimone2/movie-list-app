package com.saimone.movielistapp.features_app.presentation.movie_list_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saimone.movielistapp.R
import com.saimone.movielistapp.features_app.domain.models.Movie
import com.saimone.movielistapp.features_app.presentation.util.DateUtils

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier
) {
    Box(modifier) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = movie.imageRes),
                    contentDescription = movie.title,
                    modifier = Modifier
                        .width(110.dp)
                        .padding(horizontal = 6.dp)
                        .shadow(
                            elevation = 5.dp,
                            spotColor = Color.Black
                        )
                        .clip(RoundedCornerShape(4.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp, end = 8.dp, bottom = 16.dp),
                ) {
                    Text(
                        text = "${movie.title} (${DateUtils.getYear(movie.releasedDate)})",
                        style = typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${movie.duration} - ${movie.genre}",
                        style = typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(22.dp))
                    if (movie.isWatchlisted) {
                        Text(
                            text = stringResource(id = R.string.on_my_watchlist).uppercase(),
                            style = typography.labelSmall
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Divider(thickness = 0.5.dp, color = Color.Gray)
        }
    }
}