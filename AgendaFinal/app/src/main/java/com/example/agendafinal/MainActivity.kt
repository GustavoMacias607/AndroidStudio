package com.example.agendafinal


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvPersona: RecyclerView
    var listaPersonas: List<Persona> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPersona = findViewById(R.id.rvPersonas)
        val adaptador = Adaptador(this,this, listaPersonas)
        rvPersona.adapter = adaptador
        rvPersona.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() { // metodo que se ejecuta al iniciar el programa
        super.onResume()
        val adaptador = Adaptador(this,this, listaPersonas)
        rvPersona.adapter = adaptador
        rvPersona.layoutManager = LinearLayoutManager(this)
        consulteDatos()
    }

fun actualizar(v:View){ // metodo para actualizar
    val intent = Intent(this, Prueba::class.java)
    startActivity(intent)
}
    fun consulteDatos() { // metodo para llenar la lista con los datos
        val retrofit = Retrofit.getRetrofit()
        val retrofitServicio = retrofit.create(IPersona::class.java)
        val peticion: Call <List<Persona>> = retrofitServicio.getListaPersonas()
        peticion.enqueue(object: Callback<List<Persona>>{
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                listaPersonas = response.body()!!
                acomodaRecycler(listaPersonas)
            }
            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
            }
        })
    }

    fun acomodaRecycler(listaPersonas:List<Persona>){ // metodo para imprimir en consola las personas registradas
         listaPersonas.forEach{
        Log.e("Dato", it.toString())
        }
    }

    fun accionBoton(v:View){ // metodo para abrir la vista de agregar
        val intent = Intent(this, Agregar::class.java)
        startActivity(intent)
    }



}