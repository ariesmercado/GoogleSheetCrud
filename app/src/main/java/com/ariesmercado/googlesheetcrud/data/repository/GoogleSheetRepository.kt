package com.ariesmercado.googlesheetcrud.data.repository

import com.ariesmercado.googlesheetcrud.data.source.remote.service.GoogleSheetApi

interface GoogleSheetRepository {
}

class GoogleSheetRepositoryImpl(private val api: GoogleSheetApi): GoogleSheetRepository
