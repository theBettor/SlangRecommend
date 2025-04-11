package com.bettor.slangrecommend.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bettor.slangrecommend.BuildConfig
import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.remote.RetrofitInstance
import com.bettor.slangrecommend.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel : ViewModel() {
    private val repository = WordRepository()

    private val _words = MutableLiveData<List<UrbanDefinition>>()
    val words: LiveData<List<UrbanDefinition>> = _words

    fun fetchRandomWord() {
        viewModelScope.launch {
            val definitions = repository.getRandomSlangDefinition()
            _words.value = definitions
        }
    }

    fun toggleTranslation(item: UrbanDefinition, onUpdate: () -> Unit) {
        viewModelScope.launch {
            if (item.translated == null) {
                val result = repository.translateWord(item.definition)
                item.translated = result ?: "번역 실패"
            }

            item.isTranslatedShown = !item.isTranslatedShown
            onUpdate()
        }
    }
}