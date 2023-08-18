package com.app.inctureproject.utils

sealed class UiStates {
    object Loading : UiStates()
    object Complete : UiStates()
    data class Error(val message : String?) : UiStates()
}