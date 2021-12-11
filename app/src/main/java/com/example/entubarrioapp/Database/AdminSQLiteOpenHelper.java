package com.example.entubarrioapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Cliente(nombreC TEXT, direccionC TEXT, telefonoC TEXT, emailC TEXT, passwordC TEXT);");
        db.execSQL("CREATE TABLE Negocio(nombreN TEXT, direccionN TEXT, telefonoN TEXT, pagWebN TEXT, passwordN TEXT);");
        db.execSQL("CREATE TABLE Producto(codigoP TEXT PRIMARY KEY, categoriaP TEXT, nombreP TEXT, descripcionP TEXT, precioP REAL, cantidadP INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
