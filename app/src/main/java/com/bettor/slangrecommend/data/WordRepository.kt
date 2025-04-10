package com.bettor.slangrecommend.data

import com.bettor.slangrecommend.model.Word

class WordRepository {

    private val wordList = listOf(
        Word("abandon", "to give up completely"),
        Word("benevolent", "well-meaning and kindly"),
        Word("candid", "truthful and straightforward")
    )

    fun getRandomWord(): Word {
        return wordList.random()
    }
}