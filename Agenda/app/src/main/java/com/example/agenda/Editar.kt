package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Editar : AppCompatActivity() {
    lateinit var txtNombre : TextView
    lateinit var txtApellido : TextView
    lateinit var txtEdad : TextView
    var idd = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar)
        txtNombre = findViewById(R.id.edNombre)
        txtApellido = findViewById(R.id.edApellido)
        txtEdad = findViewById(R.id.edEdad)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getStringExtra("edad")
        val id = intent.getStringExtra("id")
        idd = Integer.parseInt(id)
        Toast.makeText(this, "Recibi $idd", Toast.LENGTH_SHORT).show()

        txtNombre.setText(nombre)
        txtApellido.setText(apellido)
        txtEdad.setText(edad)

    }
    fun guardar(v: View){
        val nombre = txtNombre.text.toString()
        val apellido = txtApellido.text.toString()
        val edad = txtEdad.text.toString()//.toInt()
        val persona = Persona(nombre,apellido,edad,idd.toString())
        Provisional.listPersona[idd] = persona
        Toast.makeText( this, "Se guardo" , Toast.LENGTH_SHORT).show()


        finish()
    }
}