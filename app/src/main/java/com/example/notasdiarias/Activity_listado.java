package com.example.notasdiarias;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_listado extends AppCompatActivity {

    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lista1_listado), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //Este codigo indica que meu quiero asociar
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
            pasarPantalla = new Intent(Activity_listado.this, Activity_crear.class); //Paso de pantalla a listado
            startActivity(pasarPantalla);

            return true;
        }
        else if (id == R.id.menu_item_opciones)
        {
            //Paso pantalla a BorrarActivity
            pasarPantalla = new Intent(Activity_listado.this, activity_borrar_nota.class); //Paso de pantalla a listado
            startActivity(pasarPantalla);
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
}