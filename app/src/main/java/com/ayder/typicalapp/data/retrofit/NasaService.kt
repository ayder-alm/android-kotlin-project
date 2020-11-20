package com.ayder.typicalapp.data.retrofit

import com.ayder.typicalapp.data.retrofit.entity.DailyPictureEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {

    @GET("planetary/apod")
    suspend fun getTodayPicture(
        @Query("api_key") apiKey: String
    ): DailyPictureEntity

    @GET("planetary/apod")
    suspend fun getDailyPicture(
        @Query("api_key") apiKey: String,
        @Query("date") date: String
    ): DailyPictureEntity

}