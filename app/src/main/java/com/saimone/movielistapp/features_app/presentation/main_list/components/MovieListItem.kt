package com.saimone.movielistapp.features_app.presentation.main_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saimone.movielistapp.features_app.domain.models.Movie

@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier
) {
    Box(modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {

        }
    }
}