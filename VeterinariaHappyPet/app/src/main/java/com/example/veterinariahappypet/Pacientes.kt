package com.example.veterinariahappypet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Pacientes : AppCompatActivity() {
    lateinit var dao: daoUsuario
    lateinit var txti: TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)
        dao = daoUsuario(this)
        txti = findViewById(R.id.TextoId);
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val id = preferencia.getString("IdUsuario", "No se ha ingresado")


        var ux : Usuario = dao.getUsuarioById(Integer.parseInt(id))
        txti.text = "" + ux.id + " " + ux.usuario + " " + ux.nombre + " " + ux.password
    }

    fun CerrarSesion(v: View){
        val preferencias = getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.putString("IdUsuario", "");
        editor.commit()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}