package com.example.bmicalculator

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity:AppCompatActivity(){
    lateinit var binding:ActivityMainBinding

    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bind Calculate button to onClickListener. On click call calculate function.
        binding.calculateButton.setOnClickListener { calculate() }

        binding.resetButton.setOnClickListener { clear() }

        // Set background color of activity to green
        window.decorView.setBackgroundColor(Color.GREEN)
    }

    private fun calculate() {
        // This function will perform the BMI calculation.

        // Hide Keypad on button click.
        try {
            val imm: InputMethodManager =
                getSystemService (Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            // TODO: handle exception
        }

        
        // Get height and weight from entry fields.
        // Convert to usable doubles.
        val stringInTextField = binding.enterWeight.text.toString()
        val weightDouble = stringInTextField.toDouble()
        val nextStringInTextField = binding.enterHeight.text.toString()
        val heightDouble = nextStringInTextField.toDouble()

        // Calculation stage.
        val bmiResult = weightDouble / (heightDouble * heightDouble)

        // Call output function and pass bmiResult
        output (bmiResult)

    }

    private fun output(bmiResult: Double) {
        // This function will handle the output of this app.
        // Format bmiResult to two decimal places.
        val final = "BMI = %.2f".format(bmiResult)
        // Output to text field.
        binding.resultOutput.text = final

        // Output Description
        if (bmiResult <= 18.5 ){
            window.decorView.setBackgroundColor(Color.YELLOW)
            // Have a snack!
            binding.resultOutput2.text = "Underweight"
        }

        else if (bmiResult >=18.6 && bmiResult <= 24.9){
            // Good Going!
            binding.resultOutput2.text = "Healthy Weight"
        }

        else if (bmiResult >= 25 && bmiResult <= 29.9){
            window.decorView.setBackgroundColor(Color.YELLOW)
            // Better put down that fork!
            binding.resultOutput2.text = "Overweight"
        }

        else {
            window.decorView.setBackgroundColor(Color.RED)
            // Oh lawd (s)he comin'!!
            binding.resultOutput2.text = "Obese"
        }


    }

    private fun clear(){
        // This function clears the input fields.
        // Clear first text entry field
        val stringInTextField = binding.enterHeight.text
        stringInTextField.clear()
        // Clear second text entry field
        val nextStringInTextField = binding.enterWeight.text
        nextStringInTextField.clear()
    }


}
