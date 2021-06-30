package com.bookmyroom.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.bookmyroom.R
import com.bookmyroom.ui.components.*

@Composable
fun Home(viewModel: HomeViewModel) {
    val viewState by viewModel.state.collectAsState()
    val dialogState by viewModel.isRoomBooked.collectAsState()
    val errorMessageState by viewModel.errorMessage.collectAsState()

    Box(Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(items = viewState.showRooms) {
                    room -> BMRCard(room = room, onClick = { viewModel.requestBookRoom(room) })
            }
        }

        BMRLoadingSpinner(isLoading = viewState.isLoading)

        BMREmptyState(isVisible = viewState.isEmpty,
                       text = stringResource(id = R.string.roomCard_emptyState_text))

        BMRSnackBar(text = errorMessageState)

        BMRDialog(isVisible = dialogState,
            onClose = { viewModel.hideDialog() },
            title = stringResource(id = R.string.roomCard_bookRoomDialog_title),
            description = stringResource(id = R.string.roomCard_bookRoomDialog_description),
            button = stringResource(id = R.string.roomCard_bookRoomDialog_button))
    }
}