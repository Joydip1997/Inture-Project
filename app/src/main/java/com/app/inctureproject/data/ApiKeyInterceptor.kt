package com.app.inctureproject.data


import com.app.inctureproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val modifiedRequest: Request = originalRequest.newBuilder()
            .header("X-RapidAPI-Key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(modifiedRequest)
    }
}