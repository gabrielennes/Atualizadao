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

public class vendasView extends Activity {
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas_view);
        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery("select * from vendas",null);
        int id = c.getColumnIndex("id");
        int produto = c.getColumnIndex("produto");
        int prodquant = c.getColumnIndex("prodquant");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);
        final ArrayList<ven> venn = new ArrayList<ven>();
        if(c.moveToNext()){
            do {
                ven ve = new ven();
                ve.id = c.getString(id);
                ve.produto = c.getString(produto);
                ve.prodquant = c.getString(prodquant);
                venn.add(ve);

                titles.add(c.getString(id)+"\t"  +c.getString(produto)+"\t"  +c.getString(prodquant)+"\t");


            }while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                ven ve = venn.get((position));
                Intent i = new Intent(getApplicationContext(), vendasEditar.class);
                i.putExtra("id", ve.id);
                i.putExtra("produto", ve.produto);
                i.putExtra("prodquant", ve.prodquant);

                startActivity(i);
            }
        });
    }
}