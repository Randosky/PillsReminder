package com.ovinkin.pillsreminder.domain.repository

import com.ovinkin.pillsreminder.data.model.UserData

interface IAuthRepository {
    suspend fun login(userData: UserData): Boolean

    suspend fun register(userData: UserData): Boolean
}