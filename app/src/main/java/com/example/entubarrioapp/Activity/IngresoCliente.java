package com.example.entubarrioapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.entubarrioapp.R;

public class IngresoCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_cliente);

        TextView tvSaludoC = findViewById(R.id.tv_SaludoC);
        String saludo = getIntent().getStringExtra("usuario");
        tvSaludoC.setText(saludo);
    }

    //Método para el Botón Salir
    public void salirC(View view) {
        finish();
    }

    //Método para ir a los Productos
    public void irProductos(View view) {
        Intent intent = new Intent(this, ClienteProductos.class);
        String saludo = getIntent().getStringExtra("usuario");
        intent.putExtra("usuario", saludo);
        startActivity(intent);
    }

    //Método para ir a los Negocios
    public void irNegocios(View view) {
        Intent intent = new Intent(this, ClienteNegocios.class);
        String saludo = getIntent().getStringExtra("usuario");
        intent.putExtra("usuario", saludo);
        startActivity(intent);
    }
}