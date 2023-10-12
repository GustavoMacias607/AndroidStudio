package com.example.veterinariahappypet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class PaAdaptador (private var contexto: Context, private var id:Int, private var cadena:String = "") :
    RecyclerView.Adapter<PaAdaptador.ViewHolderPaciente>(){

    private var lista: ArrayList<RegPaciente>

    init {
        // Inicializa la lista obteniendo los datos del daoPaciente
        if(cadena != ""){
            val dao = daoPaciente(contexto)
            lista = dao.selectPacientes(id, cadena)

        }else{
            val dao = daoPaciente(contexto)
            lista = dao.selectPacientes(id)
        }

    }
    class ViewHolderPaciente(item: View):
        RecyclerView.ViewHolder(item) {
        var txNombre: TextView = item.findViewById(R.id.txNombre)
        var txDomicilio: TextView = item.findViewById(R.id.txDomicilio)
        var txTelefono: TextView = item.findViewById(R.id.txTelefono)
    }

    // surve para especificar la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPaciente {
        val layoutItem = LayoutInflater.from(parent.context).inflate(R.layout.mostrarpacientes,parent,false)
        return ViewHolderPaciente(layoutItem)
    }
    // sirve para decir cuantas vececes se va a repetir
    override fun getItemCount(): Int = lista.size


    // llena los datos en cada repeticion
    override fun onBindViewHolder(holder: ViewHolderPaciente, position: Int) {
        val paciente = lista[position]
        val activity  = contexto as Pacientes
        holder.txNombre.text = "Propietario: " +  paciente.propietario
        holder.txTelefono.text = "Telefono: " +  paciente.telefono
        holder.txDomicilio.text = "Mascota: " +  paciente.mascota
        /*holder.itemView.setOnClickListener{
            activity.onClickPaciente(paciente)
        }
        */

    } }