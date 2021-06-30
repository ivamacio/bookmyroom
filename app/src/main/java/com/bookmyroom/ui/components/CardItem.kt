package com.bookmyroom.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bookmyroom.R
import com.bookmyroom.ui.themes.CardMeasurement
import com.bookmyroom.ui.themes.Heather01
import com.bookmyroom.ui.themes.Heather02
import com.bookmyroom.ui.viewdata.RoomViewData
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun BMRCardItem(
    room: RoomViewData,
    onClick: () -> Unit) {
    val resources = LocalContext.current.resources
    val painter = rememberGlidePainter(room.thumbnailImage, fadeIn = true)
    val spots = room.spots

    Image(painter = painter,
        contentDescription = room.name,
        modifier = Modifier
            .fillMaxWidth()
            .height(CardMeasurement.Height)
            .testTag(CardItemTag),
        contentScale = ContentScale.Crop)
    Column {
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(CardMeasurement.Height)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Heather02
                        )
                    )
                )
        )

        Text(
            text = room.name,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
            style = MaterialTheme.typography.h5,
            color = Color.Black,
        )
        Text(
            text = resources.getQuantityString(R.plurals.roomCard_spotRemaining_text, spots, spots),
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.primary,
        )
        BMRButton(text = stringResource(id = R.string.roomCard_quickBooking_button),
            modifier = Modifier.align(alignment = Alignment.End)
                .padding(bottom = 24.dp,
                    start = 16.dp,
                    end = 16.dp),
            onClick = onClick)
    }
}

const val CardItemTag = "BMRCardItem"