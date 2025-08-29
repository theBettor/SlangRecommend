package com.bettor.slangrecommend.ui

import com.bettor.slangrecommend.data.model.Urban
import com.bettor.slangrecommend.data.model.UrbanDisplay

object UrbanDisplayMapper {
    fun fromDomain(u: Urban): UrbanDisplay = UrbanDisplay(
        word = u.word,
        defEn = u.definition,
        exEn = u.example
    )

    fun withTranslation(base: UrbanDisplay, defKo: String, exKo: String): UrbanDisplay =
        base.copy(defKo = defKo, exKo = exKo)
}