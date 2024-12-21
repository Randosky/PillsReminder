package com.ovinkin.pillsreminder.domain.repository

interface IAuthRepository {
    fun login(email: String, password: String): Boolean

    fun register(fullName: String, email: String, password: String, role: String): Boolean
}