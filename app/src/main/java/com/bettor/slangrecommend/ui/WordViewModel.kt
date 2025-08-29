package com.bettor.slangrecommend.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bettor.slangrecommend.data.model.UrbanDisplay
import com.bettor.slangrecommend.data.model.dto.UrbanDefinitionDto
import com.bettor.slangrecommend.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(
    private val repo: WordRepository = WordRepository() // 기본은 실제 Repo
) : ViewModel() {

    private val _item = MutableLiveData<UrbanDisplay?>()
    val item: LiveData<UrbanDisplay?> = _item

    fun fetchRandom() = viewModelScope.launch {
        val list = repo.getRandomUrban()
        _item.value = UrbanDisplayMapper.fromDomain(list.first())
    }

    fun toggleTranslation() = viewModelScope.launch {
        val cur = _item.value ?: return@launch
        if (cur.defKo == null || cur.exKo == null) {
            val (defKo, exKo) = repo.translateDefAndEx(cur.defEn, cur.exEn)
            _item.value = cur.copy(defKo = defKo, exKo = exKo, isTranslatedShown = !cur.isTranslatedShown)
        } else {
            _item.value = cur.copy(isTranslatedShown = !cur.isTranslatedShown)
        }
    }
}

class WordViewModelFactory(
    private val repo: WordRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
//class WordViewModel : ViewModel() {
//    private val repo = WordRepository()
//
//    private val _item = MutableLiveData<UrbanDisplay?>()
//    val item: LiveData<UrbanDisplay?> = _item
//
//    fun fetchRandom() = viewModelScope.launch {
//        val list = repo.getRandomUrban()
//        val first = list.firstOrNull() ?: return@launch
//        _item.value = UrbanDisplayMapper.fromDomain(first)
//    }
//
//    fun toggleTranslation() = viewModelScope.launch {
//        val cur = _item.value ?: return@launch
//        if (cur.defKo == null || cur.exKo == null) {
//            val (defKo, exKo) = repo.translateDefAndEx(cur.defEn, cur.exEn)
//            _item.value = cur.copy(defKo = defKo, exKo = exKo, isTranslatedShown = !cur.isTranslatedShown)
//        } else {
//            _item.value = cur.copy(isTranslatedShown = !cur.isTranslatedShown)
//        }
//    }
//}