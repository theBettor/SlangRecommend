package com.bettor.slangrecommend.data.repository

import com.bettor.slangrecommend.data.UrbanMapper
import com.bettor.slangrecommend.data.model.UdTextFormatter
import com.bettor.slangrecommend.data.model.Urban
import com.bettor.slangrecommend.data.remote.RetrofitInstance


open class WordRepository {

    private val urbanApi = RetrofitInstance.urbanDictionaryApi
    private val googleApi = RetrofitInstance.googleTranslateApi
    private val apiKey = com.bettor.slangrecommend.BuildConfig.GOOGLE_TRANSLATE_API_KEY

//    suspend fun getRandomUrban(): List<Urban> {
//        val dtoList = urbanApi.getRandomDefinitions().list  // List<UrbanDefinitionDto>
//        return dtoList.map { UrbanMapper.fromDto(it) }      // List<Urban>
//    }
//
//    suspend fun translateDefAndEx(defEn: String, exEn: String): Pair<String, String> {
//        // 번역 전엔 괄호만 제거해 번역 품질 향상 (이미 정규화됐으면 생략 가능)
//        val res = googleApi.translateBatch(
//            apiKey = apiKey,
//            q = listOf(defEn, exEn),
//            target = "ko",
//            source = "en",
//            format = "text"
//        )
//        val out = res.data.translations.map { it.translatedText }
//        val defKo = UdTextFormatter.normalizeForDisplay(out.getOrNull(0) ?: "")
//        val exKo  = UdTextFormatter.normalizeForDisplay(out.getOrNull(1) ?: "")
//        return defKo to exKo
//    }
    open suspend fun getRandomUrban(): List<Urban> {
        TODO("Not yet implemented")
    }

    open suspend fun translateDefAndEx(defEn: String, exEn: String): Pair<String, String> {
        TODO("Not yet implemented")
    }
}