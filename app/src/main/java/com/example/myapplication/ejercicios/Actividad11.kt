package com.example.myapplication.ejercicios

class Persona(clave: String = "NODEF",nombre:String = "NODEF", saldo: Int){
    var clave:String = ""
    var nombre = ""
    var estado = false
    var usuario = ""
    var password = ""
    var saldo = 0
    init{
        this.clave = clave
        this.nombre = nombre
        this.saldo = saldo
    }
    fun autentificacion(usuario:String, password:String):Boolean{
        this.usuario = usuario
        this.estado = password == "123"
        this.password = password
        return estado
    }
    fun mostrarInformacion(){
        val msg = """
            Clave: $clave
            Nombre: $nombre
            Estado: $estado
            Usuario: $usuario
            Password: $password
            Saldo: $saldo
            """.trimIndent()
        println(msg)
    }
    fun saludo(){
        println("Es un saludo")
    }
}

fun main(){
    println("Es una prueba con clases")
    val personaObjeto = Persona("001","Gustavo", 23)
    personaObjeto.saludo()
    val autentificado = personaObjeto.autentificacion("GMacias","123")
    if (autentificado)
        println("Correcto")
    else
        println("Incorrecto")
    personaObjeto.mostrarInformacion()
}