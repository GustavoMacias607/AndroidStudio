package com.example.intenciones07

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class Tecladito : AppCompatActivity() {
    lateinit var txtTexto: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tecladito)



      txtTexto = findViewById(R.id.txtTexto)





    }

    fun btn1(v: View){
        txtTexto.text = "${txtTexto.text}1"
    }
    fun btn2(v: View){
        txtTexto.text = "${txtTexto.text}2"
    }
    fun btn3(v: View){
        txtTexto.text = "${txtTexto.text}3"
    }
    fun btn4 (v: View){
        txtTexto.text = "${txtTexto.text}4"
    }
    fun uno (v:View){
        txtTexto.text = txtTexto.text?.replaceFirst(".$".toRegex(), "")
    }
    fun clear(v:View){
        txtTexto.text = ""
    }


}