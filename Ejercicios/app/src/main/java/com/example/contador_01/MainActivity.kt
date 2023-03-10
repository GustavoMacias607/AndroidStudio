package com.example.contador_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var cont:Int = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cont = 0;
        val txtValor = findViewById<TextView>(R.id.tvValor);
        val btnContador = findViewById<Button>(R.id.btnContar);
        val btnReset = findViewById<Button>(R.id.btnReset);

        var ban = true; // bandera

        btnContador.setOnClickListener{ // evento al precionar el contador
            //Toast.makeText(this,"Preiono", Toast.LENGTH_LONG).show();

            if (cont == 0){ // if para controlar si aumenta o disminuye
                ban = true;
            }else if(cont == 10){
                ban = false;
            }

            if (ban){ // dependiendo del valor de la variable aumenta o disminuye el contador
                cont = cont.inc();
                btnContador.setText("Aumentar");
            }else {
                cont = cont.dec();
                btnContador.setText("Disminuir");
            }
            txtValor.text = "$cont";
        }
        btnReset.setOnClickListener{ // evento al precionar el boton de resetear, cambia el contador a 0
            cont = 0;
            txtValor.text = "$cont";
            Toast.makeText(this,"Se ha reseteado el contador", Toast.LENGTH_LONG).show();
            btnContador.setText("Aumentar");
        }
    }

}