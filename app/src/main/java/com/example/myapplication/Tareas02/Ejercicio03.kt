package com.example.myapplication.Tareas02

import java.sql.SQLOutput

/**
 * Convertir las calificaciones alfabéticas A, B, C, D y E a calificaciones numéricas 10, 9,
8, 7 y 6, respectivamente
 */

fun main(){
    println("Ingresa la calificacion: ")
    val cal = readLine().toString();

    when(cal){
        "A" -> println("10");
        "B" -> println("9");
        "C" -> println("8");
        "D" -> println("7");
        "E" -> println("6");
        else -> println("No se reconoce el valor")
    }
}