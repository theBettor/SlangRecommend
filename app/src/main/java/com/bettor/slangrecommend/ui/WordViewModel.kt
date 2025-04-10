package com.bettor.slangrecommend.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bettor.slangrecommend.data.WordRepository
import com.bettor.slangrecommend.model.Word

class WordViewModel : ViewModel() {

    private val repository = WordRepository()

    private val _wordLiveData = MutableLiveData<Word>()
    val wordLiveData: LiveData<Word> = _wordLiveData

    fun fetchRandomWord() {
        _wordLiveData.value = repository.getRandomWord()
    }
}