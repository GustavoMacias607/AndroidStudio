package com.example.tarjetadigital

import android.app.Person
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Editar : AppCompatActivity() {
    lateinit var edNombre : EditText
    lateinit var edCarrera : EditText
    lateinit var edTelefono : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)

        edNombre = findViewById(R.id.edNombre)
        edCarrera = findViewById(R.id.edCarrera)
        edTelefono = findViewById(R.id.edTelefono)

        val nombre = intent.getStringExtra("nombre")
        val carrera = intent.getStringExtra("carrera")
        val telefono = intent.getStringExtra("telefono")

        edNombre.setText(nombre)
        edCarrera.setText(carrera)
        edTelefono.setText(telefono)

    }
    fun guardarValor(v: View){
        val preferencias = getSharedPreferences("misdatos", Context.MODE_PRIVATE)
        val editor = preferencias.edit()//permite guardar
        val nombre = edNombre.text.toString()//obtener del cuadro
        val carrera = edCarrera.text.toString()//obtener del cuadro
        val telefono = edTelefono.text.toString()//obtener del cuadro
        editor.putString("nombre", nombre)
        editor.putString("carrera", carrera)
        editor.putString("telefono", telefono)
        editor.commit()


        onClickPersona(nombre,carrera,telefono)
    }

    fun onClickPersona(nombre:String, carrera:String, telefono: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("nombre", nombre)
        intent.putExtra("carrera", carrera)
        intent.putExtra("telefono", telefono)
        startActivity(intent)
    }
}