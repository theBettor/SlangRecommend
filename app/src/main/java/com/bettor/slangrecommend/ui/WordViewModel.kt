package com.bettor.slangrecommend.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel : ViewModel() {
    private val repository = WordRepository()

    private val _words = MutableLiveData<List<UrbanDefinition>>()
    val words: LiveData<List<UrbanDefinition>> = _words

    fun fetchWord(term: String) {
        viewModelScope.launch {
            val definitions = repository.getSlangDefinition(term)
            _words.value = definitions
        }
    }
}