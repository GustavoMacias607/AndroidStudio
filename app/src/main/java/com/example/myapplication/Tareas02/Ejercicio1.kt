package com.example.myapplication.Tareas02


    /**
     * Utilizacion de las estructuras de control
     */


    fun main(){

        println("Uso del if");
        println("Dame una hora");
        val hora = readLine()!!.toInt();
        var hora12 : String
        ///Condiciones
        if (hora==0)

            hora12 = "12:00 AM";
        else if(hora < 0)
            hora12 = "Hora erronea"
        else if(hora < 12)
            hora12 = "$hora:00 AM"
        else if(hora == 12)
            hora12 = "12:00 PM"
        else if(hora < 24)
            hora12 = "${hora-12}:00 PM"

        else{
            hora12 = "Hora erronea";
        }

        println("La hora es: ${hora12}")
    }