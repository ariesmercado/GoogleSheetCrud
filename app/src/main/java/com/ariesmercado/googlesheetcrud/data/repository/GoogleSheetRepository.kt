package com.ariesmercado.googlesheetcrud.data.repository

import com.ariesmercado.googlesheetcrud.data.source.remote.dto.DeleteResponse
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Employee
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Records
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.SuccessResponse
import com.ariesmercado.googlesheetcrud.data.source.remote.service.GoogleSheetApi

interface GoogleSheetRepository {
   suspend fun getEmployeeList(): Records
   suspend fun registerEmployee(username: String, email: String) : SuccessResponse

    suspend fun deleteEmployee(id: Int) : DeleteResponse
}

class GoogleSheetRepositoryImpl(private val api: GoogleSheetApi): GoogleSheetRepository {
    override suspend fun getEmployeeList(): Records {
        return api.getEmployeeList()
    }

    override suspend fun registerEmployee(username: String, email: String): SuccessResponse {
        return api.registerEmployee(username, email)
    }

    override suspend fun deleteEmployee(id: Int): DeleteResponse {
        return api.deleteEmployee(id)
    }

}
