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
import java.util.regex.Pattern

class ModificarPaciente : AppCompatActivity() {

    //variables globales
    lateinit var edMascota: TextView;
    lateinit var edPropietario: TextView;
    lateinit var edTelefono: TextView;
    lateinit var edDomicilio: TextView;
    lateinit var edCorreo: TextView;
    lateinit var lblMsg: TextView;
    lateinit var p : RegPaciente
    lateinit var dao: daoPaciente;
    lateinit var handler: Handler;
    var idPac = ""
    var idUser = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_paciente)

        //asignacion de variables globales
        edMascota = findViewById(R.id.edMascota);
        edPropietario = findViewById(R.id.edPropietario);
        edTelefono = findViewById(R.id.edTelefono);
        edDomicilio = findViewById(R.id.edDomicilio);
        edCorreo = findViewById(R.id.edCorreo);
        lblMsg = findViewById(R.id.lblMsg);
        handler = Handler(Looper.getMainLooper())
        dao = daoPaciente(this)
        idPac = intent.getStringExtra("idPac").toString()
        idUser = intent.getStringExtra("pIdUsuario").toString()

        //hace una consulta para buscar el paciente
       p = dao.getPacienteById(Integer.parseInt(idPac),Integer.parseInt(idUser))

        //llena los txt de la vista con los valores tomados de la consulta de buscar el paciente
        edMascota.text = p.mascota
        edCorreo.text = p.correo
        edPropietario.text = p.propietario
        edDomicilio.text = p.domicilio
        edTelefono.text = p.telefono
    }

    //metodo para regresar a la pantalla donde muestra las citas del paciente
    fun Regresar(v: View){
        finish()
    }
    fun ModificarPaci(v: View){
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val idString = preferencia.getString("IdUsuario", "No se ha ingresado")
        val id = idString?.toIntOrNull() ?: 0
        // llenado de variables
        val mascota = edMascota.text.toString()
        val propietario = edPropietario.text.toString()
        val telefono = edTelefono.text.toString()
        val domicilio = edDomicilio.text.toString()
        val correo = edCorreo.text.toString()

        //Validacion del telefono y correo
        val pattern = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
        )
        val matcher = pattern.matcher(correo)
        val patternTel = Pattern.compile(
            "^?\\d{10,12}$"
        )
        val matcherTel = patternTel.matcher(telefono)

        //Creando objeto de la clase y condiciones
        val u = RegPaciente(id, mascota, propietario, telefono, domicilio, correo)
        if(mascota == "" || propietario == ""|| telefono == ""|| domicilio == ""|| correo == ""){
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Campos Vacios"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
                // Hacer el TextView invisible después de 2 segundos
            }, 2000)
        }else if(!matcher.matches()){ // verifica que el correo sea valido
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Correo Invalido"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
                // Hacer el TextView invisible después de 2 segundos
            }, 2000)
        }else if(!matcherTel.matches()){//verifica que el telefono sea valido
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Telefono Invalido"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
                // Hacer el TextView invisible después de 2 segundos
            }, 2000)
        } else if(dao.updatePaciente(u,idPac)){//verifica que se haya modificado correctamente el usuario
            Toast.makeText(this,"Paciente Modificado Correctamente", Toast.LENGTH_LONG).show();
            val intent = Intent(this, CitasPaciente::class.java)
            intent.putExtra("pId", (idPac))
            intent.putExtra("pIdUsuario", (idUser))
            startActivity(intent);

        }
    }
}