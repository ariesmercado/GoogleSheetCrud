package com.ariesmercado.googlesheetcrud.common.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

inline fun <reified T>provideGSONApi(apiUrl: String, httpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
        .create(T::class.java)
}

inline fun <reified T>provideMoshiApi(apiUrl: String, httpClient: OkHttpClient): T {
    return Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(MoshiConverterFactory.create().asLenient())
        .client(httpClient)
        .build()
        .create(T::class.java)
}