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
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("CHECK", "BuildConfig.GOOGLE_TRANSLATE_API_KEY(len)=" +
                BuildConfig.GOOGLE_TRANSLATE_API_KEY.length)

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
                viewModel.toggleTranslation(firstItem) {
                    val (defText, exText) =
                        if (firstItem.isTranslatedShown) {
                            (firstItem.translatedDefinition ?: firstItem.definition) to
                                    (firstItem.translatedExample   ?: firstItem.example)
                        } else {
                            firstItem.definition to firstItem.example
                        }

                    textView2.text = "뜻📝\n$defText\n\n예시💬\n$exText"
                }
            }
        }
    }
}