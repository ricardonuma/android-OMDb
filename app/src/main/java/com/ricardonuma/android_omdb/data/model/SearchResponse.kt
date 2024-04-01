package com.ricardonuma.android_omdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    /**
     * Field used in case of list of movies returned from API
     */
    @SerialName("Search")
    var movies: List<Movie> = emptyList(),

    /**
     * Error returned from API
     */
    @SerialName("Error")
    val error: String = "",

    /**
     * Fields used in case of one movie returned from API
     */
    @SerialName("imdbID")
    val id: String = "",
    @SerialName("Title")
    val title: String = "",
    @SerialName("Year")
    val year: String = "",
    @SerialName("Poster")
    val poster: String = ""
)