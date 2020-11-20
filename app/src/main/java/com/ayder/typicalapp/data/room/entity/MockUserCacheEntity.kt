package com.ayder.typicalapp.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "mock_users")
data class MockUserCacheEntity(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "name")
        var name: String,

        @ColumnInfo(name = "avatarUrl")
        var avatarUrl: String,

        @ColumnInfo(name = "creationDate")
        var creationDate: Date,
)