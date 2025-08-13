package com.bettor.slangrecommend.data.model

data class UrbanResponse(
    val list: List<UrbanDefinition>
)

data class UrbanDefinition(
    val word: String,
    val definition: String,
    val example: String,
    var translated: String? = null, // 초기엔 null
    var isTranslatedShown: Boolean = false // 현재 어떤 언어가 보이는지
)

data class GoogleTranslateResponse(
    val data: Data
) {
    data class Data(
        val translations: List<Translation>
    )
    data class Translation(
        val translatedText: String,
        val detectedSourceLanguage: String? = null
    )
}