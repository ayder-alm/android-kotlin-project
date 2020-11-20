package com.ayder.typicalapp.data.repository

import android.util.Log
import com.ayder.typicalapp.data.model.NasaDailyPicture
import com.ayder.typicalapp.data.retrofit.NasaService
import com.ayder.typicalapp.data.retrofit.util.NasaDailyPictureNetworkMapper
import com.ayder.typicalapp.data.room.NasaDao
import com.ayder.typicalapp.data.room.util.NasaDailyPictureCacheMapper
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private const val TAG = "NasaRepository"

private const val NASA_API_KEY: String =
    "9gLyPa8ToBpiP9fno4YzpBJBIrvdJ9Y6WIY7ha49" // store somewhere safe later

private val DAILY_DATE_FORMAT: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

class NasaRepository(
    private val nasaRetrofit: NasaService,
    private val nasaDao: NasaDao,
    private val cacheMapper: NasaDailyPictureCacheMapper,
    private val networkMapper: NasaDailyPictureNetworkMapper
) {

    suspend fun getTodayPicture(): NasaDailyPicture {
        return networkMapper.toModel(nasaRetrofit.getTodayPicture(NASA_API_KEY))
    }

    suspend fun getDailyPicture(dayDate: Date): NasaDailyPicture {
        val date = dateToDayString(dayDate)
        val apiEntity = nasaRetrofit.getDailyPicture(NASA_API_KEY, date)
        return networkMapper.toModel(apiEntity)
    }

    //There's no API to get range of pictures so we load available pictures one by one
    //to db cache and collect them
    suspend fun getDailyPictures(dayFrom: Date, dayTo: Date): List<NasaDailyPicture> {
        cacheDailyPictures(dayFrom, dayTo)
        val pictures = mutableListOf<NasaDailyPicture>()
        val dbPictures = nasaDao.get()
        for (entity in dbPictures) {
            val cachedImage = cacheMapper.toModel(entity)
            pictures.add(cachedImage)
        }
        return pictures
    }

    //Load Nasa picture models from Network to DB cache
    suspend fun cacheDailyPictures(dateFrom: Date, dateTo: Date) {
        val today = Date()
        //don't make api calls for future days cap query at today
        val dayTo = if (dateTo.after(today)) today else dateTo
        if (dateFrom.after(dayTo)) {
            return
        }
            nasaDao.clear()
            val daysBetween =
                TimeUnit.DAYS.convert(dayTo.time - dateFrom.time, TimeUnit.MILLISECONDS)
            val calendar = Calendar.getInstance()
            calendar.time = dateFrom
            repeat(daysBetween.toInt() + 1) {
                try {
                    val dailyPicture = getDailyPicture(calendar.time)
                    val cacheEntity = cacheMapper.fromModel(dailyPicture)
                    nasaDao.insert(cacheEntity)
                } catch (e: Exception) {
                    Log.e(TAG, "Error in cacheDailyPictures()", e)
                }
                calendar.add(Calendar.DATE, 1)//add 1 day and repeat
            }
    }

    private fun dateToDayString(date: Date): String {
        return DAILY_DATE_FORMAT.format(date)
    }

}