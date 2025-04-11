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

data class PapagoResponse(
    val message: Message
)

data class Message(
    val result: Result
)

data class Result(
    val translatedText: String
)