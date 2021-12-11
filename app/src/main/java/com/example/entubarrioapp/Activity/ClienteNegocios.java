package com.example.entubarrioapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.entubarrioapp.Adapters.NegociosClienteAdapter;
import com.example.entubarrioapp.Database.AdminSQLiteOpenHelper;
import com.example.entubarrioapp.Entidades.Negocio;
import com.example.entubarrioapp.R;

import java.util.ArrayList;

public class ClienteNegocios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_negocios);

        TextView tvSaludoC = findViewById(R.id.tv_SaludoC);
        String saludo = getIntent().getStringExtra("usuario");
        tvSaludoC.setText(saludo);

        RecyclerView recyclerViewNegLista = findViewById(R.id.rv_NegC);
        recyclerViewNegLista.setLayoutManager(new LinearLayoutManager(this));

        NegociosClienteAdapter adapter = new NegociosClienteAdapter(mostrarNegocios());
        recyclerViewNegLista.setAdapter(adapter);
    }

    //Método para el Botón Salir
    public void salirC(View view) {
        finish();
    }

    //Método para Mostrar negocios en el RecyclerView
    public ArrayList<Negocio> mostrarNegocios() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ArrayList<Negocio> listaNegocios = new ArrayList<>();

        Cursor cursorNegocios = db.rawQuery("SELECT * FROM Negocio", null);

        if(cursorNegocios.moveToFirst()) {
            do {
                Negocio negocio = new Negocio();
                negocio.setNombreN(cursorNegocios.getString(0));
                negocio.setDireccionN(cursorNegocios.getString(1));
                negocio.setTelefonoN(cursorNegocios.getString(2));
                negocio.setPagWebN(cursorNegocios.getString(3));
                negocio.setPasswordN(cursorNegocios.getString(4));
                listaNegocios.add(negocio);
            } while(cursorNegocios.moveToNext());
        }

        cursorNegocios.close();

        return listaNegocios;
    }
}