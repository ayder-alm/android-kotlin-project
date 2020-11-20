package com.ayder.typicalapp.ui.main.viewmodel

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayder.typicalapp.data.model.NasaDailyPicture
import com.ayder.typicalapp.data.repository.NasaRepository
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "NasaPictures"

class DailyPicturesActivityViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val nasaRepository: NasaRepository
) : ViewModel() {
    var pictures: MutableLiveData<List<NasaDailyPicture>>
    var dateFrom: MutableLiveData<Date>
    var dateTo: MutableLiveData<Date>
    var isLoading: MutableLiveData<Boolean>
    var error: MutableLiveData<String>

    init {
        val calendar = Calendar.getInstance()
        dateTo = MutableLiveData(calendar.time)
        calendar.add(Calendar.DAY_OF_YEAR, -5) //subtract 10 days
        dateFrom = MutableLiveData(calendar.time)
        isLoading = MutableLiveData(false)
        pictures = MutableLiveData()
        error = MutableLiveData()
    }

    fun loadDailyPictures() {
        isLoading.value = true
        pictures.value = emptyList()
        viewModelScope.launch {
            var dailyPictures: List<NasaDailyPicture>? = null
            try {
                dailyPictures = nasaRepository.getDailyPictures(dateFrom.value!!, dateTo.value!!)
            } catch (e: Exception) {
                Log.e(TAG, "Error loading daily pictures", e)
                error.value = e.message
            }
            pictures.value = dailyPictures
            isLoading.value = false
        }
    }
}