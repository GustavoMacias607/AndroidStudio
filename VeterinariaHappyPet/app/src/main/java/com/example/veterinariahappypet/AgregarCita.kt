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
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date
import java.util.regex.Pattern


class AgregarCita : AppCompatActivity() {

    lateinit var Imagencita: ImageView
    lateinit var btnFoto: Button

    lateinit var edMascota: TextView;
    lateinit var edPropietario: TextView;

    lateinit var edSintomas: EditText;
    lateinit var lblMsg: TextView;
    var imageBitmap : Bitmap? = null


    lateinit var dao: daoCita;
    lateinit var handler: Handler;

    lateinit var edFecha: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_cita)

        Imagencita = findViewById(R.id.ImgFoto)
        btnFoto = findViewById(R.id.btnFoto)
        edFecha = findViewById(R.id.dtFecha);
        edMascota = findViewById(R.id.txtMascotaC);
        edPropietario = findViewById(R.id.txtPropietarioC);

        edSintomas = findViewById(R.id.txtSintomas);
        lblMsg = findViewById(R.id.lblMsg3);

        handler = Handler(Looper.getMainLooper())
        dao = daoCita(this)

        btnFoto.setOnClickListener{
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }
    fun Regresar(v: View){
        finish()
    }


    fun validarFormatoFecha(fecha: String, formato: String): Boolean {
        val regex = Regex(formato)
        return regex.matches(fecha)
    }

    // Uso del método para validar un formato de fecha específico


    fun CrearCita(v: View) {
        val idString = intent.getStringExtra("idPac")
        val id = idString?.toIntOrNull() ?: 0
        // llenado de variables
        val fecha = edFecha.text.toString()
        val sintomas = edSintomas.text.toString()


        val formato = "\\d{2}/\\d{2}/\\d{4}" // Expresión regular para el formato "YYYY-MM-DD"
        val esFormatoValido = validarFormatoFecha(fecha, formato)



        if (esFormatoValido) {

            if (sintomas != "") {
                if (imageBitmap != null) {
                    //Creando objeto de la clase y condiciones
                    val u = Citas(id, fecha, sintomas, imageBitmap)


                    if (dao.insertCita(u)) {
                        Toast.makeText(this, "Cita Registrada Correctamente", Toast.LENGTH_LONG).show();
                        finish()

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

        }else{
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Fecha Invalida"
            Toast.makeText(this, "Formato: Dia/Mes/Año", Toast.LENGTH_LONG).show();
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
                // Hacer el TextView invisible después de 2 segundos
            }, 2000)
        }
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK){
            val intent = result.data
              imageBitmap = intent?.extras?.get("data") as Bitmap // imagen


            Imagencita.setImageBitmap(imageBitmap)
        }
    }
}