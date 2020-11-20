package com.ayder.typicalapp.ui.main.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ayder.typicalapp.data.repository.MockUserRepository

class MockUserViewModel
@ViewModelInject
constructor(
        @Assisted private val savedStateHandle: SavedStateHandle,
        private val userRepository: MockUserRepository
) : ViewModel() {
}