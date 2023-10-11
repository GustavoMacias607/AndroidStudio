package com.example.veterinariahappypet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class AgregarPaciente : AppCompatActivity() {

    lateinit var edMascota: EditText;
    lateinit var edPropietario: EditText;
    lateinit var edTelefono: EditText;
    lateinit var edDomicilio: EditText;
    lateinit var edCorreo: EditText;
    lateinit var lblMsg: TextView;


    lateinit var dao: daoPaciente
    lateinit var handler: Handler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_paciente)

        edMascota = findViewById(R.id.edMascota);
        edPropietario = findViewById(R.id.edPropietario);
        edTelefono = findViewById(R.id.edTelefono);
        edDomicilio = findViewById(R.id.edDomicilio);
        edCorreo = findViewById(R.id.edCorreo);
        lblMsg = findViewById(R.id.lblMsg);
        handler = Handler(Looper.getMainLooper())
        dao = daoPaciente(this)

    }

    fun Regresar(v: View){
        val intent = Intent(this, Pacientes::class.java)
        startActivity(intent)
    }

    fun CrearPaciente(v: View){
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val idString = preferencia.getString("IdUsuario", "No se ha ingresado")

        val id = idString?.toIntOrNull() ?: 0

            val mascota = edMascota.text.toString()
            val propietario = edPropietario.text.toString()
            val telefono = edTelefono.text.toString()
            val domicilio = edDomicilio.text.toString()
            val correo = edCorreo.text.toString()
            val u = RegPaciente(id, mascota, propietario, telefono, domicilio, correo)
            if(mascota == "" || propietario == ""|| telefono == ""|| domicilio == ""|| correo == ""){
                lblMsg.visibility = View.VISIBLE
                lblMsg.text = "Campos Vacios"
                handler.postDelayed({
                    lblMsg.visibility = View.INVISIBLE
                    // Hacer el TextView invisible después de 2 segundos
                }, 2000)
            }else if(dao.insertPaciente(u)){

                Toast.makeText(this,"Paciente Registrado Correctamente", Toast.LENGTH_LONG).show();
                val intent = Intent(this, Pacientes::class.java)
                startActivity(intent);
                finish();
            }



    }
}