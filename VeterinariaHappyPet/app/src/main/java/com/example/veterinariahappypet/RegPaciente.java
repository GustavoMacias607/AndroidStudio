package com.example.veterinariahappypet;

import java.util.concurrent.TransferQueue;

public class RegPaciente {
    int Id,IdUsuario;
    String Mascota, Propietario, Telefono, Domicilio, Correo;

    public RegPaciente() {

    }
    public RegPaciente(int idUsuario,String mascota, String propietario, String telefono, String domicilio, String correo) {
        IdUsuario = idUsuario;
        Mascota = mascota;
        Propietario = propietario;
        Telefono = telefono;
        Domicilio = domicilio;
        Correo = correo;
    }

    public boolean isNull(){
        if(Mascota.equals("") && Propietario.equals("") && Telefono.equals("") && Domicilio.equals("") && Correo.equals("")){
            return false;
        }else{
            return true;
        }
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.IdUsuario = idUsuario;
    }

    public String getMascota() {
        return Mascota;
    }

    public void setMascota(String mascota) {
        Mascota = mascota;
    }

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String propietario) {
        Propietario = propietario;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
