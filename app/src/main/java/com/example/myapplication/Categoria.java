package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Categoria extends Activity {
    EditText ed1,ed2;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        ed1 = findViewById(R.id.categoria);
        ed2 = findViewById(R.id.categoriadesc);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Categoria.this,Main.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
                finish();
            }
        });
    }

    public void insert(){
        try {
            String categoria = ed1.getText().toString();
            String descricao = ed2.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS categoria(id INTEGER PRIMARY KEY AUTOINCREMENT,categoria VARCHAR,catdesc VARCHAR)");

            String sql = "insert into categoria(categoria, catdesc)values(?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,categoria);
            statement.bindString(2,descricao);
            statement.execute();
            Toast.makeText(this, "Categoria adicionada com sucesso!!", Toast.LENGTH_SHORT).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        }catch (Exception ex){
            Toast.makeText(this, "Categoria deu error!!", Toast.LENGTH_SHORT).show();
        }
    }
}