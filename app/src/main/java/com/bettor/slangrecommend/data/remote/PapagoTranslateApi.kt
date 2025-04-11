package com.bettor.slangrecommend.data.remote

import com.bettor.slangrecommend.data.model.PapagoResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface PapagoTranslateApi {
    @FormUrlEncoded
    @POST("v1/papago/n2mt")
    @Headers(
//        "X-Naver-Client-Id: ${BuildConfig.NAVER_CLIENT_ID}",
//        "X-Naver-Client-Secret: ${BuildConfig.NAVER_CLIENT_SECRET}"
    )
    suspend fun translate(
        @Field("source") source: String,
        @Field("target") target: String,
        @Field("text") text: String
    ): PapagoResponse
}