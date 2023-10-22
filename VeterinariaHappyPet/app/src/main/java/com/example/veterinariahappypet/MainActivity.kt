package com.example.veterinariahappypet

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    //variables globales
    lateinit var edUser:EditText;
    lateinit var edPass:EditText;
    lateinit var lblMsg: TextView;
    lateinit var dao: daoUsuario
    lateinit var handler: Handler;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //asignacion de variables globales
        edUser = findViewById(R.id.edUser);
        edPass = findViewById(R.id.edPassword);
        lblMsg = findViewById(R.id.lblMsg2);
        handler = Handler(Looper.getMainLooper())
        dao = daoUsuario(this)

        val preferencia = getSharedPreferences("Usuario", MODE_PRIVATE)
        val id = preferencia.getString("IdUsuario", "")

        //direcciona a la pantalla de pacientes si un usuario tiene una sesion activa
        if(id.toString() != ""){
            val intent = Intent(this, Pacientes::class.java)
            startActivity(intent)
        }

    }

    //metodo que se ejecuta cuando se le da al boton de crear cuenta,
    //este direcciona a la pantalla para que el usuario cree una cuenta
    fun CrearCuenta(v: View){
        val intent = Intent(this, Registrar::class.java)
        startActivity(intent)
    }

    //metodo para

    fun Entrar(v: View){
        var u = edUser.text.toString();
        var p = edPass.text.toString();

        //valida si los campos estan vacios
        if(u == ""  || p == ""){
            if(u == ""){
                lblMsg.visibility = View.VISIBLE
                lblMsg.text = "Ingrese el Usuario"
                handler.postDelayed({
                    lblMsg.visibility = View.INVISIBLE
                }, 2000)
            }else if (p == ""){
                lblMsg.visibility = View.VISIBLE
                lblMsg.text = "Ingrese una Contraseña"
                handler.postDelayed({
                    lblMsg.visibility = View.INVISIBLE

                }, 2000)
            }
        }else if(dao.login(u,p)==1){ //inicia la sesion
            var ux : Usuario = dao.getUsuario(u,p);
            Toast.makeText(this, "Inicio de Sesion Correctamente", Toast.LENGTH_LONG).show();
            val preferencias = getSharedPreferences("Usuario", Context.MODE_PRIVATE)
            val editor = preferencias.edit()
            editor.putString("IdUsuario", ux.id.toString());
            editor.commit()
            val intent = Intent(this, Pacientes::class.java)
            startActivity(intent)

        }else if(dao.login(u,p)==0){// verifica si el usuario existe
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "El Usuaio No Existe"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE
            }, 2000)
        }

    }

}