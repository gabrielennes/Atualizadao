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

public class produtoView extends Activity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_view);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
        final Cursor c = db.rawQuery("select * from produto",null);
        int id = c.getColumnIndex("id");
        int produto = c.getColumnIndex("produto");
        int prodesc = c.getColumnIndex("prodesc");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,titles);

        lst1.setAdapter(arrayAdapter);
        final ArrayList<pro> prod = new ArrayList<pro>();

        if(c.moveToNext()){
            do{
                pro pd = new pro();
                pd.id = c.getString(id);
                pd.produto = c.getString(produto);
                pd.prodesc = c.getString(prodesc);
                prod.add(pd);

                titles.add(c.getString(id)+"\t" +c.getString(produto)+"\t" +c.getString(prodesc));


            }while(c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                pro pd = prod.get((position));
                Intent i = new Intent(getApplicationContext(), produtoEditar.class);
                i.putExtra("id",pd.id);
                i.putExtra("produto",pd.produto);
                i.putExtra("prodesc",pd.prodesc);

                startActivity(i);

            }
        });
    }
}