package com.bettor.slangrecommend.data.repository

import android.util.Log
import com.bettor.slangrecommend.BuildConfig
import com.bettor.slangrecommend.data.model.TranslateResponse
import com.bettor.slangrecommend.data.model.UrbanDefinition
import com.bettor.slangrecommend.data.remote.RetrofitInstance
import com.bettor.slangrecommend.data.remote.RetrofitInstance.urbanDictApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordRepository {

    suspend fun getRandomSlangDefinition(): List<UrbanDefinition> {
        return urbanDictApi.getRandomDefinitions().list
    }

    suspend fun translateWord(text: String): String? = withContext(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.googleTransApi.translate(
                apiKey = BuildConfig.GOOGLE_API_KEY,
                text = text,
                source = "en",
                target = "ko"
            ).execute()
            Log.d("TranslateDebug", "API Key: ${BuildConfig.GOOGLE_API_KEY}")
            Log.d("TranslateDebug", "Response code: ${response.code()}")
            Log.d("TranslateDebug", "Response body: ${response.body()}")
            Log.d("TranslateDebug", "Error body: ${response.errorBody()?.string()}")

            return@withContext response.body()?.data?.translations?.firstOrNull()?.translatedText
        } catch (e: Exception) {
            return@withContext "번역 실패: ${e.message}"
        }


    }
}