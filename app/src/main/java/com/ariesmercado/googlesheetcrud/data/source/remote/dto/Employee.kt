package com.ariesmercado.googlesheetcrud.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Employee(
    val id: Int,
    val username: String?,
    val email: String?,
    val timestamp: String?,
    val currentTime: String?
)
