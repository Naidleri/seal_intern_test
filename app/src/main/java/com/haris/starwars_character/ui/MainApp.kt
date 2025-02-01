package com.haris.starwars_character.ui

import android.app.Application
import com.haris.core.injection.networkModule
import com.haris.core.injection.repositoryModule
import com.haris.starwars_character.injection.useCaseModule
import com.haris.starwars_character.injection.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApp)
            modules(
                listOf(
                    useCaseModule,
                    viewModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}