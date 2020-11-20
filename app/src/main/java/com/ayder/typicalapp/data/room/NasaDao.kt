package com.ayder.typicalapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayder.typicalapp.data.room.entity.NasaDailyPictureCacheEntity

@Dao
interface NasaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nasaDailyPictureCacheEntity: NasaDailyPictureCacheEntity): Long

    @Query(value = "SELECT * FROM daily_picture")
    suspend fun get(): List<NasaDailyPictureCacheEntity>

    @Query(value = "SELECT * FROM daily_picture WHERE id = :id")
    suspend fun get(id: Long): List<NasaDailyPictureCacheEntity>

    @Query("DELETE FROM daily_picture")
    suspend fun  clear()
}
