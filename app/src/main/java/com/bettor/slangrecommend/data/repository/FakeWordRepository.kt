package com.bettor.slangrecommend.data.repository

import com.bettor.slangrecommend.data.model.Urban

// test/fake/FakeWordRepository.kt
class FakeWordRepository : WordRepository() {
    override suspend fun getRandomUrban(): List<Urban> {
        return listOf(
            Urban(
                word = "mouse around",
                definition = "Verb: the act of [fucking] in [Disneyland] park grounds.",
                example = "Girl: OMG... Guy: Doors open better run!"
            )
        )
    }

    // 필요하다면 번역도 고정 응답
    override suspend fun translateDefAndEx(defEn: String, exEn: String): Pair<String, String> {
        return "디즈니랜드에서 하는 행위" to "소녀: 오마이갓... 남자: 빨리 도망가!"
    }
}