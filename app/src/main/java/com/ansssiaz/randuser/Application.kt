package com.ansssiaz.randuser

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.ansssiaz.randuser.di.apiModule
import com.ansssiaz.randuser.di.repositoryModule
import com.ansssiaz.randuser.di.viewModelModule

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(repositoryModule, apiModule, viewModelModule))
        }
    }
}