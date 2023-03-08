package com.example.myapplication.Tarea3

/**
 * Hacer un Script que pida 10 calificaciones y utilice el metodo average para mostrar el
promedio de las calificaciones.
 */

fun main(){

    var lista = Array(10) {_->0}
    for (i in 1..10){
        println("Ingresa la calificacion $i")
        var cal = readLine()!!.toInt()
        lista[i-1] = cal;
    }
    println("El promedio es: ${lista.average()}")
}