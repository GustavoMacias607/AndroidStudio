package com.example.agendafinal

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
interface IPersona {
    @GET("wsLeer.php")
    fun getListaPersonas(): Call<List<Persona>>
    @POST("agrega.php")
    fun agregarPersona(@Body persona: Persona): Call<Resultado>
}