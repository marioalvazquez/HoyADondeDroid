package com.example.hoyadonde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        this.getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editTextUserName);
        password = (EditText) findViewById(R.id.editTextPassword);

        final Button loginBtn = findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = username.getText().toString();
                String uPass = password.getText().toString();

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.43.211:8000/api/v1/hoy_login?username="+uName+"&password="+uPass;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Type useUsuario = new TypeToken<Usuario>(){}.getType();
                                    JSONObject jsonResponse = new JSONObject(response);
                                    JSONArray usuarioArray = jsonResponse.getJSONArray("data");
                                    Gson gson = new Gson();
                                    usuario = gson.fromJson(usuarioArray.get(0).toString(), useUsuario);
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    intent.putExtra("USUARIO", usuario);
                                    startActivity(intent);
                                } catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);



            }
        });
    }
}
