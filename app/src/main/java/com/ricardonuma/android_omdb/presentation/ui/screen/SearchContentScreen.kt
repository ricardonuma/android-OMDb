package com.ricardonuma.android_omdb.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ricardonuma.android_omdb.R
import com.ricardonuma.android_omdb.data.model.Movie
import com.ricardonuma.android_omdb.data.model.SearchResponse
import com.ricardonuma.android_omdb.presentation.ui.theme.AndroidOMDbTheme
import com.ricardonuma.android_omdb.utils.ErrorMessage
import com.ricardonuma.android_omdb.utils.UiState

/**
 * The SearchContentScreen displaying the search content.
 */
@Composable
fun SearchContentScreen(
    searchUiState: UiState,
    modifier: Modifier = Modifier
) {
    when (searchUiState) {
        is UiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )

        is UiState.Success -> ResultScreen(
            searchUiState.response as SearchResponse,
            modifier = modifier.fillMaxWidth()
        )

        is UiState.Error -> ErrorScreen(
            modifier = modifier.fillMaxSize(),
            searchUiState.errorMessage
        )
    }
}

/**
 * The search screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The search screen displaying the error message.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier, errorMessage: ErrorMessage) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val failMessage = when (errorMessage) {
            ErrorMessage.NO_INTERNET_MESSAGE -> stringResource(R.string.error_message_no_internet)
            ErrorMessage.DEFAULT_MESSAGE -> stringResource(R.string.error_message_default)
        }
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = failMessage
        )
        Text(
            text = failMessage,
            modifier = Modifier.padding(integerResource(R.integer.double_padding).dp)
        )
    }
}

/**
 * The ResultScreen displaying the server response.
 */
@Composable
fun ResultScreen(searchResponse: SearchResponse, modifier: Modifier = Modifier) {
    if (searchResponse.error.isEmpty()) {
        if (searchResponse.id.isNotEmpty()) {
            searchResponse.movies = listOf(
                searchResponse.let {
                    Movie(
                        id = it.id,
                        title = it.title,
                        year = it.year,
                        poster = it.poster
                    )
                }
            )
        }
        MovieList(movieList = searchResponse.movies)
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_connection_error),
                contentDescription = searchResponse.error
            )
            Text(
                text = searchResponse.error,
                modifier = Modifier.padding(integerResource(R.integer.double_padding).dp)
            )
        }
    }
}

/**
 * The MovieList displaying the list of movies
 * */
@Composable
fun MovieList(movieList: List<Movie>, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        LazyColumn(modifier = modifier) {
            items(movieList) { movie ->
                MovieCard(
                    movie = movie
                )
            }
        }
    }
}

/**
 * The MovieCard displaying each movie
 * */
@Composable
fun MovieCard(movie: Movie) {
    val defaultPadding = integerResource(R.integer.default_padding)
    Card(
        modifier = Modifier.padding(bottom = integerResource(R.integer.double_padding).dp)
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(movie.poster)
                    .build(),
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = movie.title,
                modifier = Modifier.padding(
                    horizontal = defaultPadding.dp,
                    vertical = 0.dp
                ),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = movie.year,
                modifier = Modifier.padding(
                    horizontal = defaultPadding.dp,
                    vertical = 0.dp
                ),
                style = MaterialTheme.typography.titleMedium
            )
            Button(
                onClick = {
                    // TODO: view details button click event
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(defaultPadding.dp)
            ) {
                Text(stringResource(R.string.movie_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AndroidOMDbTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AndroidOMDbTheme {
        ErrorScreen(errorMessage = ErrorMessage.DEFAULT_MESSAGE)
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListPreview() {
    AndroidOMDbTheme {
        val movie1 = Movie().getPreviewMovie("1")
        val movie2 = Movie().getPreviewMovie("2")
        val movieList = listOf(movie1, movie2)
        val searchResponse = SearchResponse(movies = movieList)
        ResultScreen(searchResponse)
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieCardPreview() {
    MovieCard(Movie().getPreviewMovie("1"))
}