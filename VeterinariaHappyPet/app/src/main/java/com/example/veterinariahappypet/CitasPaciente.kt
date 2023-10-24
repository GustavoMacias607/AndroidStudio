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
    //variables globales
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
    var idUser = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_citas_paciente)
        //asignacion de variables globales
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
        idUser = intent.getStringExtra("pIdUsuario").toString()

        //llenamos los txt con los datos del paciente
        p = daoP.getPacienteById(Integer.parseInt(id),Integer.parseInt(idUser))
        txtMascota.text = p.mascota;
        txtPropietario.text = p.propietario;
        txtCorreo.text = p.correo;
        txtDomicilio.text = p.domicilio;
        txtTelefono.text = p.telefono;


    }

    //llenado del RecyclerView viee
    override fun onResume() {
        super.onResume()
        val adaptador = CiAdaptador(this,idPaci)
        rvCitas.adapter = adaptador
        rvCitas.layoutManager = LinearLayoutManager(this)
    }

    // metodo que se activa al darle clic al boton de modificar paciente, envia el id del paciente y del usuario
    fun UpdatePaciente(v:View){
        val intent = Intent(this, ModificarPaciente::class.java)
        intent.putExtra("idPac", (idPac))
        intent.putExtra("pIdUsuario", (idUser))
        startActivity(intent)
    }

    //metodo que se ejecuta al darle clic a una cita en el RecyclerView
    fun onClickCita(cita:Citas){
        val intent = Intent(this, DetallesCita::class.java)
        intent.putExtra("idCita", (cita.id).toString())
        intent.putExtra("idPaciente", (cita.idPaciente).toString())

        startActivity(intent)
    }
    //regresa a la pantalla anterior de pacientes
    fun Regresar(v: View){
        val intent = Intent(this, Pacientes::class.java)
        startActivity(intent)
    }

    fun Nou(v: View){
        Toast.makeText(this,"Aun no disponible", Toast.LENGTH_LONG).show();
    }


    //metodo que se activa cuando se le da clic al boton de agregar cita, enviando el id del usuario y paciente
    fun Agregar(v: View){
        val intent = Intent(this, AgregarCita::class.java)
        intent.putExtra("idPac", (idPac))
        intent.putExtra("pIdUsuario", (idUser))
        startActivity(intent)
    }
}