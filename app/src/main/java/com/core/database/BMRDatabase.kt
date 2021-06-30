package com.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bookmyroom.data.dao.RoomDao
import com.bookmyroom.data.models.Room

@Database(entities = [Room::class], version = 1, exportSchema = false)
abstract class BMRDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao
}