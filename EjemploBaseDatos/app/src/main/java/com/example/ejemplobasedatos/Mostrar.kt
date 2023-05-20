package com.example.ejemplobasedatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Mostrar : AppCompatActivity() {

    lateinit var rvPersona: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)
        rvPersona = findViewById(R.id.rvPersonas)
    }

    override fun onResume() {
        super.onResume()
        val adaptador = Adaptador(this,this)
        rvPersona.adapter = adaptador
        rvPersona.layoutManager = LinearLayoutManager(this)

    }

    fun Regresar(v: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}