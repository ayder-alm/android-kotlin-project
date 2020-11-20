package com.ayder.typicalapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ayder.typicalapp.data.room.entity.MockUserCacheEntity
import com.ayder.typicalapp.data.room.entity.NasaDailyPictureCacheEntity
import com.ayder.typicalapp.data.room.util.Converters

@Database(entities = [MockUserCacheEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MockUserDatabase : RoomDatabase() {
    abstract fun userDao(): MockUserDao

    companion object {
        const val DB_NAME = "mock_user_db"
    }
}