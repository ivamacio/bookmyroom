package com.bookmyroom.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "room")
@Parcelize
data class Room(@PrimaryKey(autoGenerate = false) @SerializedName("name") val name: String,
                @SerializedName("spots") val spots: Int,
                @SerializedName("thumbnail") val thumbnail: String): Parcelable