package com.example.veterinariahappypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CitasPaciente : AppCompatActivity() {
    lateinit var txtMascota: TextView
    lateinit var txtPropietario: TextView
    lateinit var txtCorreo: TextView
    lateinit var txtTelefono: TextView
    lateinit var txtDomicilio: TextView

    lateinit var daoP: daoPaciente
    lateinit var dao: daoCita
    lateinit var rvCitas: RecyclerView

    lateinit var p : RegPaciente
    lateinit var c : Citas

    var idPac = ""
    var idPaci = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas_paciente)
        txtMascota = findViewById(R.id.txtMascota)
        txtPropietario = findViewById(R.id.txtPropietario)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtDomicilio = findViewById(R.id.txtDomicilio)

        rvCitas = findViewById(R.id.rvCitas)

        daoP = daoPaciente(this)
        dao = daoCita(this)

        val id = intent.getStringExtra("pId")
        idPaci = id?.toIntOrNull() ?: 0

        idPac = id.toString()

        val idUser = intent.getStringExtra("pIdUsuario")

        //Toast.makeText(this,c.fecha + " " + c.foto, Toast.LENGTH_LONG).show();
        p = daoP.getPacienteById(Integer.parseInt(id),Integer.parseInt(idUser))
        txtMascota.text = p.mascota;
        txtPropietario.text = p.propietario;
        txtCorreo.text = p.correo;
        txtDomicilio.text = p.domicilio;
        txtTelefono.text = p.telefono;


    }

    override fun onResume() {
        super.onResume()

        val adaptador = CiAdaptador(this,idPaci)
        rvCitas.adapter = adaptador
        rvCitas.layoutManager = LinearLayoutManager(this)


    }



    fun Regresar(v: View){
        val intent = Intent(this, Pacientes::class.java)
        startActivity(intent)
    }

    fun Nou(v: View){
        Toast.makeText(this,"Aun no disponible", Toast.LENGTH_LONG).show();
    }


    fun Agregar(v: View){
        val intent = Intent(this, AgregarCita::class.java)

        intent.putExtra("idPac", (idPac))
        startActivity(intent)
    }
}