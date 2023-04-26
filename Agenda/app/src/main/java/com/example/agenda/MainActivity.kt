package com.example.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvPersona: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPersona = findViewById(R.id.rvPersonas)
    }

    override fun onResume() {
        super.onResume()
        val adaptador = Adaptador(this)
        rvPersona.adapter = adaptador
        rvPersona.layoutManager = LinearLayoutManager(this)
    }

    fun onClickPersona(persona:Persona){
        val intent = Intent(this, Editar::class.java)
        intent.putExtra("nombre", persona.nombre)
        intent.putExtra("apellido", persona.apellido)
        intent.putExtra("edad", persona.edad)
        intent.putExtra("id", persona.id)
        startActivity(intent)
    }

    fun mandarAgregar(v:View){
        val intent = Intent(this, AgregarActivity::class.java)
        startActivity(intent)
    }
}