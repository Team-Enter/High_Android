package com.enter.high_v1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenApiProvider {
    private var instance: Retrofit? = null
    private val OPEN_API_URL = "www.career.go.kr/inspct/openapi/test"

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                    .baseUrl(OPEN_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return instance!!
    }
}