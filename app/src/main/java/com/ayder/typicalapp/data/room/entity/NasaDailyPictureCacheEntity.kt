package com.ayder.typicalapp.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_picture")
data class NasaDailyPictureCacheEntity(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "title")
        var title: String?,

        @ColumnInfo(name = "copyright")
        var copyright: String?,

        @ColumnInfo(name = "date")
        var date: String,

        @ColumnInfo(name = "url")
        var url: String,

        @ColumnInfo(name = "hd_url")
        var hdurl: String?,

        @ColumnInfo(name = "explanation")
        var explanation: String?,
)