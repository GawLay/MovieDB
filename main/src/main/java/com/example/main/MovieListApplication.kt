package com.example.main

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.useCaseModule
import com.example.main.di.viewModelModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@MovieListApplication)
            modules(
                dataModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}