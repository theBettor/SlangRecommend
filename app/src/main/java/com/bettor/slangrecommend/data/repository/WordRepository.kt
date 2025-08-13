package com.bettor.slangrecommend.data.repository

import android.util.Log
import com.bettor.slangrecommend.BuildConfig
import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.remote.RetrofitInstance
import com.bettor.slangrecommend.data.remote.RetrofitInstance.api
import com.bettor.slangrecommend.data.remote.RetrofitInstance.googleTranslateApi
import retrofit2.HttpException

class WordRepository {

    private val api = RetrofitInstance.api
    private val googleTranslateApi = RetrofitInstance.googleTranslateApi
    private val googleApiKey = BuildConfig.GOOGLE_TRANSLATE_API_KEY


    suspend fun getRandomSlangDefinition(): List<UrbanDefinition> {
        return api.getRandomDefinitions().list
    }

    // 이름은 유지하고 내부만 Google Translate로 대체
    suspend fun translateWithGoogle(text: String): String {
        return try {
            val res = googleTranslateApi.translate(
                apiKey = googleApiKey,
                q = text,
                target = "ko",
                source = "en",
                format = "text"
            )
            res.data.translations.firstOrNull()?.translatedText ?: ""
        } catch (e: HttpException) {
            val errBody = e.response()?.errorBody()?.string()
            Log.e("GoogleTranslate", "HTTP ${e.code()} ${e.message()} body=$errBody")
            ""
        }
    }
}