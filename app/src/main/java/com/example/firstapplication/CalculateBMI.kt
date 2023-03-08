package com.example.firstapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.MainActivityBinding
import com.example.firstapplication.utils.Keys
import com.google.gson.Gson

class CalculateBMI: AppCompatActivity(), OnClickListener {
    private val tAG: String = "BMI_Calculator"

    /*//globally assign
    private lateinit var weight:EditText
    private lateinit var height:EditText
    private lateinit var button:Button
    private lateinit var result:TextView
    private lateinit var result2:TextView*/
//    var check: Boolean = false
    private var heightValue = 0.0 //assigning value
    private var weightValue = 0.0 //assigning value
    private lateinit var context: Context

    //view binding created for main_activity for better performance
    private lateinit var binding: MainActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //inflating the layout

        binding = MainActivityBinding.inflate(layoutInflater)
        //set the content view as binding root
        setContentView(binding.root)

        Log.d(tAG, "onCreate()")
        context = this@CalculateBMI
        binding.enterweight1.requestFocus()
        setSupportActionBar(binding.toolbar)

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


      /*  binding.result.visibility = View.INVISIBLE
        binding.messageUnderORoverWeight.visibility = View.INVISIBLE*/

        //call the click listener
        binding.CalculateBMI.setOnClickListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       // menuInflater.inflate(R.menu.menu_design,menu)
        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.menu_design, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item1 -> {
                val aboutActivity = Intent(this, AboutApp::class.java)
                startActivity(aboutActivity)
            }
            R.id.item2 -> {
                val bmiChartActivity = Intent(this, BmiChart::class.java)
                startActivity(bmiChartActivity)
            }
            R.id.item3 -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Confirmation")
                alertDialogBuilder.setMessage("Do you want to Exit?")
                alertDialogBuilder.setPositiveButton("Yes"){
                        _,_ ->
                    finish()
                }
                alertDialogBuilder.setNegativeButton("No"){
                        Dialog,_->
                    Dialog.cancel()
                }
                val alertDialog: AlertDialog = alertDialogBuilder.create()
                // Set other dialog properties
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        Log.d(tAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tAG, "onResume()")

    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
       // super.onBackPressed()
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Do you want to Exit?")
        alertDialogBuilder.setPositiveButton("Yes"){
            _,_ ->
            finish()
        }
        alertDialogBuilder.setNegativeButton("No"){
            Dialog,_->
            Dialog.cancel()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
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
    private fun checkWeight(bmi: Float): String {
        val message: String = if (bmi < 18.5) {
            "Underweight"
        } else if ((bmi) in 18.5..24.9) {
            "Normal"
        } else if (bmi in 24.9..29.9) {
            "Overweight"
        } else {
            "Obese"
        }
        return message // returning the message
    }

    //completion of work keyboard is hided
    private fun closeKeyboard() {
        val newView: View? = this.currentFocus //created view to save the focus
        if (newView != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(newView.windowToken, 0)
        }
    }

    override fun onClick(v: View?) {


            when (v?.id) {
                R.id.Calculate_BMI -> {
                    //if height and weight is not 0 and not empty then assigning the height and weight value
                    if (binding.enterheight1.editText!!.text.toString()
                            .isNotEmpty() && binding.enterweight1.editText!!.text.toString().isNotEmpty()
                    ) {
                        if(binding.enterheight1.editText!!.text.toString().toInt() == 0 || binding.enterweight1.editText!!.text.toString().toInt() == 0){
                            if(binding.enterheight1.editText!!.text.toString().toInt() == 0 && binding.enterweight1.editText!!.text.toString().toInt() == 0){
                                Toast.makeText(
                                    context, "Weight and Height Value should be greater than 0",
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.enterweight1.editText!!.text.clear()
                                binding.enterheight1.editText!!.text.clear()
                                binding.enterweight1.requestFocus()
                                return
                            }
                            else if(binding.enterweight1.editText!!.text.toString().toInt()==0){
                                Toast.makeText(
                                    context, "Weight Value should be greater than 0",
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.enterweight1.editText!!.text.clear()
                                binding.enterweight1.requestFocus()
                                return
                            }else{
                                Toast.makeText(
                                    context, "Height Value should be greater than 0",
                                    Toast.LENGTH_LONG
                                ).show()
                                binding.enterheight1.editText!!.text.clear()
                                binding.enterheight1.requestFocus()
                                return
                            }
                        }else {
                            heightValue =
                                binding.enterheight1.editText!!.text.toString()
                                    .toDouble() //assigning height value

                            //if weight is not 0 then assigning the weight value
                            weightValue =
                                binding.enterweight1.editText!!.text.toString()
                                    .toDouble() //assigning weight value
                        }
                    }else{
                        if (binding.enterheight1.editText!!.text.toString().isEmpty() && binding.enterweight1.editText!!.text.toString().isEmpty() ) {
                            Toast.makeText(
                                context, "Weight and Height Values should not be empty",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.enterweight1.requestFocus()
                            return
                            /*binding.enterheight1.text.clear()
                            binding.enterweight1.text.clear()*/
                        }
                        else if (binding.enterheight1.editText!!.text.toString()
                                .isEmpty()) {
                            Toast.makeText(
                                context, "Height Value should not be empty",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.enterheight1.requestFocus()
                            return
                        }else if(binding.enterweight1.editText!!.text.toString().isEmpty()) {
                            Toast.makeText(
                                context, "Weight Value should not be empty",
                                Toast.LENGTH_LONG
                            ).show()
                            binding.enterweight1.requestFocus()
                            return
                        }
                    }
                    //calculating bmi and displaying it if height and weight is not 0
                    if(heightValue > 0 && weightValue > 0) {
                        //calculating bmi using bmi formula with giving float values
                        val bmiValue =
                            weightValue.toFloat() / ((heightValue.toFloat() / 100) * (heightValue.toFloat() / 100))
                        //converting the values to string
                        val userBmi = String.format("%.2f", bmiValue).toFloat()

                        //calling the function check_weight to know whether bmi comes under normal underweight or overweight
                        val bmiMessage = checkWeight(userBmi)


                        val intent = Intent(this, SecondActivity::class.java)
                        val user = User()
                        user.userbmi = userBmi.toString()
                        user.bmiMessage = bmiMessage
//        intent.putExtra(Keys.USER, user)
                        intent.putExtra(Keys.JSON_USER, Gson().toJson(user))
                        startActivity(intent)
//                        finish()

                        //giving visibility to the textview
                       /* binding.result.visibility = VISIBLE
                        binding.messageUnderORoverWeight.visibility = VISIBLE*/

                        //displaying the result in result textview
                        /*binding.result.text = "Your BMI is " + String.format("%.2f", bmiValue)
                        binding.result.setBackgroundColor(Color.rgb(173,216,230))

                        if(bmiMessage == "Normal"){
                            binding.messageUnderORoverWeight.text = "You are  ${bmiMessage}"
                            binding.messageUnderORoverWeight.setBackgroundColor(Color.GREEN)
                        }else if(bmiMessage == "Underweight") {
                            binding.messageUnderORoverWeight.text = "You are  ${bmiMessage}"
                            binding.messageUnderORoverWeight.setBackgroundColor(Color.YELLOW)
                        }else if(bmiMessage == "Overweight"){
                            binding.messageUnderORoverWeight.text = "You are  ${bmiMessage}"
                            binding.messageUnderORoverWeight.setBackgroundColor(Color.MAGENTA)
                        }else if(bmiMessage == "Obese"){
                            binding.messageUnderORoverWeight.text = "You are  ${bmiMessage}"
                            binding.messageUnderORoverWeight.setBackgroundColor(Color.RED)
                        }

                        check = true
                        binding.CalculateBMI.text = "CLEAR"
                        binding.enterheight1.isEnabled = false
                        binding.enterweight1.isEnabled = false
                       */
                        //calling closeKeyboard function to close or hide after clicking the bmi calculator button (work completed)
                        closeKeyboard()
                        binding.enterweight1.editText!!.text.clear()
                        binding.enterweight1.editText!!.text.clear()
                    }
                    //if height and weight is 0 and still click the bmi calculator button this message will arise(pop up)
                }
            }
        /*}else{
                binding.enterweight1.editText!!.text.clear()
                binding.enterheight1.editText!!.text.clear()
               *//* binding.result.visibility = View.INVISIBLE
                binding.messageUnderORoverWeight.visibility = View.INVISIBLE*//*
                binding.CalculateBMI.text = "CALCULATE"
                check=false
                binding.enterheight1.isEnabled = true
                binding.enterweight1.isEnabled = true
                binding.enterweight1.requestFocus()

            }*/

        }

}