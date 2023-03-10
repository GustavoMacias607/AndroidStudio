package com.example.ejerciciodado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var lblNumero: TextView
    lateinit var imgDado: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lblNumero = findViewById(R.id.txtNumero)
        imgDado = findViewById(R.id.imgDado)
    }
    /*
    Metodo para lanzar el clic
     */

    fun lanzaDado(v: View){
        Log.d("DadoApp", "Se presiono")//Print
        var numeroAleatorio = Random.nextInt(6)+1;
        val imagenAponer = when(numeroAleatorio){
            1-> {

                lblNumero.setText("1")
                R.drawable.dice_1;
            }
            2->{
                lblNumero.setText("2")
                R.drawable.dice_2;}
            3->{
                lblNumero.setText("3");
                R.drawable.dice_3}
            4->{
                lblNumero.setText("4");
                R.drawable.dice_4}
            5->{
                lblNumero.setText("5");
                R.drawable.dice_5}
            else->{
                lblNumero.setText("6");
                R.drawable.dice_6}
        }
        imgDado.setImageResource(imagenAponer);
    }
}