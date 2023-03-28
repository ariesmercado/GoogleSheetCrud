package com.ariesmercado.googlesheetcrud.presentation.ui.main

import androidx.lifecycle.viewModelScope
import com.ariesmercado.googlesheetcrud.common.DataState
import com.ariesmercado.googlesheetcrud.common.Response
import com.ariesmercado.googlesheetcrud.common.base.BaseViewModel
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.DeleteResponse
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Employee
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Records
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.SuccessResponse
import com.ariesmercado.googlesheetcrud.domain.use_case.DeleteEmployeeUseCase
import com.ariesmercado.googlesheetcrud.domain.use_case.GetGoogleSheetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGoogleSheetUseCase: GetGoogleSheetUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase
): BaseViewModel() {

    private val _employeeState = MutableStateFlow(DataState<Records>())
    val employeeState = _employeeState.asStateFlow()

    private val _response = MutableStateFlow(DataState<DeleteResponse>())
    val response = _response.asStateFlow()

    fun getGoogleSheetData() {
        getGoogleSheetUseCase().onEach { result ->
                when (result) {
                    is Response.Success -> {
                        _employeeState.value = DataState(data = result.data)
                        Timber.d("RESPONSE -> ${_employeeState.value}")
                    }
                    is Response.Error -> {
                        _employeeState.value = DataState(error = result.message ?: "")
                        Timber.d("RESPONSE -> ${_employeeState.value}")
                    }
                    is Response.Loading -> {
                        _employeeState.value = DataState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun deleteData(id:Int) {
        deleteEmployeeUseCase(id).onEach { result ->
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