package com.example.firstapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.MainActivityBinding

class CalculateBMI: AppCompatActivity(), OnClickListener {
    private val tAG: String="BMI_Calculator"
/*//globally assign
    private lateinit var weight:EditText
    private lateinit var height:EditText
    private lateinit var button:Button
    private lateinit var result:TextView
    private lateinit var result2:TextView
    private lateinit var context: Context*/

    //view binding created for main_activity for better performance
    private lateinit var binding: MainActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflating the layout
        binding = MainActivityBinding.inflate(layoutInflater)

        //set the content view as binding root
        setContentView(binding.root)

        Log.d(tAG, "onCreate()")

        //taking values from user by ID and saving it
        /*weight = findViewById(R.id.enterweight_1)
        height = findViewById(R.id.enterheight_1)
        button  = findViewById(R.id.Calculate_BMI)
        result = findViewById(R.id.result)
        result2 = findViewById(R.id.message_underORoverWeight)
        context=this@CalculateBMI

        //Hiding the result ..will shown after calculation
        result.visibility = View.INVISIBLE
        result2.visibility = View.INVISIBLE

        //on clicking the bmi calculator this functionality will run
        button.setOnClickListener(this)*/


        binding.result.visibility = View.INVISIBLE
        binding.messageUnderORoverWeight.visibility = View.INVISIBLE

        //call the click listener
        binding.CalculateBMI.setOnClickListener(this)

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

    //created check_weight function to display result in result2
    private fun checkWeight(bmi: Float):String{
        val message:String = if(bmi<18.5){
            "Underweight"
        } else if((bmi) in 18.5..24.9) {
            "Normal"
        } else if(bmi in 24.9..29.9) {
            "Overweight"
        } else {
            "Obese"
        }
        return message // returning the message
    }
    //completion of work keyboard is hided
    private fun closeKeyboard() {
        val newView: View? = this.currentFocus //created view to save the focus
        if(newView != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(newView.windowToken, 0)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.Calculate_BMI -> {
                var heightValue = 0.0 //assigning value
                var weightValue = 0.0 //assigning value

                //if height and weight is not 0 then assigning the height and weight value
                if(binding.enterheight1.text.toString().isNotEmpty() && binding.enterweight1.text.toString().isNotEmpty()) {
                    heightValue = binding.enterheight1.text.toString().toDouble() //assigning height value

                    //if weight is not 0 then assigning the weight value
                    weightValue = binding.enterweight1.text.toString().toDouble() //assigning weight value
                }

                //calculating bmi and displaying it if height and weight is not 0
                if(heightValue > 0 && weightValue > 0) {
                    //calculating bmi using bmi formula with giving float values
                    val bmiValue =
                        weightValue.toFloat() / ((heightValue.toFloat() / 100) * (heightValue.toFloat() / 100))
                    //converting the values to string
                    val userBmi = String.format("%.2f", bmiValue).toFloat()

                    //displaying the result in result textview
                    binding.result.text = String.format("%.2f", bmiValue)

                    //calling the function check_weight to know whether bmi comes under normal underweight or overweight
                    binding.messageUnderORoverWeight.text = "You are  ${checkWeight(userBmi)}"

                    //giving visibility to the textview
                    binding.result.visibility = VISIBLE
                    binding.messageUnderORoverWeight.visibility = VISIBLE

                    //calling closeKeyboard function to close or hide after clicking the bmi calculator button (work completed)
                    closeKeyboard()
                }
                //if height and weight is 0 and still click the bmi calculator button this message will arise(pop up)
                else{
                    Toast.makeText(
                        this, "Please input Weight and Height Values greater than 0",
                        Toast.LENGTH_LONG).show()
                }
                //after calculating and displaying the result clearing the edit_view of height and weight
                binding.enterweight1.text.clear()
                binding.enterheight1.text.clear()
            }
        }
    }

}