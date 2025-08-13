package com.bettor.slangrecommend.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
object RetrofitInstance {
    val api: UrbanDictionaryApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.urbandictionary.com/v0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UrbanDictionaryApi::class.java)
    }

    private const val GOOGLE_BASE_URL = "https://translation.googleapis.com/"

    // 로깅 인터셉터 생성
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Google Translate용 OkHttpClient
    private val googleClient = OkHttpClient.Builder()
        .addInterceptor(logging) // 로그 찍기
        .build()

    val googleTranslateApi: GoogleTranslateApi by lazy {
        Retrofit.Builder()
            .baseUrl(GOOGLE_BASE_URL)
            .client(googleClient) // 여기서 로깅 적용
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleTranslateApi::class.java)
    }
}