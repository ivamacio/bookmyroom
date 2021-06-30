package com.bookmyroom.data.dao

import androidx.room.*
import com.bookmyroom.data.models.Room
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomDao {
    @Query("SELECT name, spots, thumbnail FROM room WHERE spots > 0 ORDER BY name ASC")
    fun getAlphabetizedRooms(): Flow<List<Room>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(room: Room)

    @Update
    suspend fun update(room: Room)
}