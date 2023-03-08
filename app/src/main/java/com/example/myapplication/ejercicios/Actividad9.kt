package com.example.myapplication.ejercicios

/**
 * Rangos
 */
fun main(){

    println("Manejando rangos en un if")
    val x = 10
    if (x in 5..20){
        println("Se encontro el elemento")
    }
    val y = 5
    if (x !in 0..3){
        println("El operador no se encuentra")
    }
    if (3 in y..x){
        println("Aqui se debe encontrar")
    }else{
        println("No se encuentra")
    }
    for (i in y .. x){
        println("El valor es $i")
    }
}