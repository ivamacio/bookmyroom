package com.bookmyroom.data.api

import com.bookmyroom.data.responses.room.RoomBookingResponse
import com.bookmyroom.data.responses.room.RoomsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoomsApi {

    @GET("/rooms.json")
    suspend fun fetchRooms(): Response<RoomsResponse>

    @GET("/bookRoom.json")
    suspend fun bookRoom(): Response<RoomBookingResponse>
}