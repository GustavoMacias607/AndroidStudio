package com.example.checkbox_04

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import kotlin.math.log

class MainActivity : AppCompatActivity() {
 //variables
    lateinit var txt:EditText
    lateinit var chkCursiva:CheckBox
    lateinit var chkNegrita:CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // asignacion de variables
        txt = findViewById(R.id.edNombre)
        chkCursiva = findViewById(R.id.chkCursiva)
        chkNegrita = findViewById(R.id.chkNegrita)

    }


    fun clickCheck(v:View){ // metodo para cambiar las letras en cursiva o negrita
        txt.typeface = Typeface.DEFAULT

        if (chkCursiva.isChecked && chkNegrita.isChecked)
          txt.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
         else if (chkCursiva.isChecked)
            txt.typeface = Typeface.defaultFromStyle(Typeface.ITALIC)
        else if(chkNegrita.isChecked)
            txt.typeface = Typeface.defaultFromStyle(Typeface.BOLD)

    }
}