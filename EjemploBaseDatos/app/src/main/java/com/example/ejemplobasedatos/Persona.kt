package com.example.ejemplobasedatos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Persona(
    @PrimaryKey val id:Int,
    @ColumnInfo(name = "nombre") val nombre:String,
    @ColumnInfo(name = "telefono") val telefono:String
)
