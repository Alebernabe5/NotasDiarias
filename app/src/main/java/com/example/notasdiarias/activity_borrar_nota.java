package com.example.notasdiarias;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.slider.BaseOnSliderTouchListener;

import java.util.ArrayList;

public class activity_borrar_nota extends AppCompatActivity {

    protected Button boton1, boton2;
    protected DataBaseSQL gdb;
    protected Intent pasarPantalla;
    protected ArrayList<String> listaNotas = new ArrayList<String>();
    protected ArrayAdapter<String> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_borrar_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lista1_listado), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializar la BD
        gdb = new DataBaseSQL(this);

        boton1 = (Button) findViewById(R.id.boton1_borrarNota);
        boton2 = (Button) findViewById(R.id.boton2_borrarNota);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity_borrar_nota.this, getString(R.string.string_codigo_borrarNota), Toast.LENGTH_SHORT).show();
                gdb.borrarTodasLasNota();
                // Actualizar lista de notas
                adaptador.clear();
                listaNotas = gdb.obtenerNotas();
                adaptador.addAll(listaNotas);
                adaptador.notifyDataSetChanged();

                // Retrasar el cambio de actividad
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pasarPantalla = new Intent(activity_borrar_nota.this, activity_listado.class);
                        startActivity(pasarPantalla);
                    }
                }, 1000); // Retrasa el cambio de actividad 1 segundo para que el Toast se vea
            }
        });

        //BOTON VOLVER
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla = new Intent(activity_borrar_nota.this, activity_listado.class); //Paso de pantalla a listado
                startActivity(pasarPantalla);
            }
        });




    }
}