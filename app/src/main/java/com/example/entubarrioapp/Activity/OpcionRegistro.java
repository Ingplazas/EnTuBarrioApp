package com.example.entubarrioapp.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.entubarrioapp.R;

public class OpcionRegistro extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_registro);

        ImageView Atras = findViewById(R.id.ib_Atras);

        Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OpcionRegistro.this, InicioSesion.class));

            }
        });

    }

    //Método para el Botón Cliente
    public void opCliente(View view) {
        Intent cliente = new Intent(this, RegistroCliente.class);
        startActivity(cliente);
    }

    //Método para el Botón Negocio
    public void opNegocio(View view) {
        Intent negocio = new Intent(this, RegistroNegocio.class);
        startActivity(negocio);
    }
}