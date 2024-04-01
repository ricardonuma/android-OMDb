package com.ricardonuma.android_omdb.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ricardonuma.android_omdb.R
import com.ricardonuma.android_omdb.presentation.ui.viewmodel.SearchViewModel
import com.ricardonuma.android_omdb.utils.Constants

/**
 * The SearchScreen displaying the search TextField and the content.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    val searchViewModel: SearchViewModel = viewModel()
    val searchText = searchViewModel.searchText

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(integerResource(R.integer.double_padding).dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = searchViewModel::onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = integerResource(R.integer.double_padding).dp),
            label = { Text(text = stringResource(R.string.search)) },
            singleLine = true,
            supportingText = {
                Text(
                    text = "${searchText.length} / ${Constants.MAX_SEARCH_CHAR}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                )
            }
        )
        SearchContentScreen(
            searchUiState = searchViewModel.searchUiState
        )
    }
}

