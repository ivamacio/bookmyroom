package com.bookmyroom.ui.viewdata.mappers

import com.bookmyroom.data.models.Room
import com.bookmyroom.ui.viewdata.DataMapper
import com.bookmyroom.ui.viewdata.RoomViewData

class RoomViewDataMapper : DataMapper<Room, RoomViewData> {
    override fun map(source: Room): RoomViewData {
        return RoomViewData(name = source.name,
                            spots = source.spots,
                            thumbnailImage = source.thumbnail)
    }
}

class RoomMapper : DataMapper<RoomViewData, Room> {
    override fun map(source: RoomViewData): Room {
        return Room(name = source.name,
                    spots = source.spots,
                    thumbnail = source.thumbnailImage)
    }
}