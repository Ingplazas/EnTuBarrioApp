package com.example.entubarrioapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.entubarrioapp.Adapters.ProductosClienteAdapter;
import com.example.entubarrioapp.Database.AdminSQLiteOpenHelper;
import com.example.entubarrioapp.Entidades.Producto;
import com.example.entubarrioapp.R;

import java.util.ArrayList;

public class ClienteProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_productos);

        TextView tvSaludoC = findViewById(R.id.tv_SaludoC);
        String saludo = getIntent().getStringExtra("usuario");
        tvSaludoC.setText(saludo);

        RecyclerView recyclerViewProdLista = findViewById(R.id.rv_NegC);
        recyclerViewProdLista.setLayoutManager(new LinearLayoutManager(this));

        ProductosClienteAdapter adapter = new ProductosClienteAdapter(mostrarProductos());
        recyclerViewProdLista.setAdapter(adapter);
    }

    //Método para el Botón Salir
    public void salirC(View view) {
        finish();
    }

    //Método para Mostrar productos en el RecyclerView
    public ArrayList<Producto> mostrarProductos() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        ArrayList<Producto> listaProductos = new ArrayList<>();

        Cursor cursorProductos = db.rawQuery("SELECT * FROM Producto", null);

        if(cursorProductos.moveToFirst()) {
            do {
                Producto producto = new Producto();
                producto.setCodigoP(cursorProductos.getString(0));
                producto.setCategoriaP(cursorProductos.getString(1));
                producto.setNombreP(cursorProductos.getString(2));
                producto.setDescripcionP(cursorProductos.getString(3));
                producto.setPrecioP(cursorProductos.getFloat(4));
                producto.setCantidadP(cursorProductos.getInt(5));
                listaProductos.add(producto);
            } while(cursorProductos.moveToNext());
        }

        cursorProductos.close();

        return listaProductos;
    }
}