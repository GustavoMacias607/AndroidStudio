package com.example.veterinariahappypet

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ModificarCita : AppCompatActivity() {

    //variables globales
    lateinit var Imagencita: ImageView
    lateinit var btnFoto: Button
    lateinit var edMascota: TextView;
    lateinit var edPropietario: TextView;
    lateinit var edSintomas: TextView;
    lateinit var lblMsg: TextView;
    var imageBitmap : Bitmap? = null
    lateinit var c : Citas
    lateinit var p : RegPaciente
    lateinit var dao: daoCita
    lateinit var daoP: daoPaciente

    lateinit var handler: Handler;
    lateinit var calenda: CalendarView;
    var fecha = ""
    var idPaci = ""

    var idCita = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_cita)

        //asignacion de variables globales
        calenda = findViewById(R.id.Calendario);
        Imagencita = findViewById(R.id.fotito)
        btnFoto = findViewById(R.id.btnFoto)
        edMascota = findViewById(R.id.txtPropi);
        edPropietario = findViewById(R.id.txtCorre);
        edSintomas = findViewById(R.id.edContenido);
        lblMsg = findViewById(R.id.lblMsg3);
        handler = Handler(Looper.getMainLooper())
        dao = daoCita(this)
        daoP = daoPaciente(this)

        //obtener los getString de la clase Citas paciente
        idCita = intent.getStringExtra("idCita").toString()
        idPaci = intent.getStringExtra("idPac").toString()
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val idUser = preferencia.getString("IdUsuario", "")

        c = dao.getCitaById(Integer.parseInt(idCita),Integer.parseInt(idPaci))
        p = daoP.getPacienteById(Integer.parseInt(idPaci),Integer.parseInt(idUser))


        // Crear un objeto SimpleDateFormat para analizar la fecha
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = format.parse(c.fecha)

        // Crear una instancia de Calendar y establecer la fecha analizada
        val calendar = Calendar.getInstance()
        calendar.time = date


        //asignamos valores
        fecha = c.fecha
        edMascota.text = p.mascota;
        edPropietario.text = p.propietario;
        // Establecer la fecha en el CalendarView
        calenda.setDate(calendar.timeInMillis, true, true)

        edSintomas.text = c.sintomas;
        Imagencita.setImageBitmap(c.foto)
        imageBitmap = c.foto


        //evento al boton que toma la foto
        btnFoto.setOnClickListener{
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
        //evento de fecha
        calenda.setOnDateChangeListener { view, year, month, dayOfMonth ->
            fecha = "$dayOfMonth/${month + 1}/$year"
        }
    }

    //metodo para regresar a la clase anterior CitasPaciente
    fun Regresar(v: View){
        finish()
    }

    //Metodo para crear una cita
    fun UpdateCita(v: View) {

        val id = idPaci?.toIntOrNull() ?: 0
        val sintomas = edSintomas.text.toString()
        if (sintomas != "") {//validacion para que el campo sintomas tengo algo
            if (imageBitmap != null) {  //validacion de que se haya tomado una foto
                //Creando objeto de la clase y condiciones
                val u = Citas(id, fecha, sintomas, imageBitmap)
                if (dao.updateCita(u,idCita)) {
                    //si se inserto correctamente direcciona a la pagina de citasPaciente
                    Toast.makeText(this, "Cita Modificada Correctamente", Toast.LENGTH_LONG).show();
                    val intent = Intent(this, DetallesCita::class.java)
                    intent.putExtra("idCita", (idCita))
                    intent.putExtra("idPaciente", (idPaci))

                    startActivity(intent)
                }
            } else {
                lblMsg.visibility = View.VISIBLE
                lblMsg.text = "Favor de tomar una Foto"
                handler.postDelayed({
                    lblMsg.visibility = View.INVISIBLE
                    // Hacer el TextView invisible después de 2 segundos
                }, 2000)
            }
        } else {
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Campo Sintomas Vacio"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
                // Hacer el TextView invisible después de 2 segundos
            }, 2000)
        }


    }

    //guarda la imagen en la variable imageBitmap y asigna la foto al imagenview
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
            imageBitmap = intent?.extras?.get("data") as Bitmap // imagen
            Imagencita.setImageBitmap(imageBitmap)
        }
    }
}