package com.laba.calculator

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly

class NumeralSystems : AppCompatActivity() {
    enum class Radix {
        Decimal, Binary, Hexadecimal
    }

    private val hexValues = "ABCDEF"

    private var radix: Radix = Radix.Decimal

    private lateinit var decLinear: LinearLayout
    private lateinit var binLinear: LinearLayout
    private lateinit var hexLinear: LinearLayout

    private lateinit var decInput: TextView
    private lateinit var binInput: TextView
    private lateinit var hexInput: TextView

    private lateinit var buttons: Array<Button>

    override fun onStart() {
        super.onStart()

        decLinear = findViewById(R.id.decimalLinearLayout)
        binLinear = findViewById(R.id.binaryLinearLayout)
        hexLinear = findViewById(R.id.hexadecimalLinearLayout)

        decInput = findViewById(R.id.decimalTextView)
        binInput = findViewById(R.id.binaryTextView)
        hexInput = findViewById(R.id.hexadecimalTextView)

        buttons =
            arrayOf(
                findViewById(R.id.num0),
                findViewById(R.id.num1),
                findViewById(R.id.num2),
                findViewById(R.id.num3),
                findViewById(R.id.num4),
                findViewById(R.id.num5),
                findViewById(R.id.num6),
                findViewById(R.id.num7),
                findViewById(R.id.num8),
                findViewById(R.id.num9),
                findViewById(R.id.numA),
                findViewById(R.id.numB),
                findViewById(R.id.numC),
                findViewById(R.id.numD),
                findViewById(R.id.numE),
                findViewById(R.id.numF)
            )

        decLinear.setOnClickListener {
            changeRadix(Radix.Decimal)
        }
        binLinear.setOnClickListener {
            changeRadix(Radix.Binary)
        }
        hexLinear.setOnClickListener {
            changeRadix(Radix.Hexadecimal)
        }

        buttons.forEach {
            val t = it.text.toString()
            if (t.isDigitsOnly()) it.setOnClickListener { appendNumber(t.toInt()) }
            else it.setOnClickListener { appendNumber(t.toInt(16)) }
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            decInput.text = "0"; binInput.text = "0"; hexInput.text = "0"
        }

        findViewById<Button>(R.id.buttonBackspace).setOnClickListener {
            backspace()
        }

        changeRadix(Radix.Decimal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numeral_systems)
    }

    private fun changeRadix(radix: Radix) {
        this.radix = radix
        when (radix) {
            Radix.Decimal -> {
                decLinear.setBackgroundResource(R.color.neomorphism_blue)
                binLinear.setBackgroundColor(0)
                hexLinear.setBackgroundColor(0)
                for (i in 2..9) {
                    buttons[i].isEnabled = true
                    buttons[i].setTextColor(Color.WHITE)
                }
                for (i in 10..15) {
                    buttons[i].isEnabled = false
                    buttons[i].setTextColor(Color.TRANSPARENT)
                }
            }

            Radix.Binary -> {
                binLinear.setBackgroundResource(R.color.neomorphism_blue)
                decLinear.setBackgroundColor(0)
                hexLinear.setBackgroundColor(0)
                for (i in 2..15) {
                    buttons[i].isEnabled = false
                    buttons[i].setTextColor(Color.TRANSPARENT)
                }
            }
            Radix.Hexadecimal -> {
                hexLinear.setBackgroundResource(R.color.neomorphism_blue)
                binLinear.setBackgroundColor(0)
                decLinear.setBackgroundColor(0)
                for (b: Button in buttons) {
                    b.isEnabled = true
                    b.setTextColor(Color.WHITE)
                }

            }
        }
    }

    private fun backspace() {
        when (radix) {
            Radix.Decimal -> {
                if (decInput.text.length == 1)
                    decInput.text = "0"
                else
                    decInput.text = decInput.text.substring(0, decInput.text.length - 1)
            }
            Radix.Binary -> {
                if (binInput.text.length == 1)
                    binInput.text = "0"
                else
                    binInput.text = binInput.text.substring(0, binInput.text.length - 1)
            }
            Radix.Hexadecimal -> {
                if (hexInput.text.length == 1)
                    hexInput.text = "0"
                else
                    hexInput.text = hexInput.text.substring(0, hexInput.text.length - 1)
            }
        }

        appendNumber(-1)
    }

    private fun appendNumber(n: Int) {
        var binStringCleared = binInput.text.replace(Regex(" "), "")
            when (radix) {
                Radix.Decimal -> {
                    if (n != -1 && decInput.text.length < 9) {
                        if (decInput.text.toString() == "0")
                            decInput.text = n.toString()
                        else
                            decInput.append(n.toString())
                    }

                    val decNumber = decInput.text.toString().toInt()
                    binStringCleared = Integer.toBinaryString(decNumber)
                    hexInput.text = Integer.toHexString(decNumber)
                }
                Radix.Binary -> {
                    if (n != -1 && decInput.text.length < 9) {
                        if (binStringCleared == "0")
                            binStringCleared = n.toString()
                        else {
                            binStringCleared += n.toString()
                        }
                    }

                    val decNumber = Integer.parseInt(binStringCleared, 2)
                    decInput.text = decNumber.toString()
                    hexInput.text = Integer.toHexString(decNumber)
                }
                Radix.Hexadecimal -> {
                    if (n != -1 && decInput.text.length < 9) {
                        if (n >= 10)
                            if (hexInput.text.toString() == "0")
                                hexInput.text = hexValues[n - 10].toString()
                            else
                                hexInput.append(hexValues[n - 10].toString())
                        else
                            if (hexInput.text.toString() == "0")
                                hexInput.text = n.toString()
                            else
                                hexInput.append(n.toString())
                    }

                    val decNumber = Integer.parseInt(hexInput.text.toString(), 16)
                    decInput.text = decNumber.toString()
                    binStringCleared = Integer.toBinaryString(decNumber)
                }
            }

            binInput.text = binStringCleared.chunked(4).joinToString(" ")
    }
}
