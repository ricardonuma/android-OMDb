package com.ricardonuma.android_omdb.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("imdbID")
    var id: String = "",
    @SerialName("Title")
    var title: String = "",
    @SerialName("Year")
    var year: String = "",
    @SerialName("Poster")
    var poster: String = ""
) {
    /**
     * Mock object for Compose preview
     */
    fun getPreviewMovie(
        sampleNumber: String = "1",
        year: String = "2024",
        poster: String = "https://m.media-amazon.com/images/M/MV5BNjM0NTc0NzItM2FlYS00YzEwLWE0YmUtNTA2ZWIzODc2OTgxXkEyXkFqcGdeQXVyNTgwNzIyNzg@._V1_SX300.jpg"
    ): Movie {
        this.id = sampleNumber
        this.title = "Title$sampleNumber"
        this.year = year
        this.poster = poster
        return this
    }
}