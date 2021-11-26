package com.example.entubarrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OpcionRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_registro);
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