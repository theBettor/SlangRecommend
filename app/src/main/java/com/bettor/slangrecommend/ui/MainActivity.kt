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

        val wordTextView = findViewById<TextView>(R.id.wordTextView)
        val meaningTextView = findViewById<TextView>(R.id.meaningTextView)
        val btn = findViewById<Button>(R.id.button)

        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        viewModel.wordLiveData.observe(this) { word ->
            wordTextView.text = word.word
            meaningTextView.text = word.meaning
        }

        btn.setOnClickListener {
            viewModel.fetchRandomWord()
        }
    }
}