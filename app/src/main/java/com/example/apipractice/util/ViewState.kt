package com.example.apipractice.util

sealed class ViewState {
    object Loading: ViewState()
    data class Error(val e: String) : ViewState()
    data class Success(val url: List<String>) : ViewState()

}