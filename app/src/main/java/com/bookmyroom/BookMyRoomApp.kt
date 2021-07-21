package com.bookmyroom

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.bookmyroom.ui.home.Home
import com.bookmyroom.ui.home.HomeViewModel

@ExperimentalFoundationApi
@Composable
fun BookMyRoomApp(viewModel: HomeViewModel) {
    Home(viewModel)
}