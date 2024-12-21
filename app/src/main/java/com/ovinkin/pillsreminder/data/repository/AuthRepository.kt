package com.ovinkin.pillsreminder.data.repository

import com.ovinkin.pillsreminder.domain.repository.IAuthRepository

class AuthRepository : IAuthRepository {
    override fun login(email: String, password: String): Boolean {
        return true
    }

    override fun register(
        fullName: String, email: String, password: String, role: String
    ): Boolean {
        return true
    }
}