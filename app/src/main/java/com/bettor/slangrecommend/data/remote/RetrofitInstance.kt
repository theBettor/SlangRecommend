package com.bettor.slangrecommend.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: UrbanDictionaryApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.urbandictionary.com/v0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UrbanDictionaryApi::class.java)
    }

    private const val BASE_URL = "https://openapi.naver.com/"

    val papagoApi: PapagoTranslateApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PapagoTranslateApi::class.java)
    }
}