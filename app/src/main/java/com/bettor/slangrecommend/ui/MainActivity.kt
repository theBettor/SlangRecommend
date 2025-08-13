package com.bettor.slangrecommend.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bettor.slangrecommend.R
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[WordViewModel::class.java]

        val textView = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val button = findViewById<Button>(R.id.button)

        viewModel.words.observe(this) { definitions ->
            if (definitions.isNotEmpty()) {
                val first = definitions[0]
                textView.text = "📌 ${first.word}"
                textView2.text = "뜻📝\n${first.definition}\n\n예시💬\n${first.example}"
            } else {
                textView.text = "No results found."
            }
        }

        button.setOnClickListener {
            viewModel.fetchRandomWord()
        }

        textView.setOnClickListener {
            val currentWords = viewModel.words.value
            if (!currentWords.isNullOrEmpty()) {
                val firstItem = currentWords[0]

                // ViewModel 내부에서 코루틴 처리됨 (viewModelScope)
                viewModel.toggleTranslation(firstItem) {
                    // 번역 토글 후 UI 갱신
                    if (firstItem.isTranslatedShown && firstItem.translated != null) {
                        textView2.text = "뜻📝\n${firstItem.translated}\n\n예시💬\n${firstItem.example}"
                    } else {
                        textView2.text = "뜻📝\n${firstItem.definition}\n\n예시💬\n${firstItem.example}"
                    }
                }
            }
        }
    }
}