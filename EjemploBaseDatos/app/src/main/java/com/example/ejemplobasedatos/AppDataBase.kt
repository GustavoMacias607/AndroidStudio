package com.example.ejemplobasedatos

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Persona::class], version = 1, exportSchema = false)
abstract class AppDataBase:RoomDatabase(){
    ///todos los ado´s

    abstract fun personaDao():DaoPersona

}