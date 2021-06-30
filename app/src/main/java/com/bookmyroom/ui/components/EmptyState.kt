package com.bookmyroom.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign

@Composable
fun BMREmptyState(isVisible: Boolean, text: String) {
    if (isVisible) {
        Column(modifier = Modifier.fillMaxSize().testTag(EmptyStateTag),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Text(text = text,
                textAlign = TextAlign.Center)
        }

        //TODO: Implement in the next interaction: Add a refresh button and update copy
    }
}

const val EmptyStateTag = "BMREmptyState"