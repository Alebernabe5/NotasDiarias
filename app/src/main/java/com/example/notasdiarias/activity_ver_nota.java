package com.example.notasdiarias;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class activity_ver_nota extends AppCompatActivity {

    protected TextView texto1, texto2;
    protected Button boton1, boton2;
    protected DataBaseSQL gdb;
    protected Intent pasarPantalla;
    protected String [] partes;
    protected ArrayList<String> listaNotas = new ArrayList<String>();
    protected ArrayAdapter<String> adaptador;
    protected String paquete1 ="";
    protected Bundle extras;
    protected Nota not;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lista1_listado), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializar la BD
        gdb = new DataBaseSQL(this);

        texto1 = (TextView)findViewById(R.id.texto1_verNota);
        texto2 = (TextView)findViewById(R.id.texto2_verNota);
        boton1 = (Button) findViewById(R.id.boton1_verNota);
        boton2 = (Button) findViewById(R.id.boton2_verNota);

        //Almacena todos los paquetes
        extras = getIntent().getExtras();
        if (extras!=null)
        {
            paquete1 = extras.getString("ID");
            //Toast.makeText(this, "Paquete recibido "+paquete1, Toast.LENGTH_SHORT).show();
            //Obtengo la nota del paquete de la gbd
            not = gdb.obtenerNota(paquete1);
            texto2.setText(not.getNota());
        }
        else
        {
            //Toast.makeText(this, getString(R.string.string_codigo_verNota_noPaquetes), Toast.LENGTH_SHORT).show();
        }

        //BOTON VOLVER
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarPantalla = new Intent(activity_ver_nota.this, activity_listado.class); //Paso de pantalla a listado
                startActivity(pasarPantalla);
            }
        });


        //BOTON BORRAR

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (gdb.borrarNota(paquete1))
            {
                Toast.makeText(activity_ver_nota.this, getString(R.string.string_codigo_verNota_notaBorrada), Toast.LENGTH_SHORT).show();
                //Actualizar lista de notas
                adaptador.clear();
                listaNotas = gdb.obtenerNotas();
                adaptador.addAll(listaNotas);
                adaptador.notifyDataSetChanged();
                pasarPantalla = new Intent(activity_ver_nota.this, activity_listado.class); //Paso de pantalla a listado
                startActivity(pasarPantalla);

            }else
            {
                Toast.makeText(activity_ver_nota.this, getString(R.string.string_codigo_verNota_notaNoBorrada), Toast.LENGTH_SHORT).show();
            }


            }
        });


    }
}