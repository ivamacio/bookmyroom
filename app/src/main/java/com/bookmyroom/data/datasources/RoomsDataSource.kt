package com.bookmyroom.data.datasources

import com.bookmyroom.data.api.RoomsApi
import com.bookmyroom.data.models.Room
import com.bookmyroom.data.responses.room.RoomBookingResponse
import com.bookmyroom.data.responses.room.RoomsResponse
import com.core.network.BaseDataSource
import com.core.network.ConnectionManager
import com.core.network.ResponseResult
import javax.inject.Inject

class RoomsDataSource @Inject constructor(connectionManager: ConnectionManager,
                                        private val roomsApi: RoomsApi): BaseDataSource(connectionManager) {

    suspend fun getRooms(): ResponseResult<RoomsResponse> = getResult { roomsApi.fetchRooms() }

    suspend fun bookRoom(roomId: Room): ResponseResult<RoomBookingResponse> = getResult { roomsApi.bookRoom() }
}