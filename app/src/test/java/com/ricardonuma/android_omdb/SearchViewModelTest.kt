package com.ricardonuma.android_omdb

import com.ricardonuma.android_omdb.presentation.ui.viewmodel.SearchViewModel
import com.ricardonuma.android_omdb.utils.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setUp() {
        viewModel = SearchViewModel()
    }

    @Test
    fun searchViewModel_CorrectSearchUiState_SearchUiStateLoading() {
        viewModel.getMovies()
        val searchUiState = viewModel.searchUiState
        assertEquals(UiState.Loading, searchUiState)
    }
}