package com.example.data.di

import com.example.data.remote.IApiHelper
import com.example.data.repository.ApiHelperImpl
import org.koin.dsl.module

val dataModule  = module {
    single<IApiHelper> { ApiHelperImpl(get()) }
    //repo module
    includes(movieListModule,databaseModule,networkModule, movieDetailRepoModule)
}