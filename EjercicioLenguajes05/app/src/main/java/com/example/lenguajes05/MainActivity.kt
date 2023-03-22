package com.example.lenguajes05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var txttitulo: TextView
    lateinit var spLenguajes: Spinner
    lateinit var txtcaracteristica : TextView
    var lenguajes = arrayOf("JAVA", "KOTLIN", "PHP", "SWIFT", "C#", "C++", "Dart", "JS")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        txttitulo = findViewById(R.id.tituloVentana)
        spLenguajes = findViewById(R.id.sp_Lenguajes)
        txtcaracteristica = findViewById(R.id.txtCaracteristica)
        // crear adaptador
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lenguajes)
        //como se comportara
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spLenguajes.adapter = adaptador
        spLenguajes.onItemSelectedListener = this
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicion: Int, p3: Long) {
        val valorSeleccionado = lenguajes[posicion]
        txttitulo.text = valorSeleccionado;
        when(valorSeleccionado){
            "JAVA"->
                txtcaracteristica.text = "Orientado a objetos"
            "KOTLIN"->
                txtcaracteristica.text = "Interoperabilidad con código Java"
            "PHP" ->
                txtcaracteristica.text = "fácil, potente y profesional."
            "SWIFT" ->
                txtcaracteristica.text = "lenguaje rápido y eficiente"
            "C#"->
                txtcaracteristica.text = "Sintaxis sencilla que facilita al desarrollador la escritura de código"
            "C++"->
                txtcaracteristica.text = "Es portátil y tiene un gran número de compiladores en diferentes plataformas y sistemas operativos"
            "Dart"->
                txtcaracteristica.text = "Programación estructurada y flexible"
            "JS"->
                txtcaracteristica.text = "Posee una estructura sencilla que lo vuelve más fácil de aprender e implementar"
        }

        Toast.makeText(this,"Selecciono $valorSeleccionado", Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}