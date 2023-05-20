package com.example.ejemplobasedatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    lateinit var edId : EditText
    lateinit var edNombre : EditText
    lateinit var edTelefono : EditText
    lateinit var edBuscar : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edId = findViewById(R.id.edId)
        edNombre = findViewById(R.id.edNombre)
        edTelefono = findViewById(R.id.edTelefono)
        edBuscar = findViewById(R.id.edEscribir)


    }

    fun consultar(v:View){
        val intent = Intent(this, Mostrar::class.java)
        startActivity(intent)

    }
    fun agregarDato(v:View){
        val id = edId.text.toString().toInt()
        val nombre = edNombre.text.toString()
        val telefono = edTelefono.text.toString()
        //Crear objeto persona
        val persona = Persona(id,nombre,telefono)
        //voy a crear el puntero a la bd
        val db = Room.databaseBuilder(applicationContext, 
            AppDataBase::class.java,"contactos").allowMainThreadQueries().build()
        db.personaDao().agregar(persona)
        Toast.makeText(this, "se grabo", Toast.LENGTH_SHORT).show()
    }

    fun Buscar(v:View){
        var nombre = edBuscar.text.toString();
        val db = Room.databaseBuilder(applicationContext,
            AppDataBase::class.java,"contactos").allowMainThreadQueries().build()
        val persona = db.personaDao().findByName(nombre)
        Toast.makeText(this, "id:  ${persona.id}", Toast.LENGTH_SHORT).show()

            edNombre.setText(persona.nombre)
           edId.setText((persona.id).toString())
           edTelefono.setText(persona.telefono)

    }
    

}