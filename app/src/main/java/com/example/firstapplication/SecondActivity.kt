package com.example.firstapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firstapplication.databinding.ActivitySecondBinding
import com.example.firstapplication.utils.Keys
import com.google.gson.Gson

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:ActivitySecondBinding
    lateinit var context:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context=this@SecondActivity
        setSupportActionBar(binding.toolbar)

        val intent = intent
        val intentData =  intent.getStringExtra(Keys.JSON_USER)
        val user = Gson().fromJson<User>(intentData, User::class.java)

        binding.bmiMessageView.text = "You are "+ user.bmiMessage
        binding.bmiView.text = "Your BMI is : "+ user.userbmi

        if(user.bmiMessage == "Normal"){
            binding.imageview.setImageResource(R.drawable.normal)

        }else if(user.bmiMessage == "Underweight") {
            binding.imageview.setImageResource(R.drawable.underweight)

        }else if(user.bmiMessage == "Overweight"){
            binding.imageview.setImageResource(R.drawable.overweight)

        }else if(user.bmiMessage == "Obese"){
            binding.imageview.setImageResource(R.drawable.fat)
        }

        binding.btnRecalculate.setOnClickListener(this)

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


    override fun onBackPressed() {
        // super.onBackPressed()
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmation")
        alertDialogBuilder.setMessage("Do you want to return to home page?")
        alertDialogBuilder.setPositiveButton("Yes"){
                Dialog,which ->
            val backHomePage = Intent(this, CalculateBMI::class.java)
            startActivity(backHomePage)
            finish()

        }
        alertDialogBuilder.setNegativeButton("No"){
                Dialog,which->
            Dialog.cancel()
        }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnRecalculate ->{
                val backHomePage = Intent(this, CalculateBMI::class.java)
                startActivity(backHomePage)
                finish()
            }
        }
    }
}