package com.ovinkin.pillsreminder.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ovinkin.pillsreminder.data.repository.AuthRepository
import com.ovinkin.pillsreminder.presentation.viewmodel.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }
    single { AuthRepository(get(), get(), androidContext()) }
    viewModel { AuthViewModel(get()) }
}