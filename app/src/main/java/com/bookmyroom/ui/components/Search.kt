package com.bookmyroom.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.bookmyroom.R
import com.bookmyroom.ui.home.HomeViewModel
import com.bookmyroom.ui.themes.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun BMRSearch(viewModel: HomeViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Row(modifier = Modifier.background(Grey).fillMaxWidth().testTag(SearchTag)) {
        OutlinedTextField(value = textState.value,
                    singleLine = true,
                    onValueChange = { value ->
                        textState.value = value
                        coroutineScope.launch {
                            viewModel.readRooms(value.text)
                        }
                    },
                    modifier = Modifier
                        .padding(start = SearchMeasurement.Padding,
                                    end = SearchMeasurement.Padding,
                                    top = SearchMeasurement.PaddingTop,
                                    bottom = SearchMeasurement.Padding)
                        .fillMaxWidth(),
                    label = { Text(stringResource(id = R.string.roomCard_searchRoom_text)) }
        )
    }
}

const val SearchTag = "BMRSearch"