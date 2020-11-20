package com.ayder.typicalapp.di

import android.content.Context
import androidx.room.Room
import com.ayder.typicalapp.data.room.MockUserDao
import com.ayder.typicalapp.data.room.MockUserDatabase
import com.ayder.typicalapp.data.room.NasaDao
import com.ayder.typicalapp.data.room.NasaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideNasaDb(@ApplicationContext context: Context): NasaDatabase {
        return Room.databaseBuilder(context, NasaDatabase::class.java, NasaDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideNasaDao(nasaDatabase: NasaDatabase) : NasaDao {
        return nasaDatabase.nasaDao()
    }

    @Singleton
    @Provides
    fun provideMockUserDb(@ApplicationContext context: Context): MockUserDatabase {
        return Room.databaseBuilder(context, MockUserDatabase::class.java, MockUserDatabase.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideMockUserDao(userDatabase: MockUserDatabase) : MockUserDao {
        return userDatabase.userDao()
    }
}