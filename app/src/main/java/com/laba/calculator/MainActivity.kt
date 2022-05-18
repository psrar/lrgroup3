package com.laba.calculator



import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_calc.*


class MainActivity : AppCompatActivity() {
    private var canAddOperation = false
    private var isDecimal = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        bt_change.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Вы хотите сменить калькулятор")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Конвет", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })
                // negative button text and action
                .setNegativeButton("Отмена", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("AlertDialogExample")
            // show alert dialog
            alert.show()
        }
    }




    fun numberAction(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (isDecimal) {
                    math_operation.append(view.text)
                    result_operation.text = calcResult()
                }
                canAddOperation = false

            } else {
                math_operation.append(view.text)
                result_operation.text = calcResult()
            }
            canAddOperation = true

        }

    }

    fun operationAction(view: View) {
        if (view is Button && canAddOperation) {
            math_operation.append(view.text)
            canAddOperation = false
            isDecimal = true

        }
    }

    fun allClearAction(view: View) {

        math_operation.text = ""
        result_operation.text = ""
    }

    fun backSpaceAction(view: View) {
        result_operation.text = calcResult()
        val length = math_operation.length()
        if (length > 0)
            math_operation.text = math_operation.text.subSequence(0, length - 1)
    }

    fun equalsAction(view: View) {
        result_operation.text = calcResult()
    }

    private fun calcResult(): String {
        val digitOperat = digitsOperators()
        if (digitOperat.isEmpty()) return ""

        val timeDivision = timeDivisionCalculator(digitOperat)
        if (timeDivision.isEmpty()) return ""

        val result = addSubAddCalc(timeDivision)
        return result.toString()
    }

    private fun addSubAddCalc(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }
        return result
    }

    private fun timeDivisionCalculator(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('*') || list.contains('/')) {
            list = calcTimeDiv(list)
        }
        return list
    }

    private fun calcTimeDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when (operator) {
                    '*' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }
            if (i > restartIndex)
                newList.add(passedList[i])
        }
        return newList
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDirit = ""
        for (character in math_operation.text) {
            if (character.isDigit() || character == '.')
                currentDirit += character
            else {
                list.add(currentDirit.toFloat())
                currentDirit = ""
                list.add(character)
            }
        }
        if (currentDirit != "")
            list.add(currentDirit.toFloat())
        return list
    }
}