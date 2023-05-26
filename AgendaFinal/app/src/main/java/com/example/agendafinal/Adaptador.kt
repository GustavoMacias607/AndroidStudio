package com.example.agendafinal

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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Adaptador(private var contexto: Context, private val applicationContext: AppCompatActivity,private var listaPersonas: List<Persona>) :
    RecyclerView.Adapter<Adaptador.ViewHolderPersona>(){

    class ViewHolderPersona(item:View):
        RecyclerView.ViewHolder(item) {
        var txtId: TextView = item.findViewById(R.id.txtId)
        var txtNombre: TextView = item.findViewById(R.id.txtNombre)
        var txtApellido: TextView = item.findViewById(R.id.txtApellido)
        var txtEdad: TextView = item.findViewById(R.id.txtEdad)

    }
    // surve para especificar la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPersona {

        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ViewHolderPersona(layoutItem)
    }
    // sirve para decir cuantas vececes se va a repetir
    override fun getItemCount(): Int = listaPersonas.size

    // llena los datos en cada repeticion
    override fun onBindViewHolder(holder: ViewHolderPersona, position: Int) {
            val persona = listaPersonas[position]
            holder.txtNombre.text = persona.nombre
            holder.txtId.text = persona.id.toString()
            holder.txtApellido.text = persona.apellido
            holder.txtEdad.text = persona.edad//.toString()
    }
}