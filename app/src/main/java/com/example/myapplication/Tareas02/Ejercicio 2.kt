package com.example.myapplication.Tareas02

/**
 * Realiza un programa que permita leer la edad de una persona y despliegue en
pantalla si es menor o mayor de edad.
 */

fun main(){
    println("Ingresa una edad")
    val edad = readLine()!!.toInt()

    if (edad < 18){
        println("Es menor de edad")
    }else{
        println("Es mayor de edad")
    }
}