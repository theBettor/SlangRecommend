package com.bettor.slangrecommend.data.model

data class Urban(
    val word: String,
    val definition: String, // 정규화된 영어 뜻
    val example: String     // 정규화된 영어 예시
)