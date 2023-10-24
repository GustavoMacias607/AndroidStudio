package com.example.veterinariahappypet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager.BadTokenException
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Pacientes : AppCompatActivity() {

    //variables globales
    lateinit var dao: daoUsuario
    lateinit var daoP: daoPaciente
    lateinit var lblSesion : TextView
    lateinit var rvPacientes: RecyclerView
    lateinit var textVieww : TextView
    lateinit var edBuscar: EditText
    lateinit var flFondo : FrameLayout
    lateinit var ux : Usuario

    lateinit var btnConf : ImageView
    lateinit var btnCerrarSesion : Button
    var idd:Int = 0
    var desa = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacientes)

        //asignacion de variables globales
        rvPacientes = findViewById(R.id.rvPacientes)
        dao = daoUsuario(this)
        daoP = daoPaciente(this)
        textVieww = findViewById(R.id.textView4)
        edBuscar = findViewById(R.id.txtBuscar)
        flFondo = findViewById(R.id.fLFondo)

        btnConf = findViewById(R.id.btnConf)
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)
        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val id = preferencia.getString("IdUsuario", "")
        idd = Integer.parseInt(id)
        lblSesion = findViewById(R.id.lblSesion)
        ux = dao.getUsuarioById(Integer.parseInt(id))
        lblSesion.text = ux.nombre
        desa = false
    }

    //metodo que llena el RecyclerView con la lista de pacientes
    override fun onResume() {
        super.onResume()
        val valorthis = this
        val adaptador = PaAdaptador(this,idd)
        rvPacientes.adapter = adaptador
        rvPacientes.layoutManager = LinearLayoutManager(this)
        edBuscar.addTextChangedListener(object : TextWatcher { // evento que detecta si hubo un cambio en el txt de buscar
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // actualiza la lista buscando el propietario
                val adaptador2 = PaAdaptador(valorthis, idd, charSequence.toString())
                rvPacientes.adapter = adaptador2
                rvPacientes.layoutManager = LinearLayoutManager(valorthis)
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }

    //metodo que se ejecuta al darle clic a un paciente en el RecyclerView
    fun onClickPaciente(paciente:RegPaciente){
        val intent = Intent(this, CitasPaciente::class.java)
        intent.putExtra("pId", (paciente.Id).toString())
        intent.putExtra("pIdUsuario", (paciente.IdUsuario).toString())
        intent.putExtra("pMascota", (paciente.Mascota).toString())
        intent.putExtra("pPropietario", paciente.propietario)
        intent.putExtra("pTelefono", paciente.telefono)
        intent.putExtra("pCorreo", paciente.correo)
        intent.putExtra("pDomicilio", paciente.domicilio)
        startActivity(intent)
    }


    //metodo que se ejecuta al darle al boton de cerrar sesion, este cierra la sesion y direcciona al login
    fun CerrarSesion(v: View){
        val preferencias = getSharedPreferences("Usuario", Context.MODE_PRIVATE)
        val editor = preferencias.edit()
        editor.putString("IdUsuario", "");
        editor.commit()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    //metodo que se ejecuta al darle clic a crear paciente direccionando a la pantalla de crear paciente
    fun CrearPaciente(v: View){
        val intent = Intent(this, AgregarPaciente::class.java)
        startActivity(intent)
    }

    //Metodo para configuracion
    fun conf(v:View){
        if (desa) {

            btnCerrarSesion.visibility = View.INVISIBLE
            flFondo.visibility = View.INVISIBLE
            btnConf.setImageResource(R.drawable.del)
            desa = false

        } else {

            btnCerrarSesion.visibility = View.VISIBLE
            flFondo.visibility = View.VISIBLE
            btnConf.setImageResource(R.drawable.deta)
            desa = true
        }
    }
}