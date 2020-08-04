package com.example.hoyadonde.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoyadonde.R;
import com.example.hoyadonde.Usuario;


public class Cuenta extends Fragment {
    private static final String USUARIO_KEY = "USUARIO";
    private Usuario usuario;
    EditText editName;
    EditText editAge;
    EditText editLocation;
    TextView welcome;
    Button btnActualizar;
    public Cuenta() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuenta, container, false);
        usuario = (Usuario) getArguments().getSerializable(USUARIO_KEY);
        editName = (EditText) view.findViewById(R.id.editTextName);
        editAge = (EditText) view.findViewById(R.id.editTextAge);
        editLocation = (EditText) view.findViewById(R.id.editTextLocation);
        welcome = (TextView) view.findViewById(R.id.textViewWelcome);
        btnActualizar = (Button) view.findViewById(R.id.btnActualizarUsuario);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url ="http://192.168.43.211:8000/api/v1/updateUser?usuario_id="+usuario.getUsuario_id()+"&nombre="+editName.getText().toString()+"&edad="+editAge.getText().toString()+"&ubicacion="+editLocation.getText().toString();

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("Success", response.toString());
                                Toast.makeText(getActivity().getApplicationContext(), "Se actualizaron los datos correctamente", Toast.LENGTH_SHORT).show();
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

        welcome.setText("Bienvenido, " + usuario.getUsername());
        editName.setText(usuario.getNombre());
        editAge.setText(Integer.toString(usuario.getEdad()));
        editLocation.setText(usuario.getUbicacion());
        return  view;
    }

}
