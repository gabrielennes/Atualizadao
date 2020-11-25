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

public class vendasEditar extends Activity {
    EditText ed1,ed2,ed3;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas_editar);
        ed1 = findViewById(R.id.cateid);
        ed2 = findViewById(R.id.categoriaproduto);
        ed3 = findViewById(R.id.categoriaquant);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String produto = i.getStringExtra("produto").toString();
        String prodquant = i.getStringExtra("prodquant").toString();

        ed1.setText(id);
        ed2.setText(produto);
        ed3.setText(prodquant);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Main.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deletar();
                finish();
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editar();
            }
        });


    }
    public void Editar (){
        try {
            String cateid = ed1.getText().toString();
            String categoriaproduto = ed2.getText().toString();
            String categoriaquant = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
            String sql = "update vendas set produto = ?, prodquant =? where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,categoriaproduto);
            statement.bindString(2,categoriaquant);
            statement.bindString(3,cateid);
            statement.execute();
            Toast.makeText(this, "Venda Atualizada com sucesso!!", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(this, "Venda deu error!!", Toast.LENGTH_SHORT).show();
        }
    }
    public void Deletar (){
        try {
            String cateid = ed1.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("venda", Context.MODE_PRIVATE,null);
            String sql = "delete from vendas where id=?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,cateid);
            statement.execute();
            Toast.makeText(this, "Venda Deletada Com Sucesso!!", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        }catch (Exception ex){
            Toast.makeText(this, "Venda deu error!!", Toast.LENGTH_SHORT).show();
        }
    }
}

