package com.example.intenciones07

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1: Button = findViewById(R.id.btnPantalla)
        val btn2: Button = findViewById(R.id.btnPagina)

        btn1.setOnClickListener{
            Log.w("Boton", "a pantalla")
            val intent1 = Intent(this, Tecladito::class.java)
            startActivity(intent1)
        }
        btn2.setOnClickListener{
            Log.w("Boton", "a pagina")
            // Intencion implicita
            val aPagina  = Intent(android.content.Intent.ACTION_VIEW)
            aPagina.data = Uri.parse("https://google.com.mx")
            startActivity(aPagina)
        }
    }
}