package com.laba.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NumeralSystems : AppCompatActivity() {
    override fun onStart() {
        super.onStart()

        var b = findViewById<Button>(R.id.calcButton0)
        b.setOnClickListener {
            Toast.makeText(
                this@NumeralSystems,
                "it.toString()",
                Toast.LENGTH_SHORT
            ).show()
            b = findViewById<Button>(R.id.calcButton1)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    "1",
                    Toast.LENGTH_SHORT
                ).show()

            }
            b = findViewById<Button>(R.id.calcButton2)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            b = findViewById<Button>(R.id.calcButton3)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }
            b = findViewById<Button>(R.id.calcButton4)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()

            }
            b = findViewById<Button>(R.id.calcButton5)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            b = findViewById<Button>(R.id.calcButton6)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            b = findViewById<Button>(R.id.calcButton7)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            b = findViewById<Button>(R.id.calcButton8)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    it.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            b = findViewById<Button>(R.id.calcButton9)
            b.setOnClickListener {
                Toast.makeText(
                    this,
                    0,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numeral_systems)


    }

}