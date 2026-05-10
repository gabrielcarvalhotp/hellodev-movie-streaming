package br.com.hellodev.moviestreaming

import android.app.Application
import br.com.hellodev.moviestreaming.features.signup.di.signupModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                signupModule
            )
        }
    }
}