package com.example.radiobutton03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var txtResultado: TextView
    lateinit var rdSuma: RadioButton
    lateinit var rdResta: RadioButton
    lateinit var rdMultiplicacion: RadioButton
    lateinit var rdDivision: RadioButton
    lateinit var num1: EditText
    lateinit var num2: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando

        txtResultado = findViewById(R.id.txtResultado)
        rdSuma = findViewById(R.id.rbSuma)
        rdResta= findViewById(R.id.rbResta)
        rdMultiplicacion = findViewById(R.id.rbMultiplicacion)
        rdDivision = findViewById(R.id.rbDivision)
        num1 = findViewById(R.id.edNum1)
        num2 = findViewById(R.id.edNum2)

    }

    fun accionBoton(v:View){
        val n1 = num1.text.toString().toInt()
        val n2 = num2.text.toString().toInt()
        var resultado = 0
        if (rdSuma.isChecked){
            resultado = n1 + n2
        }else if(rdResta.isChecked){
            resultado = n1-n2
        }else if(rdMultiplicacion.isChecked){
            resultado = n1*n2
        }else if(rdDivision.isChecked){
            resultado = n1/n2
        }
        txtResultado.text = "$resultado"
    }


}