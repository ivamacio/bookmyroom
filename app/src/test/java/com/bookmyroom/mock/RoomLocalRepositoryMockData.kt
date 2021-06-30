package com.bookmyroom.mock

import com.bookmyroom.data.models.Room
import com.bookmyroom.data.responses.room.RoomBookingResponse

object RoomLocalRepositoryMockData {

    const val NAME = "Mina"
    const val SPOTS = 39
    const val THUMBNAIL = "https://images.unsplash.com/photo-1601762429744-46fe92ccd903?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"

    val room = Room(NAME, SPOTS, THUMBNAIL)

    val successfulBookingResponse = RoomBookingResponse(true)

    fun createRoom(name: String,
                   spots: Int,
                   thumbnail: String): Room {
        return Room(name = name,
            spots = spots,
            thumbnail = thumbnail)
    }
}