package com.example.veterinariahappypet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Pacientes : AppCompatActivity() {
    lateinit var dao: daoUsuario
    lateinit var daoP: daoPaciente
    lateinit var lblSesion : TextView
    lateinit var rvPacientes: RecyclerView
    lateinit var textVieww : TextView
    lateinit var edBuscar: EditText

    lateinit var ux : Usuario

    var idd:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)
        rvPacientes = findViewById(R.id.rvPacientes)
        dao = daoUsuario(this)
        daoP = daoPaciente(this)
        textVieww = findViewById(R.id.textView4)
        edBuscar = findViewById(R.id.txtBuscar)


        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val id = preferencia.getString("IdUsuario", "No se ha ingresado")
        idd = Integer.parseInt(id)
        lblSesion = findViewById(R.id.lblSesion)

         ux = dao.getUsuarioById(Integer.parseInt(id))
        lblSesion.text = ux.nombre


    }

    override fun onResume() {
        super.onResume()
        val valorthis = this
        val adaptador = PaAdaptador(this,idd)
        rvPacientes.adapter = adaptador
        rvPacientes.layoutManager = LinearLayoutManager(this)

        edBuscar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // No es necesario implementar este método, pero debes mantenerlo en blanco.

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Aquí actualizas el TextView con el texto actual del EditText.
                val adaptador2 = PaAdaptador(valorthis, idd, charSequence.toString())
                rvPacientes.adapter = adaptador2
                rvPacientes.layoutManager = LinearLayoutManager(valorthis)
            }

            override fun afterTextChanged(editable: Editable) {
                // No es necesario implementar este método, pero debes mantenerlo en blanco.
            }
        })
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