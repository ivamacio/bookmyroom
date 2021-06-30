package com.bookmyroom.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.bookmyroom.ui.themes.BookMyRoomColors
import com.bookmyroom.ui.themes.BookMyRoomShapes

@Composable
fun BookMyRoomTheme(content: @Composable () -> Unit) {
        MaterialTheme(
            colors = BookMyRoomColors,
            typography = MaterialTheme.typography,
            shapes = BookMyRoomShapes,
            content = content
        )
    }