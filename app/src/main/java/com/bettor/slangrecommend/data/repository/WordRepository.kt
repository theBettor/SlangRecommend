package com.bettor.slangrecommend.data.repository

import com.bettor.slangrecommend.BuildConfig
import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.remote.RetrofitInstance


class WordRepository {



    private val api = RetrofitInstance.api
    private val googleTranslateApi = RetrofitInstance.googleTranslateApi
    private val googleApiKey = com.bettor.slangrecommend.BuildConfig.GOOGLE_TRANSLATE_API_KEY


    suspend fun getRandomSlangDefinition(): List<UrbanDefinition> {
        return api.getRandomDefinitions().list
    }

    // 이름은 유지하고 내부만 Google Translate로 대체
    // definition + example 한 번에 번역
    suspend fun translateDefinitionAndExample(
        definition: String,
        example: String
    ): Pair<String, String> {
        val res = googleTranslateApi.translateBatch(
            apiKey = googleApiKey,
            q = listOf(definition, example),
            target = "ko",
            source = "en"
        )
        val out = res.data.translations.map { it.translatedText }
        val defKo = out.getOrNull(0) ?: ""
        val exKo = out.getOrNull(1) ?: ""
        return defKo to exKo
    }
}