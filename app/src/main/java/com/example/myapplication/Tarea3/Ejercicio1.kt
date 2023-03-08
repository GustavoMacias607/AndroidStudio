package com.example.myapplication.Tarea3

import java.sql.SQLOutput

/**
 * Hacer un script que tenga un menu con las opciones 1) Agregar, 2) mostrar y 3) salir,
la función tendra que mostrar el menu mientras no se de la opción salir, cuando se da
agregar pedira un nombre y lo almacenara una lista, cuando se de la opción mostrar
tendra que mostrar la cantidad de nombres almacenados en la lista.
 */
fun main(){
var opc = 0
    val lista = mutableListOf("")
    do{
        println("***MENU***")
        println("1) Agregar")
        println("2) Mostrar")
        println("3) Salir")
        opc = readLine()!!.toInt()
        when(opc){
            1-> {
                println("Ingrese un nombre")
                var nombre = readLine()!!.toString()
                lista.add(nombre)
            }
            2 -> println("La cantidad de valores es:  ${lista.size-1}")

            else -> println("No se reconoce el valor")
        }
    }while(opc != 3)
}