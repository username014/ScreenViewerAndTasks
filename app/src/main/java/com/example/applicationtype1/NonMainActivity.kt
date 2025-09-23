package com.example.applicationtype1

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val buttonBack = findViewById<Button>(R.id.button)
        buttonBack.setOnClickListener {
            finish()
        }
    }
}