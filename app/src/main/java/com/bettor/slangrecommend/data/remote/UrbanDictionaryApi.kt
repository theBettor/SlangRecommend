package com.bettor.slangrecommend.data.remote

import com.bettor.slangrecommend.data.model.UrbanResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UrbanDictionaryApi {
    @GET("define")
    suspend fun defineWord(@Query("term") term: String): UrbanResponse
}