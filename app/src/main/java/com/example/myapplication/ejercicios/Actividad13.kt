package com.example.myapplication.ejercicios

data class Alumno(var name:String, val numeroControl:String)

fun main(){
    val al1 = Alumno("Luis" ,"123123")
    al1.name = "Jose"
    //al1.numeroControl = "123123"
    println(al1)
    println("""
        Numero Control: ${al1.numeroControl}
        Nombre: ${al1.name}
    """.trimIndent())
}