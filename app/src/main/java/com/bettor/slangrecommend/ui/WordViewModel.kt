package com.bettor.slangrecommend.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            if (item.translatedDefinition == null || item.translatedExample == null) {
                val (defKo, exKo) = repository.translateDefinitionAndExample(
                    item.definition, item.example
                )
                item.translatedDefinition = defKo
                item.translatedExample = exKo
            }
            item.isTranslatedShown = !item.isTranslatedShown

            // 옵저버를 다시 태우고 싶으면 이 줄도 추가 (리스트 참조 유지 시)
            _words.value = _words.value

            onUpdate()
        }
    }
}