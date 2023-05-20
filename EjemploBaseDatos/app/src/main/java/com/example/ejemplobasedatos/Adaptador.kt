package com.example.ejemplobasedatos

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager


class Adaptador(private var contexto: Context, private val applicationContext: AppCompatActivity) :
     RecyclerView.Adapter<Adaptador.ViewHolderPersona>(){

    //llena la lista
        val db = Room.databaseBuilder( applicationContext, AppDataBase::class.java, "contactos").allowMainThreadQueries().build()
        val personas:List<Persona> = db.personaDao().getAll()


    class ViewHolderPersona(item:View):
        RecyclerView.ViewHolder(item) {
        var txtId: TextView = item.findViewById(R.id.txtId)
        var txtNombre: TextView = item.findViewById(R.id.txtNombre)
        var txtTelefono: TextView = item.findViewById(R.id.txtTelefono)

    }

    // surve para especificar la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPersona {

        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ViewHolderPersona(layoutItem)
    }
    // sirve para decir cuantas vececes se va a repetir
    override fun getItemCount(): Int = personas.size


    // llena los datos en cada repeticion
    override fun onBindViewHolder(holder: ViewHolderPersona, position: Int) {
        val persona = personas[position]
        holder.txtNombre.text = persona.nombre
        holder.txtId.text = persona.id.toString()
        holder.txtTelefono.text = persona.telefono//.toString()

    }
     }


