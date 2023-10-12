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

class Registrar : AppCompatActivity() {

    lateinit var edUser: EditText;
    lateinit var edName: EditText;
    lateinit var edPass: EditText;
    lateinit var edVeriPass: EditText;
    lateinit var lblMsg: TextView;

    lateinit var dao: daoUsuario
    lateinit var handler: Handler;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        edUser = findViewById(R.id.edUser);
        edName = findViewById(R.id.edName);
        edPass = findViewById(R.id.edPassword);
        edVeriPass = findViewById(R.id.edVerifyPassword);
        lblMsg = findViewById(R.id.lblMsg);
        handler = Handler(Looper.getMainLooper())
       dao = daoUsuario(this)

    }


    fun Regresar(v: View){
       finish()
    }

    fun CrearCuenta(v: View){

        if((edPass.text).toString() == (edVeriPass.text).toString()){

            val nombre = edName.text.toString()
            val usuario = edUser.text.toString()
            val password = edPass.text.toString()
            val u = Usuario(nombre, usuario, password)
            if(nombre == "" || usuario == ""|| password == "" || (edVeriPass.text).toString() == ""){
                lblMsg.visibility = View.VISIBLE
                lblMsg.text = "Campos Vacios"
                handler.postDelayed({
                    lblMsg.visibility = View.INVISIBLE
                                    // Hacer el TextView invisible después de 2 segundos
                }, 2000)
            }else if(dao.insertUsuario(u)){

               Toast.makeText(this,"Usuario Registrado Correctamente", Toast.LENGTH_LONG).show();
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent);
                finish();
            }else {
                lblMsg.visibility = View.VISIBLE
                lblMsg.text = "Usuario ya Registrado"
                handler.postDelayed({
                    lblMsg.visibility = View.INVISIBLE

                }, 2000)
                }
        }else if(edPass.length() < 7){
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "La contraseña debe ser de mas de 6 caracteres"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE

            }, 2000)
        } else{
            lblMsg.visibility = View.VISIBLE
            lblMsg.text = "Las Contraseñas No Coinciden"
            handler.postDelayed({
                lblMsg.visibility = View.INVISIBLE

            }, 2000)

        }


    }
}