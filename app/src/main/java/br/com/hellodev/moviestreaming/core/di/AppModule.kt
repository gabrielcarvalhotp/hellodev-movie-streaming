package br.com.hellodev.moviestreaming.core.di

import org.koin.dsl.module

val appModules = module {
    includes(
        repositoryModule,
        presenterModule,
        useCasesModule,
    )
}