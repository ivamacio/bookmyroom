package com.bookmyroom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bookmyroom.BookMyRoomApp
import com.bookmyroom.ui.BookMyRoomTheme
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
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
}