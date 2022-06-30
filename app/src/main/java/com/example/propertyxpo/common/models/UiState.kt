package com.example.propertyxpo.common.models

sealed class UiState  {
    object Loading : UiState()
    data class Success(val data: Any? = null)  : UiState()
    data class Error(val error: Throwable?, val isNetwork:Boolean = false) : UiState()
}
