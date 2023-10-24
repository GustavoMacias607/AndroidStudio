package com.example.veterinariahappypet

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetallesCita : AppCompatActivity() {
    //variables globales
    lateinit var txtMascota: TextView
    lateinit var txtPropietario: TextView
    lateinit var txtFecha: TextView
    lateinit var txtSintomas: TextView
    lateinit var foto : ImageView
    lateinit var c : Citas
    lateinit var p : RegPaciente
    lateinit var dao: daoCita
    lateinit var daoP: daoPaciente
    var idPac = ""
    var idCita = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_cita)

        //asignacion de variables globales
        txtMascota = findViewById(R.id.txtMascotaC)
        txtPropietario = findViewById(R.id.txtPropietarioC)
        txtFecha = findViewById(R.id.dtFechaC)
        txtSintomas = findViewById(R.id.txtSintomasC)
        foto = findViewById(R.id.fotito)
        dao = daoCita(this)
        daoP = daoPaciente(this)
        idCita = intent.getStringExtra("idCita").toString()
        idPac = intent.getStringExtra("idPaciente").toString()
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val idUser = preferencia.getString("IdUsuario", "No se ha ingresado")

        //buscamos la cita

        c = dao.getCitaById(Integer.parseInt(idCita),Integer.parseInt(idPac))
        p = daoP.getPacienteById(Integer.parseInt(idPac),Integer.parseInt(idUser))
        Toast.makeText(this, p.mascota +" " +p.propietario + " " + c.fecha + " " + c.sintomas + " " + c.foto , Toast.LENGTH_LONG).show();

        //asignamos valores
        txtMascota.text = p.mascota;
        txtPropietario.text = p.propietario;
        txtFecha.text = c.fecha;
        txtSintomas.text = c.sintomas;
        foto.setImageBitmap(c.foto)





    }

    fun regresar(v: View){
        finish()
    }
}