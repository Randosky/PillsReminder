package com.ovinkin.pillsreminder.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ovinkin.pillsreminder.data.model.UserData
import com.ovinkin.pillsreminder.data.repository.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    var error: String? by mutableStateOf(null)
    var loading: Boolean by mutableStateOf(false)

    suspend fun login(userData: UserData): Boolean {
        loading = true
        return try {
            val isAuthenticated = authRepository.login(userData)
            error = null
            isAuthenticated
        } catch (e: Exception) {
            error = e.localizedMessage
            false
        } finally {
            loading = false
        }
    }

    suspend fun register(userData: UserData): Boolean {
        loading = true
        return try {
            val isRegistered = authRepository.register(userData)
            error = null
            isRegistered
        } catch (e: Exception) {
            error = e.localizedMessage
            false
        } finally {
            loading = false
        }
    }
}