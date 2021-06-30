package com.bookmyroom.data.repositories.room

import com.bookmyroom.data.datasources.RoomsDataSource
import com.bookmyroom.data.models.Room
import com.bookmyroom.data.responses.room.RoomBookingResponse
import com.bookmyroom.data.responses.room.RoomsResponse
import com.core.network.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface RoomAPIRepository {
    suspend fun getRooms(): Flow<ResponseResult<RoomsResponse>>
    suspend fun bookRoom(room: Room): Flow<ResponseResult<RoomBookingResponse>>
}

@ExperimentalCoroutinesApi
class RoomAPIRepositoryImpl @Inject constructor(private val roomsDataSource: RoomsDataSource): RoomAPIRepository {
    override suspend fun getRooms(): Flow<ResponseResult<RoomsResponse>> = flow {
        emit(roomsDataSource.getRooms())
    }.flowOn(Dispatchers.IO)

    override suspend fun bookRoom(room: Room): Flow<ResponseResult<RoomBookingResponse>> = flow {
        emit(roomsDataSource.bookRoom(room))
    }.flowOn(Dispatchers.IO)
}



