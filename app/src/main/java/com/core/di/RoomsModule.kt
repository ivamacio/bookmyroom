package com.core.di

import com.bookmyroom.data.api.RoomsApi
import com.bookmyroom.data.dao.RoomDao
import com.bookmyroom.data.datasources.RoomsDataSource
import com.bookmyroom.data.repositories.room.RoomAPIRepository
import com.bookmyroom.data.repositories.room.RoomAPIRepositoryImpl
import com.bookmyroom.data.repositories.room.RoomLocalRepository
import com.bookmyroom.data.repositories.room.RoomLocalRepositoryImpl
import com.core.network.ConnectionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object RoomsModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRooms(retrofit: Retrofit): RoomsApi = retrofit.create(RoomsApi::class.java)

    @Provides
    @ActivityRetainedScoped
    fun provideRoomsDataSource(connectionManager: ConnectionManager,
                                roomsApi: RoomsApi): RoomsDataSource = RoomsDataSource(connectionManager, roomsApi)

    @Provides
    @ActivityRetainedScoped
    fun provideRoomAPIRepository(roomsDataSource: RoomsDataSource): RoomAPIRepository = RoomAPIRepositoryImpl(roomsDataSource)

    @Provides
    @ActivityRetainedScoped
    fun provideRoomLocalRepository(roomDao: RoomDao): RoomLocalRepository = RoomLocalRepositoryImpl(roomDao)
}