package com.example.veterinariahappypet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoPaciente {
    Context c;
    RegPaciente p;
    ArrayList<RegPaciente> lista;
    SQLiteDatabase sql;
    String bd= "BDUsuarios";

    String tabla ="Create table if not exists paciente(id integer primary key autoincrement, idUsuario integer, mascota text, propietario text, telefono text, domicilio text, correo text)";


    public daoPaciente(Context c){
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        p=new RegPaciente();

    }

    public boolean insertPaciente(RegPaciente p){

            ContentValues cv = new ContentValues();
        cv.put("idUsuario", p.getIdUsuario());
            cv.put("mascota", p.getMascota());
            cv.put("propietario", p.getPropietario());
            cv.put("telefono", p.getTelefono());
            cv.put("domicilio", p.getDomicilio());
            cv.put("correo", p.getCorreo());
            return (sql.insert("paciente", null, cv) > 0);

    }
    /*
    public int buscar(String p){
        int x=0;
        lista = selectPacientes();
        for(RegPaciente us: lista){
            if(us.getPropietario().equals(p)){
                x++;
            }
        }
        return x;
    }
    */

    public ArrayList<RegPaciente> selectPacientes(int idUser){
        ArrayList<RegPaciente> lista = new ArrayList<RegPaciente>();
        lista.clear();
        String consulta = "SELECT * FROM paciente WHERE idUsuario = ?";

        // Usar el método rawQuery con la consulta parametrizada
        Cursor cr = sql.rawQuery(consulta, new String[] { String.valueOf(idUser) });
        if(cr!= null&&cr.moveToFirst()){
            do{
                RegPaciente p = new RegPaciente();
                p.setId(cr.getInt(0));
                p.setIdUsuario(cr.getInt(1));
                p.setMascota(cr.getString(2));
                p.setPropietario(cr.getString(3));
                p.setTelefono(cr.getString(4));
                p.setDomicilio(cr.getString(5));
                p.setCorreo(cr.getString(6));
                lista.add(p);

            }while (cr.moveToNext());
        }
        return lista;
    }

    public ArrayList<RegPaciente> selectPacientes(int idUser, String cadena){
        ArrayList<RegPaciente> lista = new ArrayList<RegPaciente>();
        lista.clear();
        String consulta = "SELECT * FROM paciente WHERE idUsuario = ? and propietario like ?";
        // Agregar "%" antes y después de la cadena para que funcione como un comodín
        String cadenaConComodines = "%" + cadena + "%";
        // Usar el método rawQuery con la consulta parametrizada
        Cursor cr = sql.rawQuery(consulta, new String[]{String.valueOf(idUser), cadenaConComodines});
        if(cr!= null&&cr.moveToFirst()){
            do{
                RegPaciente p = new RegPaciente();
                p.setId(cr.getInt(0));
                p.setIdUsuario(cr.getInt(1));
                p.setMascota(cr.getString(2));
                p.setPropietario(cr.getString(3));
                p.setTelefono(cr.getString(4));
                p.setDomicilio(cr.getString(5));
                p.setCorreo(cr.getString(6));
                lista.add(p);

            }while (cr.moveToNext());
        }
        return lista;
    }
/*
    public int login(String u, String p){
        int a = 0;
        Cursor cr = sql.rawQuery("select * from pacientes", null);
        if(cr!= null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while (cr.moveToNext());
        }
        return a;
    }
    */
/*
    public RegPaciente getPaciente(String u, String p){
        lista=selectPacientes();
        for(RegPaciente us: lista){
            if(us.getMascota().equals(u)&& us.getPropietario().equals(p) && us.getTelefono().equals(p) && us.getDomicilio().equals(p) && us.getCorreo().equals(p)){
                return us;
            }
        }
        return null;
    }
*/
    public RegPaciente getPacienteById(int id, int idUser){
        lista=selectPacientes(idUser);
        for(RegPaciente us: lista){
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }

}
