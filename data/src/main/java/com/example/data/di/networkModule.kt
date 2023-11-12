package com.example.data.di

import com.example.data.remote.IApiHelper
import com.example.data.remote.createApiService
import com.example.data.remote.createOkHttp
import com.example.utility.constant.UrlConfig
import org.koin.dsl.module

val networkModule = module {
    single { createOkHttp() }
    single { createApiService<IApiHelper>(get(), UrlConfig.BASE_URL.value) }
}