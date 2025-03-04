package com.example.notasdiarias;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_ver_nota extends AppCompatActivity {

    protected TextView texto1, texto2;
    protected Button boton1, boton2;

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

        texto1 = (TextView)findViewById(R.id.texto1_verNota);
        texto2 = (TextView)findViewById(R.id.texto2_verNota);
        boton1 = (Button) findViewById(R.id.boton1_verNota);
        boton2 = (Button) findViewById(R.id.boton2_verNota);


    }
}