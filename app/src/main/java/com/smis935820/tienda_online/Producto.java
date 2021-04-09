package com.smis935820.tienda_online;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Producto extends AppCompatActivity {

    DatabaseHandler DB;
    public Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        showData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        AdapterView.AdapterContextMenuInfo info=
                (AdapterView.AdapterContextMenuInfo)menuInfo;
        c.moveToPosition(info.position);
        menu.setHeaderTitle("Opciones");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                try {
                    String productos[]={c.getString(1),c.getString(2)};
                    Bundle bundle=new Bundle();
                    bundle.putString("action","delete");
                    bundle.putString("id",c.getString(0));
                    bundle.putStringArray("productos",productos);

                    Intent forMain=new Intent(Producto.this,Formulario.class);
                    forMain.putExtras(bundle);
                    startActivity(forMain);



                }catch (Exception e){
                    Toast.makeText(Producto.this,"Error:" + e.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return  super.onContextItemSelected(item);
    }

}

    private void showData() {
        DB = new DatabaseHandler(Producto.this);
        c = DB.getData();

        //Evaluar si existe registros

        if (c.moveToFirst()) {
            ListView listData = (ListView) findViewById(R.id.listData);

            //Arraylist
            final ArrayList<String> allData = new ArrayList<String>();
            final ArrayAdapter<String> aData = new ArrayAdapter<String>(Producto.this, android.R.layout.simple_expandable_list_item_1, allData);

            //Adaptador a ListView
            listData.setAdapter(aData);

            //Mostrar registros
            do {
                allData.add(c.getString(1));
            } while (c.moveToNext());
            registerForContextMenu(listData);
        } else {
            Toast.makeText(this, "No hay registros para mostrar", Toast.LENGTH_SHORT).show();
            return;
        }
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