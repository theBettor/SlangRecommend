package com.bettor.slangrecommend.data.model

data class UrbanDisplay(
    val word: String,
    val defEn: String,
    val exEn: String,
    val defKo: String? = null,
    val exKo: String? = null,
    val isTranslatedShown: Boolean = false
)