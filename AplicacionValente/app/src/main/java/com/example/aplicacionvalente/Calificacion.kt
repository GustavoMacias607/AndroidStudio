package com.example.aplicacionvalente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class Calificacion : AppCompatActivity() {
    // creas las variables que se usaran
    lateinit var txtCal: TextView
    lateinit var imgRe: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificacion)

        //busco el componente que se referenciara en la variable para poder manipularla

        txtCal = findViewById(R.id.txtValor) //txtValor es el nombre del id del textview
        imgRe = findViewById(R.id.imgReferencia) //imgReferencia es el nombre del id de la imagen
    }

//metodo que se ejecuta al cargar la vista
//en este metodo se toma el valor que le llego en este caso la palabra aprobado, reprobado, excelente
//y modifica el TextView agregandole la palabra que llego y al mismo tiempo poniendole el color correspondiente

    override fun onResume() {
        super.onResume()
        val ca = intent.getStringExtra("calif") //obtengo el valor que se mando
        txtCal.setText(ca) //le establezco el texto al TextView, si llego reprobado el textview dira reprobado


        if (ca == "aprobado") {//comprueba si lo que llego fue aprobado y cambia el color del texto y la imagen correspondiente
            txtCal.setTextColor(ContextCompat.getColor(this, R.color.azul))
            imgRe.setImageResource(R.drawable.aprobado);
        }else if (ca == "reprobado"){//comprueba si lo que llego fue reprobado y cambia el color del texto y la imagen correspondiente
            txtCal.setTextColor(ContextCompat.getColor(this, R.color.rojo))
            imgRe.setImageResource(R.drawable.reprobado);
        }else{//comprueba si lo que llego fue excelente y cambia el color del texto y la imagen correspondiente
            txtCal.setTextColor(ContextCompat.getColor(this, R.color.verde))
            imgRe.setImageResource(R.drawable.exelente);
        }

    }

    fun salir(v: View){ // regresa a la pantalla anterior
        finish()
    }
}