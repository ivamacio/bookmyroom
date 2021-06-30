package com.bookmyroom

import androidx.compose.runtime.Composable
import com.bookmyroom.ui.home.Home
import com.bookmyroom.ui.home.HomeViewModel

@Composable
fun BookMyRoomApp(viewModel: HomeViewModel) {
    Home(viewModel)
}