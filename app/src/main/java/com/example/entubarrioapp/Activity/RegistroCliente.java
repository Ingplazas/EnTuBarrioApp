package com.example.entubarrioapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.entubarrioapp.Database.AdminSQLiteOpenHelper;
import com.example.entubarrioapp.R;

public class RegistroCliente extends AppCompatActivity {

    private EditText etNombreC, etDireccionC, etTelefonoC, etEmailC, etPasswordC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_cliente);

        etNombreC = findViewById(R.id.et_NombreCliente);
        etDireccionC = findViewById(R.id.et_DireccionCliente);
        etTelefonoC = findViewById(R.id.et_TelefonoCliente);
        etEmailC = findViewById(R.id.et_EmailCliente);
        etPasswordC = findViewById(R.id.et_PasswordCliente);


        ImageView Atras = findViewById(R.id.ib_Atras);

        Atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroCliente.this, OpcionRegistro.class));

            }
        });
    }

    //Método para Registrar Clientes
    public void registrarC(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String nombreC = etNombreC.getText().toString();
        String direccionC = etDireccionC.getText().toString();
        String telefonoC = etTelefonoC.getText().toString();
        String emailC = etEmailC.getText().toString();
        String passwordC = etPasswordC.getText().toString();

        if (!nombreC.isEmpty() && !direccionC.isEmpty() && !telefonoC.isEmpty() && !emailC.isEmpty() && !passwordC.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("nombreC", nombreC);
            registro.put("direccionC", direccionC);
            registro.put("telefonoC", telefonoC);
            registro.put("emailC", emailC);
            registro.put("passwordC", passwordC);

            db.insert("Cliente", null, registro);

            db.close();
            etNombreC.setText("");
            etDireccionC.setText("");
            etTelefonoC.setText("");
            etEmailC.setText("");
            etPasswordC.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

            finish();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}