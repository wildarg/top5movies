package com.example.top5movies.data.tmdb.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val token: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return request.newBuilder()
            .url(
                request.url.newBuilder()
                    .addQueryParameter(API_KEY_PARAMETER, token)
                    .build()
            )
            .build()
            .let(chain::proceed)
    }

}

private const val API_KEY_PARAMETER = "api_key"