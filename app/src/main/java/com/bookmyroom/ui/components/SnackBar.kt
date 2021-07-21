package com.bookmyroom.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import kotlinx.coroutines.launch

@Composable
fun BMRSnackBar(text: String?) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    if (!text.isNullOrEmpty()) {
        Box(Modifier.fillMaxSize()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message = text)
            }

            SnackbarHost(hostState = snackbarHostState,
                         modifier = Modifier.align(Alignment.BottomCenter)
                                    .testTag(SnackBar)
            )
        }
    }
}

const val SnackBar = "BMRSnackBar"