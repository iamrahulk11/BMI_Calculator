package com.example.firstapplication

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.example.firstapplication.databinding.ActivityBmiChartBinding

class BmiChart : AppCompatActivity() {
    lateinit var binding: ActivityBmiChartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        finish()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // menuInflater.inflate(R.menu.menu_design,menu)
        val inflater: MenuInflater = menuInflater

        inflater.inflate(R.menu.menu_bmichart, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item1 -> {
                val aboutActivity = Intent(this, AboutApp::class.java)
                startActivity(aboutActivity)
                finish()
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

}