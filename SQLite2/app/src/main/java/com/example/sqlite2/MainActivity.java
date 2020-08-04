package com.example.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView nombreDeTarea;
    private Button agregarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreDeTarea = (TextView)findViewById(R.id.editText);

        agregarBtn = (Button)findViewById(R.id.button);
        agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar(view);
            }
        });
    }

    public void registrar(View view){
        Toast.makeText(this, "Aver si entramos, perro", Toast.LENGTH_LONG).show();
        DbHelper dbHelper = new DbHelper(this,"todolist", null, 1);
        SQLiteDatabase BaseDeDatos = dbHelper.getWritableDatabase();

        String descripcionTarea = nombreDeTarea.getText().toString();

        if (!descripcionTarea.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("descripcion", descripcionTarea);
            registro.put("todo_id", 0);
            registro.put("terminada", 0);


            BaseDeDatos.insert("todo", null, registro);

            BaseDeDatos.close();
            nombreDeTarea.setText("");

        }else{
            Toast.makeText(this,"Llena la descripción de la tarea", Toast.LENGTH_LONG).show();
        }

    }
}
