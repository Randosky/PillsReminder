package com.ovinkin.pillsreminder

import android.app.Application
import com.ovinkin.pillsreminder.di.rootKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PillsReminderApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@PillsReminderApp)
            modules(rootKoinModule)
        }
    }
}