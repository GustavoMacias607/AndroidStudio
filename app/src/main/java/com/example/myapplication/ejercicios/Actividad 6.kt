package com.example.myapplication.ejercicios

/**
 * uso del when
 */

fun main(){
    println("Dame un numero: ")
    val x = readLine()!!.toInt();


    when(x){
        1 -> println("Es la opcion 1")
        2 -> println("Es la opcion 2")
        3 -> {
            println("Opcion 3")
            println("Es otra opcion")
        }
        4,5 -> println("son opcion 4 o 5");
         in 6..9 -> println("Es mayor que 5 pero menor que 9")
        !in 10.. 15 -> println("No esta en el rango")
        else -> println("No se reconose el valor")
    }

    val resultado = when(x){
        1 ->{
            println("asdad")
            "uno"
        }
        else -> "otro"
    }
    println("$resultado");
}