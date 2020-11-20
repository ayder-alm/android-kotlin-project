package com.ayder.typicalapp.di

import com.ayder.typicalapp.data.retrofit.MockApiService
import com.ayder.typicalapp.data.retrofit.NasaService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//NASA API documentation https://api.nasa.gov/
private const val NASA_BASE_URL = "https://api.nasa.gov/"
private const val MOCK_API_BASE_URL = "https://5fb4fc1fe473ab0016a173db.mockapi.io/api/v1/"

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder {
        val loggingInterceptor = HttpLoggingInterceptor();
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideNasaService(retrofit: Retrofit.Builder): NasaService {
        return retrofit
                .baseUrl(NASA_BASE_URL)
                .build()
                .create(NasaService::class.java)
    }

    @Singleton
    @Provides
    fun provideMockService(retrofit: Retrofit.Builder): MockApiService {
        return retrofit
                .baseUrl(MOCK_API_BASE_URL)
                .build()
                .create(MockApiService::class.java)
    }

}