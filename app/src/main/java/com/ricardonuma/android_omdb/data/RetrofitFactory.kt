package com.ricardonuma.android_omdb.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitFactory {
    private val json = Json { ignoreUnknownKeys = true }

    /**
     * Create Retrofit for API calls
     */
    fun createRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(url)
            .build()
    }
}