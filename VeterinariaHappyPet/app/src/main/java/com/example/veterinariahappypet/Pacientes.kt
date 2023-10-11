package com.example.veterinariahappypet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class Pacientes : AppCompatActivity() {
    lateinit var dao: daoUsuario
    lateinit var daoP: daoPaciente
    lateinit var lblSesion : TextView
    lateinit var rvPacientes: RecyclerView

    lateinit var ux : Usuario

    var idd:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)
        rvPacientes = findViewById(R.id.rvPacientes)
        dao = daoUsuario(this)
        daoP = daoPaciente(this)


        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val id = preferencia.getString("IdUsuario", "No se ha ingresado")
        idd = Integer.parseInt(id)
        lblSesion = findViewById(R.id.lblSesion)
        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
      // var pa : RegPaciente = daoP.getPacienteById(9)
        //Toast.makeText(this, pa.idUsuario, Toast.LENGTH_LONG).show();
         ux = dao.getUsuarioById(Integer.parseInt(id))
        Toast.makeText(this, ux.nombre, Toast.LENGTH_LONG).show();
        lblSesion.text = ux.nombre
    }

    override fun onResume() {
        super.onResume()
        val adaptador = PaAdaptador(this,idd)
        rvPacientes.adapter = adaptador
        rvPacientes.layoutManager = LinearLayoutManager(this)
    }



    fun CerrarSesion(v: View){
        val preferencias = getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.putString("IdUsuario", "");
        editor.commit()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun CrearPaciente(v: View){
        val intent = Intent(this, AgregarPaciente::class.java)
        startActivity(intent)
    }
}