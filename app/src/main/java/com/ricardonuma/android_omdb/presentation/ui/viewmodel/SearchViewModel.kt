package com.ricardonuma.android_omdb.presentation.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricardonuma.android_omdb.data.repository.SearchRepository
import com.ricardonuma.android_omdb.utils.Constants
import com.ricardonuma.android_omdb.utils.ErrorMessage
import com.ricardonuma.android_omdb.utils.UiState
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import retrofit2.HttpException
import java.io.IOException


class SearchViewModel : ViewModel() {

    private val repository = SearchRepository()

    /** The mutable State that stores the status of the most recent request */
    var searchUiState: UiState by mutableStateOf(UiState.Loading)
    var searchText: String = ""

    /**
     * Call getMovies() on init so we can display status immediately.
     */
    init {
        getMovies()
    }

    /**
     * Gets movies information from the OMDb API service and updates the
     * [searchUiState].
     */
    @VisibleForTesting
    internal fun getMovies() {
        viewModelScope.launch {
            searchUiState = UiState.Loading
            searchUiState = try {
                val searchResponse = repository.getMovies(search = searchText)
                UiState.Success(searchResponse)
            } catch (e: HttpException) {
                UiState.Error(ErrorMessage.DEFAULT_MESSAGE)
            } catch (e: IOException) {
                UiState.Error(ErrorMessage.NO_INTERNET_MESSAGE)
            } catch (e: HttpException) {
                UiState.Error(ErrorMessage.DEFAULT_MESSAGE)
            }
        }
    }

    /**
     * For each typed char check search text length.
     * if it is bigger than MAX_SEARCH_CHAR, does nothing.
     * Otherwise, update searchText and call getMovies.
     */
    fun onSearchTextChange(text: String) {
        if (text.length > Constants.MAX_SEARCH_CHAR) return

        searchText = text
        getMovies()
    }
}