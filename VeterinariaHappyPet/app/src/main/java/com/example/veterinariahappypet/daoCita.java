package com.example.veterinariahappypet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class daoCita {
//variables globales
    Context c;
    Citas ci;
    ArrayList<Citas> lista;
    SQLiteDatabase sql;
    String bd= "BDUsuarios";

    //consulta de creacion de tabla citas
    String tabla ="CREATE TABLE IF NOT EXISTS citas(id INTEGER PRIMARY KEY AUTOINCREMENT, idPaciente INTEGER, fecha DATE, sintomas TEXT, foto BLOB)";


    public daoCita(Context c){
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        ci=new Citas();

    }

    //metodo para insertar una cita
    public boolean insertCita(Citas p){

        ContentValues cv = new ContentValues();
        cv.put("idPaciente", p.getIdPaciente());
        cv.put("fecha", p.getFecha());
        cv.put("sintomas", p.getSintomas());
        // Convertir Bitmap a byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        p.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        cv.put("foto", byteArray);
        return (sql.insert("citas", null, cv) > 0);

    }

    public boolean updateCita(Citas p,String id){
        ContentValues cv = new ContentValues();
        cv.put("idPaciente", p.getIdPaciente());
        cv.put("fecha", p.getFecha());
        cv.put("sintomas", p.getSintomas());
        // Convertir Bitmap a byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        p.getFoto().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        cv.put("foto", byteArray);
        return (sql.update("citas", cv, "id = ?", new String[]{id}) > 0);
    }

    //metodo para consultar las citas
    public ArrayList<Citas> selectCitas(int idPaciente){
        ArrayList<Citas> lista = new ArrayList<Citas>();
        lista.clear();
        String consulta = "SELECT * FROM citas WHERE idPaciente = ?";
        // Usar el método rawQuery con la consulta parametrizada
        Cursor cr = sql.rawQuery(consulta, new String[] { String.valueOf(idPaciente) });
        if(cr!= null&&cr.moveToFirst()){
            do{
                Citas p = new Citas();
                p.setId(cr.getInt(0));
                p.setIdPaciente(cr.getInt(1));
                p.setFecha(cr.getString(2));
                p.setSintomas(cr.getString(3));
                // Obtener el byte array de la base de datos
                byte[] byteArray = cr.getBlob(4);
                // Convertir el byte array a Bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                p.setFoto(bitmap);
                lista.add(p);
            }while (cr.moveToNext());
        }
        return lista;
    }

    //metodo para obtener una cita por su id
    public Citas getCitaById(int id, int idPaciente){
        lista=selectCitas(idPaciente);
        for(Citas us: lista){
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }
}
