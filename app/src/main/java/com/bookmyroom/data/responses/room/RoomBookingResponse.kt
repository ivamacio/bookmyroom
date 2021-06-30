package com.bookmyroom.data.responses.room

import com.google.gson.annotations.SerializedName

data class RoomBookingResponse(@SerializedName("success") private val isRequestSuccessful: Boolean)