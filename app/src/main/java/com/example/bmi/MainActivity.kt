package com.example.bmi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.bmi.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{calculateBMI()}
        binding.weightInputEditText.setOnKeyListener{view,keyCode,_ -> handleKeyEvent(view,keyCode)}
    }

    private fun calculateBMI(){
        val ageTextField = binding.ageInputEditText.text.toString().toIntOrNull()
        val heightField = binding.heightInputEditText.text.toString().toIntOrNull()
        val weightField = binding.weightInputEditText.text.toString().toDoubleOrNull()
        if((ageTextField == null) && (heightField == null) && (weightField==null)){
            return
        }
        val bmi = (weightField!! / heightField!! /heightField) * 10000
        displayBMI(bmi)
    }
    private fun displayBMI(BMI: Double) {
        binding.bmiResult.text = "BMI Result: ${String.format("%.2f",BMI)}"
    }
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}