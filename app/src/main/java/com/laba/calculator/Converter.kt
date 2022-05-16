package com.laba.calculator

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.min

class Converter : AppCompatActivity() {
    private val currencies = arrayOf("Рубль", "Доллар", "Евро", "Фунт", "14830")
    private val signs = arrayOf(R.string.ruble, R.string.dollar, R.string.euro, R.string.pound, 14830)
//БАГ 2//////////////////////////////////////////////////////////////////////////////////////////////////
    private val rates = arrayOf(0.015, 1.0, 1.05, 1.23, 0.0)

    private lateinit var firstCurrencySelector: TextView
    private lateinit var secondCurrencySelector: TextView

    private lateinit var firstValue: TextView
    private lateinit var secondValue: TextView

    private lateinit var firstCurrencyLinear: LinearLayout
    private lateinit var secondCurrencyLinear: LinearLayout

    private var firstRate = 0
    private var secondRate = 1

    private var selectedCurrency = 0

    private lateinit var buttons: Array<Button>

    override fun onStart() {
        super.onStart()

        firstCurrencySelector = findViewById(R.id.firstCurrencySelector)
        firstValue = findViewById(R.id.firstCurrencyTextView)
        firstCurrencyLinear = findViewById(R.id.firstCurrency)

        secondCurrencySelector = findViewById(R.id.secondCurrencySelector)
        secondValue = findViewById(R.id.secondCurrencyTextView)
        secondCurrencyLinear = findViewById(R.id.secondCurrency)

        firstCurrencySelector.setOnClickListener {
            val mb = AlertDialog.Builder(this)
            mb.setTitle("Выберите валюту")
            mb.setSingleChoiceItems(currencies, firstRate) { dialog, i ->
                run {
                    firstCurrencySelector.setText(signs[i])
                    firstRate = i
                    dialog.cancel()
                }
            }

            mb.setNeutralButton("Отмена") { dialog, _ -> dialog.cancel() }
            mb.create().show()
        }
        secondCurrencySelector.setOnClickListener {
            val mb = AlertDialog.Builder(this)
            mb.setTitle("Выберите валюту")
            mb.setSingleChoiceItems(currencies, secondRate) { dialog, i ->
                run {
                    secondCurrencySelector.setText(signs[i])
                    secondRate = i
                    dialog.cancel()
                }
            }

            mb.setNeutralButton("Отмена") { dialog, _ -> dialog.cancel() }
            mb.create().show()
        }

        buttons =
            arrayOf(
                findViewById(R.id.num0),
                findViewById(R.id.num1),
                findViewById(R.id.num2),
                findViewById(R.id.num3),
                findViewById(R.id.num4),
                findViewById(R.id.num5),
                findViewById(R.id.num6),
//БАГ 3/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                findViewById(R.id.num7),
                findViewById(R.id.num8),
                findViewById(R.id.num9),
                findViewById(R.id.dot)
            )

        buttons.forEach {
            val b = it
            it.setOnClickListener {
                if (b.text.toString() == ".") {
                    appendNumber(10)
                } else {
                    val t = b.text.toString().toInt()
                        appendNumber(t)
                }
            }


        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            firstValue.text = "0"; secondValue.text = "0"
        }

        findViewById<Button>(R.id.buttonBackspace).setOnClickListener {
            backspace()
        }

        firstCurrencyLinear.setOnClickListener {
            selectedCurrency = 0
            firstCurrencyLinear.setBackgroundResource(R.color.neomorphism_blue)
            secondCurrencyLinear.setBackgroundColor(0)
        }
        secondCurrencyLinear.setOnClickListener {
            selectedCurrency = 1
//БАГ 1/////////////////////////////////////////////////////////////////////
            secondCurrencyLinear.setBackgroundResource(R.color.white)
            firstCurrencyLinear.setBackgroundColor(0)
        }

        firstValue.text = "0"; secondValue.text = "0"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
    }

    private fun backspace() {
        when (selectedCurrency) {
            0 -> {
                if (firstValue.text.length == 1) {
                    firstValue.text = "0"
                    secondValue.text = "0"
                }
                else {
                    firstValue.text = firstValue.text.substring(0, firstValue.text.length - 1)
                            val fir = firstValue.text.toString().toDouble() * rates[firstRate] / rates[secondRate]
                            secondValue.text = fir.toString()
                    }
            }
            1 -> {
                if (secondValue.text.length == 1) {
                    secondValue.text = "0"
                    firstValue.text = "0"
                }
                else {
                    secondValue.text = secondValue.text.substring(0, secondValue.text.length - 1)
                    val fir = secondValue.text.toString().toDouble() * rates[firstRate] / rates[secondRate]
                    firstValue.text = fir.toString()
                }
            }
        }

        appendNumber(-1)
    }

    private fun appendNumber(n: Int) {
        when (selectedCurrency) {
            0 ->
                if (n != -1 && firstValue.text.length < 9) {
                    if (n == 10) {
                        if(!firstValue.text.toString().contains(".")) {
                            firstValue.append(".")
                            val sec =
                                firstValue.text.toString().substring(0, firstValue.text.length - 1)
                                    .toDouble() * rates[firstRate] / rates[secondRate]
                            secondValue.text = sec.toString()
                        }
                    } else {
                        if (firstValue.text.toString() == "0")
                            firstValue.text = n.toString()
                        else
                            firstValue.append(n.toString())

                        if(firstValue.text[firstValue.text.length - 1] == '.') {
                            secondValue.append(",")
                            val fir = secondValue.text.toString().substring(0, secondValue.text.length - 1)
                                .toDouble() * rates[firstRate] / rates[secondRate]
                            secondValue.text = fir.toString()
                        }
                        else {
                            val sec = firstValue.text.toString()
                                .toDouble() * rates[firstRate] / rates[secondRate]
                            secondValue.text = sec.toString()
                        }
                    }
                }
            1 -> if (n != -1 && secondValue.text.length < 9) {
                if (!secondValue.text.toString().contains(",") && n == 10) {
                    secondValue.append(",")
                    val fir = secondValue.text.toString().substring(0, secondValue.text.length - 1)
                        .toDouble() * rates[firstRate] / rates[secondRate]
                    secondValue.text = fir.toString()
                } else {
                    if (secondValue.text.toString() == "0")
                        secondValue.text = n.toString()
                    else
                        secondValue.append(n.toString())

                    val fir = secondValue.text.toString()
                        .toDouble() * rates[secondRate] / rates[firstRate]
                    firstValue.text = fir.toString()
                }
            }
        }

        firstValue.text = firstValue.text.substring(0, min(firstValue.length(), 9))
        secondValue.text = secondValue.text.substring(0, min(secondValue.length(), 9))
    }
}