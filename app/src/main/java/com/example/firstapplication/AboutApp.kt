package com.example.firstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import com.example.firstapplication.databinding.ActivityAboutAppBinding

class AboutApp : AppCompatActivity() {
    lateinit var binding: ActivityAboutAppBinding
    lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutAppBinding.inflate(layoutInflater)
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

        inflater.inflate(R.menu.menu_aboutapp, menu)
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item2 -> {
                val bmiChartActivity = Intent(this, BmiChart::class.java)
                startActivity(bmiChartActivity)
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