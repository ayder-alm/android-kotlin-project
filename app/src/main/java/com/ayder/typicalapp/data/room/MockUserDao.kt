package com.ayder.typicalapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayder.typicalapp.data.room.entity.MockUserCacheEntity
import java.util.*

@Dao
interface MockUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mockUserCacheEntity: MockUserCacheEntity): Long

    @Query(value = "SELECT * FROM mock_users")
    suspend fun get(): List<MockUserCacheEntity>

    @Query("SELECT * FROM mock_users WHERE creationDate BETWEEN :from AND :to")
    fun findUsersCreatedBetweenDates(from: Date, to: Date): List<MockUserCacheEntity>
}
