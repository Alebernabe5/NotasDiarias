package com.example.notasdiarias;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class activity_listado extends AppCompatActivity {


    protected ListView lista1;
    protected DataBaseSQL gdb;
    protected ArrayList<String> listaNotas = new ArrayList<String>();
    protected ArrayAdapter<String> adaptador;
    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        lista1 = (ListView) findViewById(R.id.lista1_listado);

        gdb = new DataBaseSQL(this); //Aqui es donde se crea y conecta la BD

        listaNotas = gdb.obtenerNotas();
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaNotas);
        lista1.setAdapter(adaptador);

    }

    //MENU
    //Este codigo indica que me quiero asociar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_listado, menu);
        return true;
    }
    //Funcionalidad al menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.

        int id = item.getItemId();
        if (id == R.id.menu_item_crear)
        {
            //Paso pantalla a crearActivity
            pasarPantalla = new Intent(activity_listado.this, Activity_crear.class); //Paso de pantalla a listado
            startActivity(pasarPantalla);

            return true;
        }
        else if (id == R.id.menu_item_opciones)
        {
            //Paso pantalla a BorrarActivity
            pasarPantalla = new Intent(activity_listado.this, activity_borrar_nota.class); //Paso de pantalla a listado
            startActivity(pasarPantalla);
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }

    }

}