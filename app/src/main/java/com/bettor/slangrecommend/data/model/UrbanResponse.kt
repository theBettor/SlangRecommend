package com.bettor.slangrecommend.data.model

data class UrbanResponse(
    val list: List<UrbanDefinition>
)

data class UrbanDefinition(
    val word: String,
    val definition: String,
    val example: String
)