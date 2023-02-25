package Tareas02



    /**
     * Programa que despliegue las primeras 10 tablas de multiplicar
     */

    fun main(){

        for(tabla in 1 .. 10){
            for (i in 1..10){
                println("$tabla x $i = ${tabla*i}")

            }
            println("--------------------------")
        }
    }
