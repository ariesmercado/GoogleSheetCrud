package com.ariesmercado.googlesheetcrud.presentation.ui.manage

import androidx.lifecycle.viewModelScope
import com.ariesmercado.googlesheetcrud.common.DataState
import com.ariesmercado.googlesheetcrud.common.Response
import com.ariesmercado.googlesheetcrud.common.base.BaseViewModel
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Records
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.SuccessResponse
import com.ariesmercado.googlesheetcrud.domain.use_case.DeleteEmployeeUseCase
import com.ariesmercado.googlesheetcrud.domain.use_case.RegisterEmployeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ManageEmployeeViewModel@Inject constructor(
    private val registerEmployeeUseCase: RegisterEmployeeUseCase,

): BaseViewModel() {

    private val _response = MutableStateFlow(DataState<SuccessResponse>())
    val response = _response.asStateFlow()

    fun sendData(username: String, email: String) {
        registerEmployeeUseCase(username, email).onEach { result ->
            when (result) {
                is Response.Success -> {
                    _response.value = DataState(data = result.data)
                }
                is Response.Error -> {
                    _response.value = DataState(error = result.message ?: "")
                }
                is Response.Loading -> {
                    _response.value = DataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}