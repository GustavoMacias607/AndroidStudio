package com.example.veterinariahappypet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class EnviarCorreo : AppCompatActivity() {

    //variables globales
    lateinit var edPropi: TextView
    lateinit var edCorre: TextView
    lateinit var edAsunto: EditText;
    lateinit var edContenido: EditText;
    lateinit var lblMsg: TextView;
    lateinit var p : RegPaciente
    lateinit var daoP: daoPaciente
    lateinit var handler: Handler;
    var idPac = ""
    var idUser = ""
    var corr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_correo)
        //asignacion de variables globales
        edPropi = findViewById(R.id.txtPropi)
        edCorre = findViewById(R.id.txtCorre)
        edAsunto = findViewById(R.id.edAsunto)
        edContenido = findViewById(R.id.edContenido)
        lblMsg = findViewById(R.id.lblMsg3);
        daoP = daoPaciente(this)
        handler = Handler(Looper.getMainLooper())
        idPac = intent.getStringExtra("idPaciente").toString()
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        idUser = preferencia.getString("IdUsuario", "").toString()
        //buscamos el paciente
        p = daoP.getPacienteById(Integer.parseInt(idPac),Integer.parseInt(idUser))
        //asignamos valores
        edPropi.text = p.propietario;
        edCorre.text = p.Correo;

        //asignacion del cuerpo del mensaje
        corr = p.correo
    }
    fun Regresar(v: View){
        finish()
    }

    fun enviarCorreo(v:View){
        if(edAsunto.text.toString() == ""){
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Ingresa un Asunto"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
                // Hacer el TextView invisible después de 2 segundos
            }, 2000)
        }else if(edContenido.text.toString() == ""){
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Ingresa un Contenido"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
                // Hacer el TextView invisible después de 2 segundos
            }, 2000)
        }else{
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.data = Uri.parse("mailto:")
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(corr))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, edAsunto.text.toString())
            emailIntent.putExtra(Intent.EXTRA_TEXT, edContenido.text.toString())
            startActivity(Intent.createChooser(emailIntent, "Enviando Correo..."))
        }

    }

}