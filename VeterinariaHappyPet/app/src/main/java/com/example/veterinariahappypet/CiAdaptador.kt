package com.example.veterinariahappypet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CiAdaptador (private var contexto: Context, private var id:Int) :
    RecyclerView.Adapter<CiAdaptador.ViewHolderCitas>() {

    private var lista: ArrayList<Citas>

    init {
        // Inicializa la lista obteniendo los datos del daoPaciente
            val dao = daoCita(contexto)
            lista = dao.selectCitas(id)
    }

    class ViewHolderCitas(item: View) :
        RecyclerView.ViewHolder(item) {
        var dtFecha: TextView = item.findViewById(R.id.dtFecha5)
        var nCita: TextView = item.findViewById(R.id.nCita)
        var imFoto: ImageView = item.findViewById(R.id.imagenCitas)

    }
    // sirve para especificar la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCitas {
        val layoutItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_citas, parent, false)
        return ViewHolderCitas(layoutItem)
    }

    // sirve para decir cuantas vececes se va a repetir
    override fun getItemCount(): Int = lista.size


    // llena los datos en cada repeticion
    override fun onBindViewHolder(holder: ViewHolderCitas, position: Int) {
        val citas = lista[position]
        val activity = contexto as CitasPaciente
        holder.dtFecha.text = "Fecha: " + citas.fecha
        holder.nCita.text = "No. Cita: " + (position+1);
        holder.imFoto.setImageBitmap(citas.foto)

        /*
        holder.itemView.setOnClickListener {
            activity.onClickPaciente(paciente)
        }

         */
    }
}