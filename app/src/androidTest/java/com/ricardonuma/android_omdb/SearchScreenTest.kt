package com.ricardonuma.android_omdb

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.ricardonuma.android_omdb.presentation.ui.screen.SearchScreen
import com.ricardonuma.android_omdb.presentation.ui.theme.AndroidOMDbTheme
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun search_avengers_avengersEndgame() {
        composeTestRule.setContent {
            AndroidOMDbTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SearchScreen()
                }
            }
        }
        composeTestRule.onNodeWithText("Search")
            .performTextInput("Avengers")
        composeTestRule.onNodeWithText("Avengers: Endgame")
            .assertExists("No node with this text was found.")
    }
}