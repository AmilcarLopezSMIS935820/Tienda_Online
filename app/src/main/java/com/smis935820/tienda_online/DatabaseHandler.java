package com.smis935820.tienda_online;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    //Constructor
    public DatabaseHandler(@Nullable Context context) {
        super(context,  "Tienda_Online.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Productos(codProducto TEXT PRIMARY KEY, nombre TEXT, precio TEXT)"); /// Creación de tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Productos");
    }

    //Metodos del CRUD

    public boolean insertData(String codProducto,  String nombre, String precio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codProducto", codProducto);
        contentValues.put("nombre", nombre);
        contentValues.put("precio", precio);

        long result = db.insert("Productos", null, contentValues);
        if (result==-1){
            return false;
        } else{
            return true;
        }
    }

    //obtener datos

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Productos", null);
        return cursor;
    }

    //actualizar datos

    public boolean updateData(String codProducto,  String nombre, String precio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codProducto", codProducto);
        contentValues.put("nombre", nombre);
        contentValues.put("precio", precio);

        //Busqueda del registro
        Cursor cursor = db.rawQuery("SELECT * FROM Productos WHERE codProducto=?", new String[]{codProducto});

        //Evaluación de registro
        if (cursor.getCount()>0){
            long result = db.update("Productos", contentValues, "codProducto=?", new String[]{codProducto});
            if (result==-1){
                return false;
            } else{
                return true;
            }
        } else {
            return false;
        }

    }


    //Metodo delete (Eliminar Registro)
    public boolean deleteData(String codProducto){
        SQLiteDatabase db = this. getWritableDatabase();

        Cursor cursor = db.rawQuery( "SELECT * FROM Productos WHERE codProducto=?", new String[]{codProducto});
        if (cursor.getCount()>0){
            long result = db.delete("Productos",  "codProducto=?", new String[]{codProducto});
            if (result==-1){
                return false;
            } else{
                return true;
            }
        } else {
            return false;
        }
    }
}
