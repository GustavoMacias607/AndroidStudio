package com.example.veterinariahappypet;

public class Usuario {
    int Id;
    String Nombre, Usuario, Password;

    public Usuario() {
    }

    public Usuario(String nombre, String usuario, String password) {
        Nombre = nombre;
        Usuario = usuario;
        Password = password;
    }

    public boolean isNull(){
        if(Nombre.equals("") && Usuario.equals("") && Password.equals("")){
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

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", nombre='" + Nombre + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
