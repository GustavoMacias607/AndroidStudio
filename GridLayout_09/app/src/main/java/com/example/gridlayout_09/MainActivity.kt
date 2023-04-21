package com.example.gridlayout_09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var txtCadena: TextView
    //Variables para las validaciones
    var cadena = ""
    var cantidad = 0
    var caracter = ""
    var cadenaInicial = ""
    var neg = false
    var ban = true
    var bani = true
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtCadena = findViewById(R.id.txtCadena)
    }

    fun presiono(v: View){ // funcion que se ejecuta al presionar un boton
        val vista = v as TextView
        when(vista.text){ //condiciona que tecla se presiono
            "C" ->{ // elimina todo si se presiona esta telcla
                cadena = ""
                cantidad = 0
                txtCadena.text = "0"
                ban = true
                bani = true
            }
            "+" -> { // suma los numeros de la operacion
                if (ban) {
                    cadenaInicial = cadena
                    caracter = "+"
                    cadena = ""
                    ban = false
                    }else {
                    cantidad += Integer.parseInt(cadena)
                    caracter = "+"
                    cadena = ""
                }
            }
            "-" -> { // resta los numeros de la operacion
                if (cadena.equals("")) {
                    cadenaInicial = cadena
                    caracter = "-"
                    neg = true
                } else {
                    if (ban) {
                        cadenaInicial = cadena
                        caracter = "-"
                        cadena = ""
                        ban = false
                    } else {
                        cantidad -= Integer.parseInt(cadena)
                        Log.e("Boton", "valor resta: ${cantidad}")
                        caracter = "-"
                        cadena = ""
                    }
                }
            }
            "=" ->{ // muestra el resultado de la operacion
                if(neg) { // valida si el primer numero es negativo
                    if (caracter.equals("+")){
                    cantidad = -Integer.parseInt(cadenaInicial) + Integer.parseInt(cadena)
                    } else{
                        cantidad = -Integer.parseInt(cadenaInicial) - Integer.parseInt(cadena)
                    }
                    neg = false
                }else {
                    Log.e("Boton", "Cadena en el igual: ${cadena}")
                    if (bani) {
                        Log.e("Boton", "Cadena operacion: ${cadenaInicial} - ${cadena}")
                        if (caracter.equals("+")){
                            cantidad = Integer.parseInt(cadenaInicial) + Integer.parseInt(cadena)
                        } else{
                            cantidad = Integer.parseInt(cadenaInicial) - Integer.parseInt(cadena)
                        }
                        txtCadena.text = cantidad.toString()
                        cadena = "0"
                        bani = false
                    } else {
                        if (caracter.equals("+")) {
                            cantidad += Integer.parseInt(cadena)
                        } else {
                            cantidad -= Integer.parseInt(cadena)
                        }
                        txtCadena.text = cantidad.toString()
                        Log.e("Boton", "valor resultado: ${cantidad}")
                        cadena = "0"

                    }

                }
                txtCadena.text = cantidad.toString()
            }else ->{// guarda el caracter escrito en la cadena
            cadena += vista.text as String
            txtCadena.text = cadena
            Log.e("Boton", "valor: ${cadena}")
            }




        }


    }
}