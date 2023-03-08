package com.example.myapplication.Tareas02
/**
 * Programa que lea una serie de números y se detenga cuando el número leído sea un 0.
 */
fun main(){
    var num = 2;
    while (num != 0){
        println("Ingresa un numero: ")
        num = readLine()!!.toInt();
    }

}