package com.example.myapplication.ejercicios


/**
 * Uso del if cuando regresamos valor
 */
fun main(){
println("Dame un numero")
    val num = readLine()!!.toInt();
    var resultado :String

    resultado = if (num%2==0) "Par" else "Impar";
    val num2 = if (num%2==0) 10 else 30
    println("El valor en cadena es ${resultado}")
    println("El valor del numero es ${num2}")

}
