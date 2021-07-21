package com.bookmyroom.data.repositories.room

import com.bookmyroom.data.dao.RoomDao
import com.bookmyroom.data.models.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface RoomLocalRepository {
    suspend fun getAlphabetizedRooms(searchByName: String): Flow<List<Room?>?>?
    suspend fun insertRoom(room: Room)
    suspend fun updateRoom(room: Room)
}

@ExperimentalCoroutinesApi
class RoomLocalRepositoryImpl @Inject constructor(private val roomDao: RoomDao): RoomLocalRepository {

    private var _rooms: Flow<List<Room?>?>? = roomDao.getAlphabetizedRoomsWithSearch()

    override suspend fun getAlphabetizedRooms(searchByName: String): Flow<List<Room?>?>? {
        _rooms = roomDao.getAlphabetizedRoomsWithSearch(searchByName)
        return _rooms
    }

    override suspend fun insertRoom(room: Room) {
        withContext(Dispatchers.IO) {
            roomDao.insert(room)
        }
    }

    override suspend fun updateRoom(room: Room) {
        withContext(Dispatchers.IO) {
            roomDao.update(room)
        }
    }
}