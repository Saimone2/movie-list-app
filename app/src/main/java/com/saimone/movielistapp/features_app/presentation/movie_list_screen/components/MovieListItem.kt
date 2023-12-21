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
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saimone.movielistapp.features_app.domain.models.Movie

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
                        .width(120.dp)
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
                        .padding(start = 15.dp, end = 8.dp, bottom = 20.dp),
                ) {
                    Text(
                        text = movie.title + " (" + movie.releasedDate.split("\\s".toRegex())
                            .lastOrNull { it.isNotEmpty() } + ")",
                        style = LocalTextStyle.current.copy(fontSize = 17.sp, fontWeight = FontWeight.Bold, lineHeight = 22.sp, letterSpacing = 0.3.sp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = movie.duration + " - " + movie.genre,
                        style = LocalTextStyle.current.copy(fontSize = 15.sp, color = Color.Gray, lineHeight = 20.sp, letterSpacing = 0.3.sp)
                    )
                    Spacer(modifier = Modifier.height(26.dp))
                    Text(
                        text = "ON MY WATCHLIST",
                        style = LocalTextStyle.current.copy(fontSize = 12.sp, color = Color.Black, fontWeight = FontWeight.SemiBold)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Divider(thickness = 0.5.dp, color = Color.Gray)
        }
    }
}
