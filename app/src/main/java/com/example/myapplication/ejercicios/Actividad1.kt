package com.example.myapplication.ejercicios

/**
 * Metodo que permite ejecutar inicial
 */

fun main(){
    print("es una linea sin salto");
    println(" -- aqui continue y esta si salta");
    println("Una nueva linea");
    val nombre = "Gustavo";
    val dia = 10
    val lenguaje = "kotlin"

    val enunciado = """Mi nombre es: ${nombre} y estoy en el dia: ${dia}
         que voy a programar en ${lenguaje}""".trimIndent()
    println(enunciado)
}