package com.ovinkin.pillsreminder.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ovinkin.pillsreminder.data.repository.UserRepository
import com.ovinkin.pillsreminder.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootKoinModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }
    single { UserRepository(get(), get()) }
    viewModel { UserViewModel(get()) }
}