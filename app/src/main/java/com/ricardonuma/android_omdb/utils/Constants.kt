package com.ricardonuma.android_omdb.utils

import com.ricardonuma.android_omdb.BuildConfig

class Constants {
    companion object {
        const val BASE_URL = "https://www.omdbapi.com"
        const val IMDb_ID = BuildConfig.IMDb_ID
        const val OMDb_API_KEY = BuildConfig.OMDb_API_KEY
        const val MAX_SEARCH_CHAR = 30
    }
}