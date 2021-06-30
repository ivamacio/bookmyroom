package com.bookmyroom.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp

@Composable
fun BMRLoadingSpinner(isLoading: Boolean) {
    if (isLoading) {
        Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                        .testTag(LoadingSpinner),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
    }
}

const val LoadingSpinner = "BMRLoadingSpinner"