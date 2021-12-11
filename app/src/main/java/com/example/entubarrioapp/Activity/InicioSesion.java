package com.example.entubarrioapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entubarrioapp.Database.AdminSQLiteOpenHelper;
import com.example.entubarrioapp.R;

public class InicioSesion extends AppCompatActivity {

    private EditText etUsuario, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etUsuario = findViewById(R.id.et_Usuario);
        etPassword = findViewById(R.id.et_Password);
    }

    //Método para el Botón Ingresar
    public void ingresar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        String usuario = etUsuario.getText().toString();
        String password = etPassword.getText().toString();

        if(!usuario.isEmpty() && !password.isEmpty()) {
            Cursor filaC = db.rawQuery
                    ("SELECT * FROM Cliente WHERE nombreC ='" + usuario + "' AND passwordC ='" + password + "';", null);
            Cursor filaN = db.rawQuery
                    ("SELECT * FROM Negocio WHERE nombreN ='" + usuario + "' AND passwordN ='" + password + "';", null);

            if(filaC.moveToFirst()) {
                Intent ingreso = new Intent(this, IngresoCliente.class);
                ingreso.putExtra("usuario", etUsuario.getText().toString());
                startActivity(ingreso);
                Toast.makeText(this, "Sesion Iniciada", Toast.LENGTH_SHORT).show();
                etUsuario.setText("");
                etPassword.setText("");
            } else if(filaN.moveToFirst()) {
                Intent ingresoN = new Intent(this, CrudProducto.class);
                ingresoN.putExtra("usuario", etUsuario.getText().toString());
                startActivity(ingresoN);
                etUsuario.setText("");
                etPassword.setText("");
            } else {
                etUsuario.setText("");
                etPassword.setText("");
                Toast.makeText(this, "Error: Usuario y/o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            }
            filaC.close();
            filaN.close();
            db.close();

        } else {
            Toast.makeText(this, "Debes escribir un Usuario y su Contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para el Botón Registrarse
    public void registrarse(View view) {
        Intent registro = new Intent(this, OpcionRegistro.class);
        startActivity(registro);
    }
}