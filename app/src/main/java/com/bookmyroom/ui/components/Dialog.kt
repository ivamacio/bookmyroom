package com.bookmyroom.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag

@Composable
fun BMRDialog(isVisible: Boolean,
              onClose: () -> Unit,
              title: String,
              description: String,
              button: String) {
    if (isVisible) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(text = title)
            },
            modifier = Modifier.testTag(DialogTag),
            text = {
                Text(text = description)
            },
            confirmButton = {
                Button(onClick = { onClose() }) {
                    Text(text = button, color = Color.White)
                }
            }
        )
    }
}

const val DialogTag = "BMRDialog"