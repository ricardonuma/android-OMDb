package com.ricardonuma.android_omdb.data.remote

import com.ricardonuma.android_omdb.data.model.SearchResponse
import com.ricardonuma.android_omdb.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {
    /**
     * OMDb search movies API
     */
    @GET(".")
    suspend fun getMovies(
        @Query("i") id: String = Constants.IMDb_ID,
        @Query("apikey") apikey: String = Constants.OMDb_API_KEY,
        @Query("s") search: String
    ): SearchResponse
}