package com.ricardonuma.android_omdb.data.repository

import com.ricardonuma.android_omdb.data.RetrofitFactory
import com.ricardonuma.android_omdb.data.model.SearchResponse
import com.ricardonuma.android_omdb.data.remote.OmdbApiService
import com.ricardonuma.android_omdb.utils.Constants
import retrofit2.Retrofit

class SearchRepository {
    private val retrofit: Retrofit = RetrofitFactory.createRetrofit(Constants.BASE_URL)
    private val omdbApiService = retrofit.create(OmdbApiService::class.java)

    /**
     * Call getMovies from OmdbApiService and return SearchResponse
     */
    suspend fun getMovies(search: String): SearchResponse {
        return omdbApiService.getMovies(search = search)
    }
}