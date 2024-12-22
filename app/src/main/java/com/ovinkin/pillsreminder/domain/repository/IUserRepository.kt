package com.ovinkin.pillsreminder.domain.repository

import com.ovinkin.pillsreminder.data.model.UserData

interface IUserRepository {
    suspend fun getUserData(): UserData?

    suspend fun setUserData(userData: UserData?): Unit?
}