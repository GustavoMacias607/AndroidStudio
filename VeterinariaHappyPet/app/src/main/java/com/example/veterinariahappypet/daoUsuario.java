package com.example.veterinariahappypet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {
    //variables globales
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd= "BDUsuarios";

    //consulta de creacion de tabla usuario
    String tabla ="Create table if not exists usuario(id integer primary key autoincrement, usuario text, pass text, nombre text)";


           // "delete from usuario";



    public daoUsuario(Context c){
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u=new Usuario();

    }

    //metodo para insertar un usuario
    public boolean insertUsuario(Usuario u){
        if(buscar(u.getUsuario()) == 0){
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("pass", u.getPassword());
            cv.put("nombre", u.getNombre());
            return (sql.insert("usuario", null, cv) > 0);
        }else{
            return false;
        }
    }
    //metodo para buscar un usuario y comprobar si este ya existe
    public int buscar(String u){
        int x=0;
        lista = selectUsuarios();
        for(Usuario us: lista){
            if(us.getUsuario().equals(u)){
                x++;
            }
        }
        return x;
    }

    //metodo para consultar los usuarios
    public ArrayList<Usuario> selectUsuarios(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!= null&&cr.moveToFirst()){
            do{
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                lista.add(u);
            }while (cr.moveToNext());
        }
        return lista;
    }

    //metodo para iniciar sesion
    public int login(String u, String p){
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!= null&&cr.moveToFirst()){
            do{
                if(cr.getString(1).equals(u)&&cr.getString(2).equals(p)){
                    a++;
                }
            }while (cr.moveToNext());
        }
        return a;
    }

    //metodo para obtener un usuario
    public Usuario getUsuario(String u, String p){
            lista=selectUsuarios();
            for(Usuario us: lista){
                if(us.getUsuario().equals(u)&& us.getPassword().equals(p)){
                    return us;
                }
            }
            return null;
    }

    //metodo para buscar un usuario por id
    public Usuario getUsuarioById(int id){
        lista=selectUsuarios();
        for(Usuario us: lista){
            if(us.getId()==id){
                return us;
            }
        }
        return null;
    }
}
