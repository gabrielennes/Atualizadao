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

public class fabricanteview extends Activity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabricanteview);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery("select * from fabricante",null);
        int id = c.getColumnIndex("id");
        int fabricante = c.getColumnIndex("fabricante");
        int fabdesc = c.getColumnIndex("fabdesc");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);
        final ArrayList<fab> cante = new ArrayList<fab>();

        if(c.moveToNext()){
            do{
                fab br  = new fab();
                br.id = c.getString(id);
                br.fabricante = c.getString(fabricante);
                br.desc = c.getString(fabdesc);
                cante.add(br);

                titles.add(c.getString(id)+"\t" +c.getString(fabricante)+"\t" +c.getString(fabdesc));


            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String aaa = titles.get(position).toString();
                fab br = cante.get((position));
                Intent i = new Intent(getApplicationContext(), fabricanteEditar.class);
                i.putExtra("id",br.id);
                i.putExtra("fabricante",br.fabricante);
                i.putExtra("fabdesc",br.desc);

                startActivity(i);

            }
        });
    }
}