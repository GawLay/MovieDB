package com.example.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun createOkHttp(): OkHttpClient = OkHttpClient.Builder()
    .addNetworkInterceptor(Interceptor { chain ->
        var request = chain.request();
        request = request.newBuilder().addHeader("accept", "application/json")
            .addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyMWRhNzliNjZlMjRiY2Y3ZThlMGMyOTczNDRkZDFiMSIsInN1YiI6IjVjNjUxYmFhOTI1MTQxMmZjNmZmZGQ4MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.NMgk9bcbtKItIqLsi81GRfNHDGhFTkXywIJSorvA8wM"
            )
            .build()
        chain.proceed(request)
    })
    .readTimeout(240, TimeUnit.SECONDS)
    .connectTimeout(240, TimeUnit.SECONDS)
    .addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    ).build()


inline fun <reified T> createApiService(okHttpClient: OkHttpClient, baseUrl: String): T {
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}