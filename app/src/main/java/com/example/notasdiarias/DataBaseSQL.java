package com.example.notasdiarias;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {


    public DataBaseSQL(@Nullable Context context) {
        super(context, "notitas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT, nota TEXT)"); //Consulta crear tabla notas

        //Ejecuciones de muestra para tener en BBDD
        //db.execSQL("INSERT INTO notas VALUES(null,'nota ej 1')");
        //db.execSQL("INSERT INTO notas VALUES(null,'nota ej 2')");
        //db.execSQL("INSERT INTO notas VALUES(null,'nota ej 3')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //BORRAR NOTA
    public boolean borrarNota (String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id="+id);

        if (existeIdNotas(id))
        {
            return false;
        }
        return true;
    }

    //BORRAR TODAS LAS NOTAS
    public void borrarTodasLasNota ()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id>=0");

    }

    //INSERTAR NOTA
    public boolean insertarNota (String nota)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!existeNotas(nota))
        {
            db.execSQL("INSERT INTO notas VALUES(null,'"+nota+"')");
            return true;
        }
        return false;
       }


    //OBTENER NOTA
    public Nota obtenerNota(String id)
    {
        Nota not;

        if (existeIdNotas(id)) {
            SQLiteDatabase db = this.getReadableDatabase(); //Referencia a la BBDD
            Cursor cur = db.rawQuery("SELECT * FROM notas WHERE id=" + id, null);

            if (cur != null) {
                cur.moveToFirst(); //Me pongo en primera posic
                //Crear objeto con los constructores y los valores que hemos obtenido de la consulta
                not = new Nota(cur.getInt(0), cur.getString(1));
                return not;

            }
        }

        return null;
    }

    //OBTENER NOTAS
    public ArrayList<String> obtenerNotas()
    {
        ArrayList<String> notas= new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase(); //Referencia a la BBDD
        Cursor cur = db.rawQuery("SELECT * FROM notas",null);

        if (cur!=null)
        {
            cur.moveToFirst(); //Me pongo en primera posic
            while(!cur.isAfterLast())
            {
                //Codigo donde obtengo la informacion
                notas.add(cur.getString(0)+".-"+ cur.getString(1));


                cur.moveToNext();
            }
        }
        return notas;
    }

    //EXISTE NOTAS
    public boolean existeNotas(String nota)
    {
        SQLiteDatabase db = this.getReadableDatabase(); //Referencia a la BBDD
        Cursor cur = db.rawQuery("SELECT * FROM notas WHERE nota='"+nota+"'",null);

        if (cur!=null)
        {
            cur.moveToLast(); //Me pongo en primera posic
            if (cur.getCount()>0) {
                return true;
            }
        }
        return false;
    }

    //EXISTE ID NOTAS

    public boolean existeIdNotas(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase(); //Referencia a la BBDD
        Cursor cur = db.rawQuery("SELECT * FROM notas WHERE id='"+id+"'",null);

        if (cur!=null)
        {
            cur.moveToLast(); //Me pongo en primera posic
            if (cur.getCount()>0) {
                return true;
            }
        }
        return false;
    }



}
