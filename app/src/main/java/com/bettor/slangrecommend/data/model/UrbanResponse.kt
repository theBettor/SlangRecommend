package com.bettor.slangrecommend.data.model

data class UrbanResponse(
    val list: List<UrbanDefinition>
)

data class UrbanDefinition(
    val word: String,
    val definition: String,
    val example: String,
    var translated: String? = null // 초기엔 null
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