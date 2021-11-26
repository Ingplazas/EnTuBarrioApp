package com.example.entubarrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    //Método para el Botón Cliente
    public void opCliente(View view) {
        Intent cliente = new Intent(this, MainActivity4.class);
        startActivity(cliente);
    }

    //Método para el Botón Negocio
    public void opNegocio(View view) {
        Intent negocio = new Intent(this, MainActivity3.class);
        startActivity(negocio);
    }
}