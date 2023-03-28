package com.ariesmercado.googlesheetcrud.data.source.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteResponse(
    val status: Boolean,
    val message: String?
)
