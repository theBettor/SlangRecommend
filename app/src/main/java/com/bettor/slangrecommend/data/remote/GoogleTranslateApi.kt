package com.bettor.slangrecommend.data.remote

import retrofit2.Call
import com.bettor.slangrecommend.data.model.TranslateResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleTranslateApi {
    @FormUrlEncoded
    @POST("language/translate/v2")
    fun translate(
        @Query("key") apiKey: String,
        @Field("q") text: String,
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("format") format: String = "text"
    ): Call<TranslateResponse>
}
