package com.bettor.slangrecommend.data.repository

import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.remote.RetrofitInstance
import com.bettor.slangrecommend.data.remote.RetrofitInstance.api
import com.bettor.slangrecommend.data.remote.RetrofitInstance.papagoApi

class WordRepository {

    suspend fun getRandomSlangDefinition(): List<UrbanDefinition> {
        return api.getRandomDefinitions().list
    }

    suspend fun translateWithPapago(text: String): String {
        val response = papagoApi.translate(
            source = "en", target = "ko", text = text
        )
        return response.message.result.translatedText
    }
}