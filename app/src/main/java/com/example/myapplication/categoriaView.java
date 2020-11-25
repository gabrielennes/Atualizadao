package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class categoriaView extends Activity {
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_view);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery("select * from categoria",null);
        int id = c.getColumnIndex("id");
        int categoria = c.getColumnIndex("categoria");
        int catdesc = c.getColumnIndex("catdesc");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);
        final ArrayList<cate> catee = new ArrayList<cate>();

        if(c.moveToNext()){
            do{
                cate ca = new cate();
                ca.id = c.getString(id);
                ca.categoria = c.getString(categoria);
                ca.desc = c.getString(catdesc);
                catee.add(ca);

                titles.add(c.getString(id)+"\t" +c.getString(categoria)+"\t" +c.getString(catdesc));


            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                cate ca = catee.get((position));
                Intent i = new Intent(getApplicationContext(), CategoriaEditar.class);
                i.putExtra("id",ca.id);
                i.putExtra("categoria",ca.categoria);
                i.putExtra("catdesc",ca.desc);

                startActivity(i);

            }
        });
    }
}