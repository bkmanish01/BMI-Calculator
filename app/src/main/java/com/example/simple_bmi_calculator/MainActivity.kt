package com.example.simple_bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textWeight = findViewById<EditText>(R.id.etWeight)
        val textHeight = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalc)

        calcButton.setOnClickListener {
            val weight = textWeight.text.toString()
            val height = textHeight.text.toString()

            if (inputValidation(weight, height)) {
                val bmi = (weight.toFloat() / 2.205) / (height.toFloat() * 0.305).pow(2)
                val bmi2Decimal = String.format("%.2f", bmi).toFloat()

                displayResult(bmi2Decimal)
                textWeight.text.clear()
                textHeight.text.clear()
            }

        }
    }

    private fun inputValidation(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter weight", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Please enter height", Toast.LENGTH_LONG).show()
                return false
            }  else -> {
                return true
            }
        }
    }

    private fun displayResult(bmi: Float) {
        val bmiResult = findViewById<TextView>(R.id.tvbmi)
        val textResult = findViewById<TextView>(R.id.tvResult)
        bmiResult.text = bmi.toString()

        var txtResult = ""
        var color = 0
        when {
            bmi < 18.50 -> {
                txtResult = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
                txtResult = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                txtResult = "Overweight"
                color = R.color.over_weight
            }
            bmi > 29.99 -> {
                txtResult = "Obese"
                color = R.color.obese
            }
        }
        textResult.setTextColor(ContextCompat.getColor(this, color))
        textResult.text = txtResult
    }
}