package com.example.convertercoin

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.convertercoin.databinding.ActivityMainBinding
import java.util.Scanner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroupOptions = findViewById<RadioGroup>(R.id.radioGroupOptions)
        val editTextAmount = findViewById<EditText>(R.id.editTextAmount)
        val buttonConvert = findViewById<Button>(R.id.buttonConvert)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonConvert.setOnClickListener {
            val selectedRadioButtonId = radioGroupOptions.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedRadioButtonId)

            val amountText = editTextAmount.text.toString()
            if (amountText.isEmpty()) {
                textViewResult.text = "Por favor, ingresa una cantidad"
                return@setOnClickListener
            }

            val amount = amountText.toDouble()

            when (radioButton) {
                findViewById<RadioButton>(R.id.radioButtonMillilitersToFluidOunces) -> {
                    val fluidOunces = convertFromMillilitersToFluidOunces(amount)
                    textViewResult.text = "$amount mililitros equivalen a $fluidOunces onzas líquidas"
                }
                findViewById<RadioButton>(R.id.radioButtonFluidOuncesToMilliliters) -> {
                    val milliliters = convertFromFluidOuncesToMilliliters(amount)
                    textViewResult.text = "$amount onzas líquidas equivalen a $milliliters mililitros"
                }
            }
        }
    }

    private fun convertFromMillilitersToFluidOunces(milliliters: Double): Double {
        return milliliters / 29.5735 // 1 onza líquida = 29.5735 mililitros
    }

    private fun convertFromFluidOuncesToMilliliters(fluidOunces: Double): Double {
        return fluidOunces * 29.5735 // 1 onza líquida = 29.5735 mililitros
    }
}