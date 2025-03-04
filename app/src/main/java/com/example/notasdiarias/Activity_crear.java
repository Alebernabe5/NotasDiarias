package com.example.notasdiarias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_crear extends AppCompatActivity {

    protected TextView texto1;
    protected EditText caja1;
    protected Button boton1, boton2;
    protected String contenidoCaja1="";
    protected DataBaseSQL gdb;
    protected Intent pasarPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lista1_listado), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        texto1 = (TextView)findViewById(R.id.texto1_crear);
        caja1 = (EditText)findViewById(R.id.caja1_crear);
        boton1 = (Button) findViewById(R.id.boton1_crear);  //Boton de atras
        boton2 = (Button) findViewById(R.id.boton2_crear);  //Boton de crear

        //Inicializar la BD
        gdb = new DataBaseSQL(this);

        //BOTON VOLVER
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla = new Intent(Activity_crear.this, activity_listado.class); //Paso de pantalla a listado
                startActivity(pasarPantalla);
            }
        });

        //BOTON DE CREAR
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
                contenidoCaja1 = caja1.getText().toString();
                if(contenidoCaja1.equalsIgnoreCase(""))
                {
                    Toast.makeText(Activity_crear.this, "Nota obligatoria", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    caja1.setText(""); //Borro el contenido de la caja
                   if (gdb.insertarNota(contenidoCaja1))
                   {
                       Toast.makeText(Activity_crear.this, "Nota creada correctamente", Toast.LENGTH_SHORT).show();
                       pasarPantalla = new Intent(Activity_crear.this, activity_listado.class); //Paso de pantalla a listado
                       startActivity(pasarPantalla);
                   }
                   else
                   {
                       Toast.makeText(Activity_crear.this, "No se ha podido añadir la nota", Toast.LENGTH_SHORT).show();
                                          }

                }
            }
        });


    }
}