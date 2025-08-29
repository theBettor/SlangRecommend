package com.bettor.slangrecommend.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bettor.slangrecommend.BuildConfig
import com.bettor.slangrecommend.R
import com.bettor.slangrecommend.data.repository.FakeWordRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("CHECK", "BuildConfig.GOOGLE_TRANSLATE_API_KEY(len)=" +
                BuildConfig.GOOGLE_TRANSLATE_API_KEY.length)

        val fakeRepo = FakeWordRepository()
        val factory = WordViewModelFactory(fakeRepo)

        viewModel = ViewModelProvider(this, factory)[WordViewModel::class.java]


//        viewModel = ViewModelProvider(this)[WordViewModel::class.java]

        val textView = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val button = findViewById<Button>(R.id.button)

        viewModel.item.observe(this) { ui ->
            if (ui == null) return@observe
            textView.text = "📌 ${ui.word}"
            if (ui.isTranslatedShown && ui.defKo != null) {
                textView2.text = "뜻📝\n${ui.defKo}\n\n예시💬\n${ui.exKo ?: ""}"
            } else {
                textView2.text = "뜻📝\n${ui.defEn}\n\n예시💬\n${ui.exEn}"
            }
        }

        button.setOnClickListener {
            viewModel.fetchRandom()
        }
        textView.setOnClickListener {
            viewModel.toggleTranslation()
        }
    }
}