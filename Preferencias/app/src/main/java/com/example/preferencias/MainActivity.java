package com.example.preferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private EditText nombreEditText;
    private Switch configuracion1Switch;
    private Switch configuracion2Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreEditText = (EditText) findViewById(R.id.nombreEditText);
        configuracion1Switch = (Switch) findViewById(R.id.configuracion1Switch);
        configuracion2Switch = (Switch) findViewById(R.id.configuracion2Switch);

        // leer
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String nombre = preferences.getString("nombre", "Sin nombre");
        boolean conf1 = preferences.getBoolean("conf1", false);
        boolean conf2 = preferences.getBoolean("conf2", false);

        nombreEditText.setText(nombre);
        configuracion1Switch.setChecked(conf1);
        configuracion2Switch.setChecked(conf2);
    }

    public void guardar(View view) {

        String nombre = nombreEditText.getText().toString();

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", nombre);
        editor.apply();
    }

    public void cambiarConfiguracion1(View view) {
        boolean configuracion1 = configuracion1Switch.isChecked();

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("conf1", configuracion1);
        editor.apply();
    }

    public void cambiarConfiguracion2(View view) {
        boolean configuracion2 = configuracion2Switch.isChecked();

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("conf2", configuracion2);
        editor.apply();
    }
}
