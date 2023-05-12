package com.example.tarjetadigital

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var lblNombre : TextView
    lateinit var lblCarrera : TextView
    lateinit var lblTelefono :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ////

        lblNombre = findViewById(R.id.lblNombre)
        lblCarrera = findViewById(R.id.lblCarrera)
        lblTelefono = findViewById(R.id.lblTelefono)

    }

    override fun onResume() {
        super.onResume()
        val preferencia = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val nombre = preferencia.getString("nombre", "Aun no registrado")
        val telefono = preferencia.getString("telefono", "Aun no registrado")
        val carrera = preferencia.getString("carrera", "Aun no registrado")
        lblNombre.text = nombre
        lblCarrera.text = carrera
        lblTelefono.text = telefono
    }

    fun guardar(v:View){
        val intent = Intent(this, Editar::class.java)
        intent.putExtra("nombre", lblNombre.text)
        intent.putExtra("carrera", lblCarrera.text)
        intent.putExtra("telefono", lblTelefono.text)
        startActivity(intent)
    }



}