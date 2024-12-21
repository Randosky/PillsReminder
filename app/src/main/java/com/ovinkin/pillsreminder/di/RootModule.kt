package com.ovinkin.pillsreminder.di

import com.ovinkin.pillsreminder.data.repository.AuthRepository
import com.ovinkin.pillsreminder.presentation.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rootKoinModule = module {
    viewModel { AuthViewModel(get()) }
    single { AuthRepository() }
}