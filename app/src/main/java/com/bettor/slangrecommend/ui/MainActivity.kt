package com.bettor.slangrecommend.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bettor.slangrecommend.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[WordViewModel::class.java]

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)

        viewModel.words.observe(this) { definitions ->
            if (definitions.isNotEmpty()) {
                val first = definitions[0]
                textView.text = "📌 ${first.word}\n\n📝 ${first.definition}\n\n💬 ${first.example}"
            } else {
                textView.text = "No results found."
            }
        }

        button.setOnClickListener {
            viewModel.fetchWord("rizz") // ← 여기에 슬랭을 바꿔서 시도 가능!
        }
    }
}