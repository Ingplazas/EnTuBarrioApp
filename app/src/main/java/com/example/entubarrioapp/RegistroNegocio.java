package com.example.entubarrioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegistroNegocio extends AppCompatActivity {

    private EditText etNombreN, etDireccionN, etTelefonoN, etPaginaWebN, etPasswordN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_negocio);

        etNombreN = findViewById(R.id.et_NombreNegocio);
        etDireccionN = findViewById(R.id.et_DireccionNegocio);
        etTelefonoN = findViewById(R.id.et_TelefonoNegocio);
        etPaginaWebN = findViewById(R.id.et_PgWebNegocio);
        etPasswordN = findViewById(R.id.et_PasswordNegocio);


        ImageView Atras = findViewById(R.id.ib_Atras);

        Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroNegocio.this, OpcionRegistro.class));

            }
        });
    }

    //Método para Registrar Negocios
    public void registrarN(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String nombreN = etNombreN.getText().toString();
        String direccionN = etDireccionN.getText().toString();
        String telefonoN = etTelefonoN.getText().toString();
        String paginaWebN = etPaginaWebN.getText().toString();
        String passwordN = etPasswordN.getText().toString();

        if (!nombreN.isEmpty() && !direccionN.isEmpty() && !telefonoN.isEmpty() && !paginaWebN.isEmpty() && !passwordN.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("nombreN", nombreN);
            registro.put("direccionN", direccionN);
            registro.put("telefonoN", telefonoN);
            registro.put("pagWebN", paginaWebN);
            registro.put("passwordN", passwordN);

            db.insert("Negocio", null, registro);

            db.close();
            etNombreN.setText("");
            etDireccionN.setText("");
            etTelefonoN.setText("");
            etPaginaWebN.setText("");
            etPasswordN.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            Intent cambio = new Intent(this, InicioSesion.class);
            startActivity(cambio);
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}