package com.ariesmercado.googlesheetcrud.data.source.remote.service

import retrofit2.http.GET

interface GoogleSheetApi {
    @GET("currency-exchange-rates")
    suspend fun getExchangeRate() : Unit
}