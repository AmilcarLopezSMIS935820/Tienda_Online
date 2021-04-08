package com.smis935820.tienda_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Producto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
    }

    ///Pasar al registro
    public  void addData(View v){
        Intent i = new Intent(Producto.this, Formulario.class);
        startActivity(i);
    }

    public  void home(View v){
        Intent i = new Intent(Producto.this, MainActivity.class);
        startActivity(i);
    }
}