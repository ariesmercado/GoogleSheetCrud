package com.ariesmercado.googlesheetcrud.data.source.remote.service
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.DeleteResponse
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Records
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.SuccessResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSheetApi {
    @GET("AKfycbwnw5ZE2i4UUkk9WHZAy-_CEsK1MOBU41a_hQ18qg034HY6md7fBJ-jIYaKm6zG2sXR/exec?action=read")
    suspend fun getEmployeeList() : Records

    @GET("AKfycbwnw5ZE2i4UUkk9WHZAy-_CEsK1MOBU41a_hQ18qg034HY6md7fBJ-jIYaKm6zG2sXR/exec?action=insert")
    suspend fun registerEmployee(
        @Query("username") username: String,
        @Query("email") email: String,
    ) : SuccessResponse

    @GET("AKfycbwnw5ZE2i4UUkk9WHZAy-_CEsK1MOBU41a_hQ18qg034HY6md7fBJ-jIYaKm6zG2sXR/exec?action=delete")
    suspend fun deleteEmployee(
        @Query("id") id: Int,
    ) : DeleteResponse
}