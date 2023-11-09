package com.example.veterinariahappypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
    lateinit var btnModificar : Button
    lateinit var lblMensaje: TextView
    var idPac = ""
    var idCita = ""
    var idUser = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_cita)

        //asignacion de variables globales
        txtMascota = findViewById(R.id.txtPropi)
        txtPropietario = findViewById(R.id.txtCorre)
        txtFecha = findViewById(R.id.dtFechaC)
        txtSintomas = findViewById(R.id.txtSintomasC)
        foto = findViewById(R.id.fotito)
        btnModificar = findViewById(R.id.btnModificar)
        lblMensaje = findViewById(R.id.lblmensaje)
        dao = daoCita(this)
        daoP = daoPaciente(this)
        idCita = intent.getStringExtra("idCita").toString()
        idPac = intent.getStringExtra("idPaciente").toString()
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        idUser = preferencia.getString("IdUsuario", "").toString()

        //buscamos la cita
        c = dao.getCitaById(Integer.parseInt(idCita),Integer.parseInt(idPac))
        p = daoP.getPacienteById(Integer.parseInt(idPac),Integer.parseInt(idUser))

        //asignamos valores
        txtMascota.text = p.mascota;
        txtPropietario.text = p.propietario;
        txtFecha.text = c.fecha;
        txtSintomas.text = c.sintomas;
        foto.setImageBitmap(c.foto)

        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaObjeto = formato.parse(c.fecha)
        val fechaActual = Date()
        val formato2 = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fechaObjeto2 = formato2.parse(c.fecha)
        val fechaActual2 = Calendar.getInstance().time

        if (fechaObjeto.after(fechaActual)) {

        } else if (formato2.format(fechaObjeto2) == formato2.format(fechaActual2)) {

        }else if(fechaObjeto.before(fechaActual)) {
           btnModificar.setBackgroundResource(R.drawable.cerrarsesion)
           btnModificar.isEnabled = false
           lblMensaje.visibility = View.VISIBLE
       }





    }

    fun regresar(v: View){
        val intent = Intent(this, CitasPaciente::class.java)
        intent.putExtra("pIdUsuario", (idUser))
        intent.putExtra("pId", (idPac))
        startActivity(intent)
    }

    fun modificar(v:View){
        val intent = Intent(this, ModificarCita::class.java)
        intent.putExtra("idCita", (idCita))
        intent.putExtra("idPac", (idPac))
        startActivity(intent)
    }
}