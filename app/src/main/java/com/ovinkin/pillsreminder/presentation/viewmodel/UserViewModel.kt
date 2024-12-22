package com.ovinkin.pillsreminder.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ovinkin.pillsreminder.data.model.UserData
import com.ovinkin.pillsreminder.data.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    var loading: Boolean by mutableStateOf(false)

    suspend fun getUserData(): UserData? {
        loading = true
        return try {
            userRepository.getUserData()
        } catch (e: Exception) {
            null
        } finally {
            loading = false
        }
    }

    suspend fun setUserData(userData: UserData?): Unit? {
        loading = true
        return try {
            userRepository.setUserData(userData)
        } catch (e: Exception) {
            null
        } finally {
            loading = false
        }
    }
}