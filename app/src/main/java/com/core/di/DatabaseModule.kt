package com.core.di

import android.app.Application
import androidx.room.Room
import com.core.database.BMRDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): BMRDatabase = Room.databaseBuilder(application,
                                                        BMRDatabase::class.java, "bmr_database")
                                                        .build()

    @Provides
    fun provideRoomDao(db: BMRDatabase) = db.roomDao()
}