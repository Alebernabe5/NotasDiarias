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
        db.execSQL("INSERT INTO notas VALUES(null,'nota ej 1')");
        db.execSQL("INSERT INTO notas VALUES(null,'nota ej 2')");
        db.execSQL("INSERT INTO notas VALUES(null,'nota ej 3')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

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



}
