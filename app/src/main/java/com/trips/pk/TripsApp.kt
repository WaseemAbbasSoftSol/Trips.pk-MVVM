package com.trips.pk

import android.app.Application
import com.trips.pk.di.repositoriesModule
import com.trips.pk.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TripsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TripsApp)
            modules(listOf(viewModelsModule, repositoriesModule))
        }
    }

}