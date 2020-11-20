package com.ayder.typicalapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ayder.typicalapp.data.room.entity.NasaDailyPictureCacheEntity

@Database(entities = [NasaDailyPictureCacheEntity::class], version = 2, exportSchema = false)
abstract class NasaDatabase : RoomDatabase() {
    abstract fun nasaDao(): NasaDao

    companion object {
        const val DB_NAME = "nasa_db"
    }
}