package com.example.agendafinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Agregar : AppCompatActivity() {
//declaramos variables
    lateinit var edId : EditText
    lateinit var edNombre : EditText
    lateinit var edApellido : EditText
    lateinit var edEdad : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

         edId = findViewById(R.id.edId)
         edNombre = findViewById(R.id.edNombre)
         edApellido = findViewById(R.id.edApellido)
         edEdad= findViewById(R.id.edEdad)
    }
    fun accionBoton(v:View){ // metodo para agregar una persona a la base de datos
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        val persona = Persona((edId.text.toString().toInt()),edNombre.text.toString() ,edApellido.text.toString(),edEdad.text.toString())
        //Implementar retrofit
        val retrofit = Retrofit.getRetrofit()
        val retrofitServicio = retrofit.create(IPersona::class.java)
        val peticion: Call<Resultado> = retrofitServicio.agregarPersona(persona)
        peticion.enqueue(object:Callback<Resultado>{
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val resultado = response.body()
                mensajeGrabo(resultado!!)
            }
            override fun onFailure(call: Call<Resultado>, t: Throwable) {
            }
        })
    }

    fun mensajeGrabo(resultado: Resultado){ // metodo para ver el estado sobre si se guardo la persona
        Log.e("Error", resultado.estado)
    }
}