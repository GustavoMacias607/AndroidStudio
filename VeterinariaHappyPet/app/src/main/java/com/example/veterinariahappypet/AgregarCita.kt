package com.example.veterinariahappypet


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class AgregarCita : AppCompatActivity() {

    //variables globales
    lateinit var Imagencita: ImageView
    lateinit var btnFoto: Button
    lateinit var edMascota: TextView;
    lateinit var edPropietario: TextView;
    lateinit var edSintomas: EditText;
    lateinit var lblMsg: TextView;
    var imageBitmap : Bitmap? = null
    lateinit var dao: daoCita;
    lateinit var handler: Handler;
    lateinit var calenda: CalendarView;
    var fecha = ""
    var idString = ""
    var idUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cita)

        //asignacion de variables globales
        calenda = findViewById(R.id.Calendario);
        Imagencita = findViewById(R.id.fotito)
        btnFoto = findViewById(R.id.btnFoto)
        edMascota = findViewById(R.id.txtMascotaC);
        edPropietario = findViewById(R.id.txtPropietarioC);
        edSintomas = findViewById(R.id.txtSintomas);
        lblMsg = findViewById(R.id.lblMsg3);
        handler = Handler(Looper.getMainLooper())
        dao = daoCita(this)

        //obtener los getString de la clase Citas paciente
        idString = intent.getStringExtra("idPac").toString()
        idUser = intent.getStringExtra("pIdUsuario").toString()

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
    fun CrearCita(v: View) {
        val idString = intent.getStringExtra("idPac") //obtener el id paciente
        val id = idString?.toIntOrNull() ?: 0
        val sintomas = edSintomas.text.toString()
            if (sintomas != "") {//validacion para que el campo sintomas tengo algo
                if (imageBitmap != null) {  //validacion de que se haya tomado una foto
                    //Creando objeto de la clase y condiciones
                    val u = Citas(id, fecha, sintomas, imageBitmap)
                    if (dao.insertCita(u)) {
                        //si se inserto correctamente direcciona a la pagina de citasPaciente
                        Toast.makeText(this, "Cita Registrada Correctamente", Toast.LENGTH_LONG).show();
                        val intent = Intent(this, CitasPaciente::class.java)
                        intent.putExtra("pId", (idString))
                        intent.putExtra("pIdUsuario", (idUser))
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