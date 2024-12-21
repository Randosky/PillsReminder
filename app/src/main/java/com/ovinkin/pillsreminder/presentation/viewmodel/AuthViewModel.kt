package com.ovinkin.pillsreminder.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovinkin.pillsreminder.data.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun login(email: String, password: String) {
        viewModelScope.launch {
            Log.w("login", "залогинились")
            authRepository.login(email, password)
        }
    }

    fun register(fullName: String, email: String, password: String, role: String) {
        viewModelScope.launch {
            Log.w("register", "регистрация")
            authRepository.register(fullName, email, password, role)
        }
    }
}