package com.example.notasdiarias;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    protected TextView texto1;
    protected Intent pasarPantalla;
    protected TimerTask tt;
    protected Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lista1_listado), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1 = (TextView) findViewById(R.id.texto1_main);

        tt= new TimerTask() {
            @Override
            public void run() {
                pasarPantalla = new Intent(MainActivity.this, Activity_listado.class); //Paso de pantalla a listado
                startActivity(pasarPantalla);
            }
        };
        t = new Timer();
        t.schedule(tt,2000); //Metodo que llama a la tarea en x seg
    }
}