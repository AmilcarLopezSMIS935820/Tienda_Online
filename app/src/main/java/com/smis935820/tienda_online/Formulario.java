package com.smis935820.tienda_online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Formulario extends AppCompatActivity {
    String id;
    EditText idProducto, nombre, precio;
    Button insert, update, delete;
    DatabaseHandler DB;
    String action = "new";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        idProducto = (EditText) findViewById(R.id.idproducto);
        nombre = (EditText) findViewById(R.id.nombre);
        precio = (EditText) findViewById(R.id.precio);
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnActualizar);
        delete = findViewById(R.id.btnEliminar);
        DB = new DatabaseHandler(this);
        showData();


        ///Evento de boton

        //Boton insertar
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = idProducto.getText().toString().trim();
                String nombreTXT = nombre.getText().toString().trim();
                String precioTXT = precio.getText().toString().trim();

                if (validar()) {

                    Boolean checkInsert = DB.insertData(idTXT, nombreTXT, precioTXT);

                    //Evaluación de la data insertada
                    if (checkInsert == true) {
                        Toast.makeText(Formulario.this, "Se ha insertado un nuevo registro", Toast.LENGTH_SHORT).show();
                        idProducto.setText("");
                        nombre.setText("");
                        precio.setText("");
                        idProducto.requestFocus();
                    } else {
                        Toast.makeText(Formulario.this, "No se ha podido insertado el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //Boton eliminar
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codproducro = idProducto.getText().toString();

                Boolean checkdelateData = DB.deleteData(codproducro);
                if (checkdelateData == true) {
                    Toast.makeText(Formulario.this, "Se elimino exisitosamente",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Formulario.this, "Error no se pudo eliminar ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void showData() {
        try {
            Bundle bundle = getIntent().getExtras();
            action = bundle.getString("action");
            if (action.equals("delete")) {
                delete.setVisibility(View.VISIBLE);
                insert.setVisibility(View.GONE);
                update.setVisibility(View.GONE);


                id = bundle.getString("id");
                String productos[] = bundle.getStringArray("productos");
                TextView tem = (TextView) findViewById(R.id.idproducto);
                tem.setText(id);

                tem = (TextView) findViewById(R.id.nombre);
                tem.setText(productos[0].toString());

                tem = (TextView) findViewById(R.id.precio);
                tem.setText(productos[1].toString());
            }
        } catch (Exception e) {
            Toast.makeText(Formulario.this, "Error:" +
                    e.getMessage().toString(), Toast.LENGTH_LONG).show();

        }
    }



    ////Metodo de validacion de campo
    public boolean validar(){
        boolean retorno= true;
        String c1 = idProducto.getText().toString();
        String c2 = nombre.getText().toString();
        String c3 = precio.getText().toString();

        if(c1.isEmpty()){
            retorno = false;
            idProducto.setError("Campo vacio");
        }

        if(c2.isEmpty()){
            retorno = false;
            nombre.setError("Campo vacio");
        }

        if(c3.isEmpty()){
            retorno = false;
            precio.setError("Campo vacio");
        }
        return retorno;
    }

}