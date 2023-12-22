package com.saimone.movielistapp.features_app.presentation.movie_detail_screen.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MovieTableRow(headerText: String, text: String) {
    Row(
        Modifier.width(300.dp)
    ) {
        MovieTableCell(
            text = headerText,
            weight = .2f,
            textAlign = TextAlign.End,
            style = typography.bodyMedium.copy(fontWeight = FontWeight.Medium, color = if(isSystemInDarkTheme()) Color.White else Color.Black),
        )
        MovieTableCell(
            text = text,
            weight = .4f,
            textAlign = TextAlign.Start,
            style = typography.bodyMedium
        )
    }
}

@Composable
fun RowScope.MovieTableCell(
    text: String,
    weight: Float,
    textAlign: TextAlign,
    style: TextStyle
) {
    Text(
        text = text,
        Modifier
            .padding(end = 12.dp)
            .weight(weight)
            .padding(2.dp),
        textAlign = textAlign,
        style = style
    )
}

