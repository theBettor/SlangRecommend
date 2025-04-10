package com.bettor.slangrecommend.data.repository

import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.remote.RetrofitInstance

class WordRepository {

    suspend fun getSlangDefinition(term: String): List<UrbanDefinition> {
        return try {
            val response = RetrofitInstance.api.defineWord(term)
            response.list
        } catch (e: Exception) {
            emptyList()
        }
    }
}