package com.bettor.slangrecommend.data.remote

import com.bettor.slangrecommend.data.model.dto.UrbanResponseDto
import retrofit2.http.GET

interface UrbanDictionaryApi {
    @GET("random")
    suspend fun getRandomDefinitions(): UrbanResponseDto
}