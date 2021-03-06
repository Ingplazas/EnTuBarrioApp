package com.example.entubarrioapp.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.entubarrioapp.Database.AdminSQLiteOpenHelper;
import com.example.entubarrioapp.R;
import com.example.entubarrioapp.Entidades.Producto;

import java.util.ArrayList;

public class CrudProducto extends AppCompatActivity {

    EditText etnombre, etcodigo, etprecio;
    ListView lv_producto;
    TextView tvproducto;
    Button btnbuscar,btnguardar, btneliminar, btneditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        etnombre = (EditText) findViewById(R.id.et_nombre);
        etcodigo = (EditText) findViewById(R.id.et_codigo);
        etprecio = (EditText) findViewById(R.id.et_precio);
        btnbuscar=(Button) findViewById(R.id.btn_buscar);
        btnguardar=(Button) findViewById(R.id.btn_registrar);
        btneditar=(Button) findViewById(R.id.btn_editar);
        btneliminar=(Button) findViewById(R.id.btn_eliminar);
        lv_producto = (ListView) findViewById(R.id.lv_pro);
        tvproducto=(TextView) findViewById(R.id.tv_productos);
        BuscarP();
        AgregarP();
        EliminarP();
        ModificarP();
        listar();
    }

    public Producto buscarProducto(int codigoP){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from Producto where codigoP =" + codigoP, null);
        Producto p=null;
        if(fila.moveToNext()){
            p=new Producto();
            p.setNombreP(fila.getString(2));
            p.setPrecioP(fila.getFloat(4));
        }
        fila.close();
        db.close();
        return p;
    }
    public void BuscarP() {
        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etcodigo.getText().toString().isEmpty()) {
                    Toast.makeText(CrudProducto.this, "Debe digitar codigo del producto a buscar", Toast.LENGTH_LONG).show();

                } else {
                    int codigo=Integer.parseInt(etcodigo.getText().toString());
                    Producto p=buscarProducto(codigo);
                    if(p==null){
                        Toast.makeText(CrudProducto.this, "El producto no existe", Toast.LENGTH_LONG).show();
                        etcodigo.requestFocus();
                    }
                    else {
                        etnombre.setText(p.getNombreP());
                        etprecio.setText(Double.toString(p.getPrecioP()));
                    }
                }
            }
        });
    }

    public long agregarProducto(Producto p) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        //Pasar los datos al contenedor
        registro.put("codigoP", p.getCodigoP());
        registro.put("nombreP", p.getNombreP());
        registro.put("precioP", p.getPrecioP());
        long res=db.insert("Producto", null,registro);
        db.close();
        return res;
    }
    public void AgregarP(){
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etnombre.getText().toString().isEmpty()||etcodigo.getText().toString().isEmpty()||etprecio.getText().toString().isEmpty()){
                    Toast.makeText(CrudProducto.this, "Debes digitar info en todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    Producto p= new Producto();
                    p.setCodigoP(/*Integer.parseInt(*/etcodigo.getText().toString());
                    p.setNombreP(etnombre.getText().toString());
                    p.setPrecioP(Float.parseFloat(etprecio.getText().toString()));
                    long res= agregarProducto(p);
                    if(res!=-1){
                        StringBuffer buffer=new StringBuffer();
                        buffer.append("codigo: "+ p.getCodigoP()+"\n");
                        buffer.append("nombre: "+ p.getNombreP()+"\n");
                        buffer.append("precio: "+ p.getPrecioP()+"\n");
                        Mensaje("El producto se ha registrado correctamente",buffer.toString());
                        limpiar();
                        listar();
                    }else{
                        Toast.makeText(CrudProducto.this, "El producto ya existe", Toast.LENGTH_LONG).show();
                        etcodigo.setText("");
                        etcodigo.requestFocus();
                    }
                }
            }
        });
    }

    public void Mensaje(String titulo, String mensaje){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setIcon(R.drawable.agregado);
        builder.show();
    }


    public int eliminarProducto(int codigoP){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        int res =db.delete("Producto", "codigoP=?",new String[]{Integer.toString(codigoP)});
        db.close();
        return res;
    }
    public void EliminarP(){
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etcodigo.getText().toString().isEmpty()){
                    Toast.makeText(CrudProducto.this, "Debe digitar el codigo del producto a eliminar", Toast.LENGTH_LONG).show();
                }else{
                    int codigo=Integer.parseInt(etcodigo.getText().toString());
                    int res=eliminarProducto(codigo);
                    if(res!=-1){
                        Toast.makeText(CrudProducto.this, "El producto se elimin?? correctamente", Toast.LENGTH_LONG).show();
                        limpiar();
                        listar();
                    }else{
                        Toast.makeText(CrudProducto.this, "El producto no existe", Toast.LENGTH_LONG).show();
                        etcodigo.setText("");
                        etcodigo.requestFocus();
                    }
                }
            }
        });
    }

    public void limpiar(){
        etcodigo.setText("");
        etnombre.setText("");
        etprecio.setText("");
    }

    public int modificarProducto(Producto p){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        //Pasar los datos al contenedor
        registro.put("codigoP", p.getCodigoP());
        registro.put("nombreP", p.getNombreP());
        registro.put("precioP", p.getPrecioP());
        int res=db.update("Producto", registro, "codigoP=?",new String[]{/*Integer.toString(*/p.getCodigoP()});
        db.close();
        return res;
    }
    public void ModificarP(){
        btneditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etcodigo.getText().toString().isEmpty()){
                    Toast.makeText(CrudProducto.this, "Debe digitar el codigo del producto a modificar", Toast.LENGTH_LONG).show();
                }else {
                    if(etnombre.getText().toString().isEmpty()||etprecio.getText().toString().isEmpty()){
                        Toast.makeText(CrudProducto.this, "Debes digitar info en todos los campos", Toast.LENGTH_LONG).show();
                    }else {
                        Producto p=new Producto();
                        p.setCodigoP(/*Integer.parseInt(*/etcodigo.getText().toString());
                        p.setNombreP(etnombre.getText().toString());
                        p.setPrecioP(Float.parseFloat(etprecio.getText().toString()));
                        int res=modificarProducto(p);
                        if(res==1) {
                            StringBuffer buffer = new StringBuffer();
                            buffer.append("codigo: " + p.getCodigoP() + "\n");
                            buffer.append("nombre: " + p.getNombreP() + "\n");
                            buffer.append("precio: " + p.getPrecioP() + "\n");
                            Mensaje("El producto se ha modificado correctamente", buffer.toString());
                            limpiar();
                            listar();
                        }else {
                            Toast.makeText(CrudProducto.this, "El producto no existe", Toast.LENGTH_LONG).show();
                            etcodigo.setText("");
                            etcodigo.requestFocus();
                        }
                    }
                }
            }
        });

    }

    public ArrayList<Producto>obtenerProducto(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "enTuBarrio", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor datos=db.rawQuery("select * from Producto ", null);
        ArrayList<Producto>lista=new ArrayList<Producto>();
        while(datos.moveToNext()) {
            Producto p = new Producto();
            p.setCodigoP(datos.getString(0));
            p.setNombreP(datos.getString(2));
            p.setPrecioP(datos.getFloat(4));
            lista.add(p);
        }
        db.close();
        return lista;
    }

    public void listar(){
        final ArrayList<Producto>lista=obtenerProducto();
        if(!lista.isEmpty()){
            tvproducto.setVisibility(View.VISIBLE);
            lv_producto.setVisibility(View.VISIBLE);
            ArrayAdapter<Producto> adaptador=new ArrayAdapter<Producto>(this, android.R.layout.simple_list_item_1,lista);
            lv_producto.setAdapter(adaptador);

            lv_producto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Producto p=lista.get(position);
                    etcodigo.setText(/*Integer.toString(*/p.getCodigoP());
                    etnombre.setText(p.getNombreP());
                    etprecio.setText(Double.toString(p.getPrecioP()));
                }
            });
        }else{
            tvproducto.setVisibility(View.INVISIBLE);
            lv_producto.setVisibility(View.INVISIBLE);
        }
    }

    public void Regresar(View V)
    {
        Intent regresar= new Intent(this,InicioSesion.class);
        startActivity(regresar);
    }


}
