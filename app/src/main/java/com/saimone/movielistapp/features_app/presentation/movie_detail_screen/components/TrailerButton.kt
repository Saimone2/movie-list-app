package com.saimone.movielistapp.features_app.presentation.movie_detail_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saimone.movielistapp.R
import com.saimone.movielistapp.features_app.domain.models.Movie

@Composable
fun TrailerButton(
    currentMovie: Movie,
    uriHandler: UriHandler
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
        ),
        modifier = Modifier
            .width(128.dp)
            .height(35.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        border = BorderStroke(1.dp, if (isSystemInDarkTheme()) Color.White else Color.Black),
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