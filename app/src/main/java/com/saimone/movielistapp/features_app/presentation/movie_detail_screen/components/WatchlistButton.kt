package com.saimone.movielistapp.features_app.presentation.movie_detail_screen.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.saimone.movielistapp.R

@Composable
fun WatchlistButton(
    isWatchlisted: Boolean,
    buttonText: String,
    onClick: () -> Unit,
    isDarkTheme: Boolean
) {
    Button(
        modifier = Modifier.height(35.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isDarkTheme) Color.DarkGray else Color.LightGray,
            contentColor = if (isDarkTheme) Color.LightGray else Color.Gray
        ),
        contentPadding = PaddingValues(horizontal = 11.dp),
        onClick = onClick
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
}