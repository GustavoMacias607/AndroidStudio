package com.example.agendafinal

import com.google.gson.annotations.SerializedName

data class Persona(
    @SerializedName("id") val id:Int,
    @SerializedName("nombre") val nombre:String,
    @SerializedName("apellido") val apellido:String,
    @SerializedName("edad") val edad:String
)
