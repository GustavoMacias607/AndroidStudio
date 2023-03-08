package com.example.myapplication.ejercicios

/**
 * uso del for
 */

fun main(){
    val tabla = 6;
    for(i in 1..10){
        println("$tabla x $i = ${tabla*i}")
    }
    println("-----")
    for (i in 10 downTo 0 step 2){
        println("$tabla x $i = ${tabla*i}")
    }
}