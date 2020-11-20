package com.ayder.typicalapp.data.repository

import com.ayder.typicalapp.data.retrofit.MockApiService
import com.ayder.typicalapp.data.retrofit.util.MockUserNetworkMapper
import com.ayder.typicalapp.data.room.MockUserDao
import com.ayder.typicalapp.data.room.util.MockUserCacheMapper

class MockUserRepository
constructor(
        private val mockApiRetrofit: MockApiService,
        private val mockUserDao: MockUserDao,
        private val mockUserCacheMapper: MockUserCacheMapper,
        private val mockUserNetworkMapper: MockUserNetworkMapper
)
//todo implement later