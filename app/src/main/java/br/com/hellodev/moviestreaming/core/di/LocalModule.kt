package br.com.hellodev.moviestreaming.core.di

import br.com.hellodev.moviestreaming.core.preferences.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single<AppPreferences> {
        AppPreferences(context = androidContext())
    }
}