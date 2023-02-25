package Tareas02

/**
 * Programa que obtenga la factorial de un número leído
 */
fun main() {

    println("Ingresa un numero")
    var num = readLine()!!.toInt();
    var valor = 1;
    for (i in 1 .. num){
        valor *= i;

    }
    println("El factorial es: " + valor)
}