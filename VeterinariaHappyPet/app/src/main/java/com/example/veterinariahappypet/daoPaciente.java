package com.example.veterinariahappypet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoPaciente {
    //variables globales
    Context c;
    RegPaciente p;
    ArrayList<RegPaciente> lista;
    SQLiteDatabase sql;
    String bd= "BDUsuarios";
    //consulta de creacion de tabla pacientes
    String tabla ="Create table if not exists paciente(id integer primary key autoincrement, idUsuario integer, mascota text, propietario text, telefono text, domicilio text, correo text)";


    public daoPaciente(Context c){
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        p=new RegPaciente();
    }

    //metodo para insertar un paciente
    public boolean insertPaciente(RegPaciente p){
            ContentValues cv = new ContentValues();
            cv.put("idUsuario", p.getIdUsuario());
            cv.put("mascota", p.getMascota());
            cv.put("propietario", p.getPropietario());
            cv.put("telefono", p.getTelefono());
            cv.put("domicilio", p.getDomicilio());
            cv.put("correo", p.getCorreo());
            return (sql.insert("paciente", null,cv ) > 0);
    }

    //metodo para modificar un paciente
    public boolean updatePaciente(RegPaciente p,String id){
        ContentValues cv = new ContentValues();
        cv.put("idUsuario", p.getIdUsuario());
        cv.put("mascota", p.getMascota());
        cv.put("propietario", p.getPropietario());
        cv.put("telefono", p.getTelefono());
        cv.put("domicilio", p.getDomicilio());
        cv.put("correo", p.getCorreo());
        return (sql.update("paciente", cv, "id = ?", new String[]{id}) > 0);
    }

    //metodo para consultar los pacientes
    public ArrayList<RegPaciente> selectPacientes(int idUser){
        ArrayList<RegPaciente> lista = new ArrayList<RegPaciente>();
        lista.clear();
        String consulta = "SELECT * FROM paciente WHERE idUsuario = ?";
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

    //metodo para buscar un paciente por id
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
