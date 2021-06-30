package com.bookmyroom.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.bookmyroom.ui.themes.Flirt01

@Composable
fun BMRButton(text: String,
              modifier: Modifier = Modifier.fillMaxWidth().testTag(ButtonTag),
              horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
              onClick: () -> Unit) {
    Column(horizontalAlignment = horizontalAlignment,
        modifier = modifier) {
        Button(onClick = onClick,
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Flirt01,
                contentColor = Color.White
        )) {
            Text(text)
        }
    }
}

const val ButtonTag = "BMRButton"