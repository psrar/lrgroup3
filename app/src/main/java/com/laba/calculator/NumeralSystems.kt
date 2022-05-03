package com.laba.calculator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NumeralSystems : AppCompatActivity() {
    override fun onStart() {
        super.onStart()

        var decLinear = findViewById<LinearLayout>(R.id.decimalLinearLayout);
        var binLinear = findViewById<LinearLayout>(R.id.binaryLinearLayout);
        var hexLinear = findViewById<LinearLayout>(R.id.hexademicalLinearLayout);

        decLinear.setOnClickListener {
            decLinear.setBackgroundResource(R.color.neomorphism_blue);
            binLinear.setBackgroundColor(0);
            hexLinear.setBackgroundColor(0);
        }
        binLinear.setOnClickListener {
            binLinear.setBackgroundResource(R.color.neomorphism_blue);
            decLinear.setBackgroundColor(0);
            hexLinear.setBackgroundColor(0);
        }
        hexLinear.setOnClickListener {
            hexLinear.setBackgroundResource(R.color.neomorphism_blue.toInt());
            binLinear.setBackgroundColor(0);
            decLinear.setBackgroundColor(0);
        }

        var b = findViewById<Button>(R.id.num0)
        b.setOnClickListener {
            Toast.makeText(
                this@NumeralSystems,
                "it.toString()",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numeral_systems)


    }

}