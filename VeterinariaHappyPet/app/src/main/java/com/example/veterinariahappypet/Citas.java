package com.example.veterinariahappypet;

import android.graphics.Bitmap;

public class Citas {
    int Id, IdPaciente;
    String Fecha, Sintomas;
    Bitmap Foto;

    public Citas() {
    }

    public Citas(int idPaciente, String fecha, String sintomas, Bitmap foto) {

        IdPaciente = idPaciente;
        Fecha = fecha;
        Sintomas = sintomas;
        Foto = foto;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdPaciente() {
        return IdPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        IdPaciente = idPaciente;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String sintomas) {
        Sintomas = sintomas;
    }

    public Bitmap getFoto() {
        return Foto;
    }

    public void setFoto(Bitmap foto) {
        Foto = foto;
    }
}
