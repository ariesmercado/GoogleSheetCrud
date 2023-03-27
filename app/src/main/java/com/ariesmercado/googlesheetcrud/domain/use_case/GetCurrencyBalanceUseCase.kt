package com.ariesmercado.googlesheetcrud.domain.use_case

import com.ariesmercado.googlesheetcrud.common.Response
import com.ariesmercado.googlesheetcrud.data.repository.GoogleSheetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetCurrencyBalanceUseCase @Inject constructor(
    private val repository: GoogleSheetRepository
) {
    operator fun invoke(): Flow<Response<List<Unit>>> = flow {
        try {
            emit(Response.Loading())
//            val data = repository.getCurrencyBalance()
//            emit(Response.Success(data))
        } catch (e: IOException) {
            emit(Response.Error("Data retrieve failed"))
        }
    }
}