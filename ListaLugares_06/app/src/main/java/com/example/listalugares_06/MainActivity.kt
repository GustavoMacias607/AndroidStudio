package com.example.listalugares_06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val lugares = arrayOf("Tequila", "Magdalena", "Arenal", "Guadalajara", "Guzman", "Sayula",
        "Vallarta", "Amatitan", "Zacoalco", "Chetumal", "San Jose", "Hostotipaquillo", "Tala" )
    lateinit var txtTexto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtTexto = findViewById(R.id.txtTexto)
        val listView: ListView = findViewById(R.id.list_view_lugares)
        val adaptador = ArrayAdapter(this,R.layout.list_item, lugares)
        listView.adapter = adaptador

        listView.onItemClickListener = object: AdapterView.OnItemClickListener{

            override fun onItemClick(p0: AdapterView<*>?, p1:View?, p2:Int, p3:Long){
                val item = listView.getItemAtPosition(p2) as String;
                Log.e("Valor Lista", item)
                Toast.makeText(applicationContext, "Elvalor es: $item", Toast.LENGTH_LONG).show()
                txtTexto.text = "Valor seleccionado: $item";
            }
        }


    }
}