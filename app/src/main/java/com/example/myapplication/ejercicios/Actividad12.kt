package com.example.myapplication.ejercicios

/**
 * Manejo herencia
 */

fun interface ClickRaton{
    fun ClickListener()
}
class Ventana:ClickRaton{
    var dimension = "200x300"

    fun imprimirDato(){
        println("La dimension de la ventana es $dimension")
    }

    override fun ClickListener() {
        println("Recibi un click")
    }

}

fun main(){
    val objHijo = Ventana()
    objHijo.imprimirDato()
    objHijo.ClickListener()
    println(objHijo)
    val objPadre = ClickRaton{
        println("Es otro click")
    }
    objPadre.ClickListener()
}