package com.ricardonuma.android_omdb.utils

import com.ricardonuma.android_omdb.data.model.SearchResponse


interface UiState {
    /** Return SearchResponse in case of API call success */
    data class Success(val response: SearchResponse) : UiState

    /** Return ErrorMessage in case of API call fail */
    data class Error(val errorMessage: ErrorMessage) : UiState

    /** Return Loading UiState when API is called */
    data object Loading : UiState
}

enum class ErrorMessage {
    NO_INTERNET_MESSAGE,
    DEFAULT_MESSAGE
}