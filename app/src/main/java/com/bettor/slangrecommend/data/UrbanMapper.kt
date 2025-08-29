package com.bettor.slangrecommend.data

import com.bettor.slangrecommend.data.model.UdTextFormatter
import com.bettor.slangrecommend.data.model.Urban
import com.bettor.slangrecommend.data.model.dto.UrbanDefinitionDto

object UrbanMapper {
    fun fromDto(dto: UrbanDefinitionDto): Urban = Urban(
        word = dto.word,
        definition = UdTextFormatter.normalizeForDisplay(dto.definition),
        example = UdTextFormatter.normalizeForDisplay(dto.example)
    )
}