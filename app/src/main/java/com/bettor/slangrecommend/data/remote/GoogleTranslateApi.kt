package com.bettor.slangrecommend.data.remote

import com.bettor.slangrecommend.data.model.GoogleTranslateResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleTranslateApi {
    // q는 여러 번 보낼 수 있지만 간단히 하나만
    @FormUrlEncoded
    @POST("language/translate/v2")
    suspend fun translate(
        @Query("key") apiKey: String,
        @Field("q") q: String,
        @Field("target") target: String,
        @Field("source") source: String? = null,
        @Field("format") format: String = "text"
    ): GoogleTranslateResponse
}

