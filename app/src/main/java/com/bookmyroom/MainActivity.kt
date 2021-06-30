package com.bookmyroom

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookmyroom.ui.BookMyRoomTheme
import com.bookmyroom.ui.home.HomeViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = viewModel(HomeViewModel::class.java)
            BookMyRoomTheme {
                ProvideWindowInsets {
                    BookMyRoomApp(viewModel)
                }
            }
        }
    }
}