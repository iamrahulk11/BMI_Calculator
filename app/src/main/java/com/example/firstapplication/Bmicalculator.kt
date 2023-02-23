package com.example.firstapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Bmicalculator: AppCompatActivity() {
    val tAG: String="FirstApplication"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        //taking values from user by ID and saving it
        val weight = findViewById<EditText>(R.id.enterweight_1)
        val height = findViewById<EditText>(R.id.enterheight_1)
        val button  = findViewById<Button>(R.id.Calculate_BMI)
        val result = findViewById<TextView>(R.id.result)
        val result2 = findViewById<TextView>(R.id.message_underORoverWeight)

        //hinding the result ..will shown after calculation
        result.visibility = View.INVISIBLE
        result2.visibility = View.INVISIBLE

        //on clicking the bmi calculator this functionality will run under onCreate
        button.setOnClickListener(View.OnClickListener {
            var heightValue = 0.0 //assigning value
            var weightValue = 0.0 //assigning value

            //if height and weight is not 0 then assigning the height and weight value
            if(height.text.toString().isNotEmpty() && weight.text.toString().isNotEmpty()) {
                heightValue = height.text.toString().toDouble() //assigning height value

                //if weight is not 0 then assigning the weight value
                weightValue = weight.text.toString().toDouble() //assigning weight value
            }

            //calculating bmi and displaying it if height and weight is not 0
             if(heightValue > 0 && weightValue > 0) {
            //calculating bmi using bmi formula with giving float values
                val bmiValue =
                    weightValue.toFloat() / ((heightValue.toFloat() / 100) * (heightValue.toFloat() / 100))
            //converting the values to string
                val digitformat = String.format("%.2f", bmiValue).toFloat()

            //displaying the result in result textview
                result.text = "${digitformat}"

            //calling the function checkweight to know whether bmi comes under normal underweight or overweight
                result2.text = "You are " + "${checkWeight(digitformat.toFloat())}"

            //giving visibility to the textview
                result.visibility = VISIBLE
                result2.visibility = VISIBLE

            //calling closeKeyboard function to close or hide after clicking the bmi calculator button (work completed)
                closeKeyboard()
            }
            //if height and weight is 0 and still click the bmi calculator button this message will arise(pop up)
            else{
                Toast.makeText(
                    this, "Please input Weight and Height Values greater than 0",
                    Toast.LENGTH_LONG).show()
            }

            //after calculating and displaying the result clearing the editview of height and weight
            weight.text.clear()
            height.text.clear()
        })

        Log.d(tAG, "onCreate()")
    }

//created checkweight function to display result in result2
    fun checkWeight(bmi: Float):String{
        lateinit var message: String //created string as message name
        if(bmi<18.5){
            message="Underweight"
        } else if(bmi >= 18.5 && bmi <= 24.9) {
            message="Normal"
        } else if(bmi > 24.9 && bmi <= 29.9) {
            message="Overweight"
        } else {
            message="Obese"
        }
        return message // returning the message
    }

    //completion of work keyboard is hided
    fun closeKeyboard() {
        val newView: View? = this.currentFocus //created view to save the focus
    if(newView != null){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(newView.windowToken, 0)
    }
    }
    override fun onStart() {
        super.onStart()
        Log.d(tAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tAG, "onDestroy()")
    }
}