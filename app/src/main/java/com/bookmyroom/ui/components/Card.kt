package com.bookmyroom.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.bookmyroom.ui.themes.*
import com.bookmyroom.ui.viewdata.RoomViewData

@Composable
fun BMRCard(room: RoomViewData,
            onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                top = CardMeasurement.Padding,
                start = CardMeasurement.Padding,
                end = CardMeasurement.Padding,
                bottom = CardMeasurement.Padding,
            )
            .fillMaxWidth()
            .testTag(CardTag),
        elevation = CardMeasurement.Elevation
    ) {
        BMRCardItem(room, onClick)
    }
}

const val CardTag = "BMRCard"