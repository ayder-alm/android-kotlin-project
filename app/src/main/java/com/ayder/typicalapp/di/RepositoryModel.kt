package com.ayder.typicalapp.di

import com.ayder.typicalapp.data.repository.MockUserRepository
import com.ayder.typicalapp.data.repository.NasaRepository
import com.ayder.typicalapp.data.retrofit.MockApiService
import com.ayder.typicalapp.data.retrofit.NasaService
import com.ayder.typicalapp.data.retrofit.util.MockUserNetworkMapper
import com.ayder.typicalapp.data.retrofit.util.NasaDailyPictureNetworkMapper
import com.ayder.typicalapp.data.room.MockUserDao
import com.ayder.typicalapp.data.room.NasaDao
import com.ayder.typicalapp.data.room.util.MockUserCacheMapper
import com.ayder.typicalapp.data.room.util.NasaDailyPictureCacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModel {
    @Singleton
    @Provides
    public fun provideNasaRepository(
            nasaService: NasaService,
            nasaDao: NasaDao,
            dailyPictureCacheMapper: NasaDailyPictureCacheMapper,
            nasaDailyPictureNetworkMapper: NasaDailyPictureNetworkMapper
    ): NasaRepository {
        return NasaRepository(
                nasaRetrofit = nasaService,
                nasaDao = nasaDao,
                cacheMapper = dailyPictureCacheMapper,
                networkMapper = nasaDailyPictureNetworkMapper
        )
    }

    @Singleton
    @Provides
    public fun provideMockUserRepository(
            mockUserService: MockApiService,
            mockUserDao: MockUserDao,
            mockUserCacheMapper: MockUserCacheMapper,
            mockUserNetworkMapper: MockUserNetworkMapper
    ): MockUserRepository {
        return MockUserRepository(
                mockApiRetrofit = mockUserService,
                mockUserDao = mockUserDao,
                mockUserCacheMapper = mockUserCacheMapper,
                mockUserNetworkMapper = mockUserNetworkMapper
        )
    }

}