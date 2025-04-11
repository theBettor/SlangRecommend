package com.bettor.slangrecommend.data.repository

import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.remote.RetrofitInstance
import com.bettor.slangrecommend.data.remote.RetrofitInstance.api

class WordRepository {

    suspend fun getRandomSlangDefinition(): List<UrbanDefinition> {
        return api.getRandomDefinitions().list
    }
}