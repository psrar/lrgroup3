package com.laba.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NumeralSystems : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numeral_systems)

        for (i in 0..10) {
            var button = findViewById<Button>(R.id.calcButton0);
        }
    }
}