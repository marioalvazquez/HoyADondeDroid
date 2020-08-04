package com.example.sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnAgregar;
    private EditText textoTarea;
    private EditText id;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAgregar = (Button)findViewById(R.id.button);
        textoTarea = (EditText)findViewById(R.id.editText);
        id = (EditText)findViewById(R.id.editText2);
        layout = (LinearLayout)findViewById(R.id.myLayout);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar(view, textoTarea.getText().toString());
            }
        });

    }

    public void registrar(View view, String message){
        DbHelper admin = new DbHelper(this,"todo", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        int todoId = Integer.parseInt(id.getText().toString());
        String todoDescripcion = textoTarea.getText().toString();

        if (!todoDescripcion.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("id", todoId);
            registro.put("nombre", todoDescripcion);

            BaseDeDatos.insert("articulos", null, registro);

            BaseDeDatos.close();

            id.setText("");
            textoTarea.setText("");

            BaseDeDatos = admin.getReadableDatabase();
            Cursor cursor = BaseDeDatos.rawQuery("select * from articulos",null);
            List<String> todos = new ArrayList<>();


            if (cursor.moveToFirst()){
                do{
                    todos.add(cursor.getString(1));
                }while (cursor.moveToNext());
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            for (String todo:
                 todos) {
                TextView todoTV = new TextView(this);
                todoTV.setText(todo);
                this.layout.addView(todoTV, layoutParams);
            }

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Llena los campos correctamente", Toast.LENGTH_LONG).show();
        }

    }

}
