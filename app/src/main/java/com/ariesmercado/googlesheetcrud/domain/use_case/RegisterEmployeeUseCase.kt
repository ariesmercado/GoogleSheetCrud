package com.ariesmercado.googlesheetcrud.domain.use_case

import com.ariesmercado.googlesheetcrud.common.Response
import com.ariesmercado.googlesheetcrud.data.repository.GoogleSheetRepository
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.SuccessResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RegisterEmployeeUseCase @Inject constructor(
    private val repository: GoogleSheetRepository
) {
    operator fun invoke(username: String, email: String): Flow<Response<SuccessResponse>> = flow {
        try {
            emit(Response.Loading())
            val data = repository.registerEmployee(username, email)
            emit(Response.Success(data))
        } catch (e: IOException) {
            emit(Response.Error("Data retrieve failed"))
        }
    }
}