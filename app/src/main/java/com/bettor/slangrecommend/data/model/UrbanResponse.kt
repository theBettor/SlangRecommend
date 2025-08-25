package com.bettor.slangrecommend.data.model

data class UrbanResponse(
    val list: List<UrbanDefinition>
)

data class UrbanDefinition(
    val word: String,
    val definition: String,
    val example: String,
    var translatedDefinition: String? = null,
    var translatedExample: String? = null,
    var isTranslatedShown: Boolean = false
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