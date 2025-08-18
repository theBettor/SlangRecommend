package com.bettor.slangrecommend.data.remote

import com.bettor.slangrecommend.data.model.GoogleTranslateResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleTranslateApi {
    @FormUrlEncoded
    @POST("language/translate/v2")
    suspend fun translateBatch(
        @Query("key") apiKey: String,
        @Field("q") q: List<String>,
        @Field("target") target: String,
        @Field("source") source: String? = null,
        @Field("format") format: String = "text"
    ): GoogleTranslateResponse
}

