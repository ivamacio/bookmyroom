package com.bookmyroom.data.responses.room

import com.bookmyroom.data.models.Room
import com.google.gson.annotations.SerializedName

data class RoomsResponse(@SerializedName("rooms") val rooms: List<Room>?)