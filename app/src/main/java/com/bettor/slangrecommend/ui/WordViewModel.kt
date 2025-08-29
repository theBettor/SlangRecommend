package com.bettor.slangrecommend.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bettor.slangrecommend.data.model.UrbanDisplay
import com.bettor.slangrecommend.data.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel : ViewModel() {
    private val repo = WordRepository()

    private val _item = MutableLiveData<UrbanDisplay?>()
    val item: LiveData<UrbanDisplay?> = _item

    fun fetchRandom() = viewModelScope.launch {
        val list = repo.getRandomUrban()
        val first = list.firstOrNull() ?: return@launch
        _item.value = UrbanDisplayMapper.fromDomain(first)
    }

    fun toggleTranslation() = viewModelScope.launch {
        val cur = _item.value ?: return@launch
        if (cur.defKo == null || cur.exKo == null) {
            val (defKo, exKo) = repo.translateDefAndEx(cur.defEn, cur.exEn)
            _item.value =
                cur.copy(defKo = defKo, exKo = exKo, isTranslatedShown = !cur.isTranslatedShown)
        } else {
            _item.value = cur.copy(isTranslatedShown = !cur.isTranslatedShown)
        }
    }
}