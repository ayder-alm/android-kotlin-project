package com.ayder.typicalapp.data.retrofit

import com.ayder.typicalapp.data.retrofit.entity.MockApiUserEntity
import retrofit2.http.GET

interface MockApiService {
    @GET("user")
    suspend fun getUsers(): List<MockApiUserEntity>
}