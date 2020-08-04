package com.example.hoyadonde;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class PromocionActivity extends AppCompatActivity {

    ImageView ivPromocion;
    TextView tvTitle;
    TextView tvDescription;
    TextView tvPlace;
    TextView tvTime;
    public int usuario_id;
    Promocion promocion;
    public boolean isFavorite;
    Button btnAddFav;
    Button btnRemoveFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocion);

        ivPromocion = (ImageView) findViewById(R.id.imageViewPromocion);
        tvTitle = (TextView) findViewById(R.id.textViewPromocionTitle);
        tvDescription = (TextView) findViewById(R.id.textViewDescripcion);
        tvPlace = (TextView) findViewById(R.id.textViewUbicacion);
        tvTime = (TextView) findViewById(R.id.textViewHora);
        btnAddFav = (Button) findViewById(R.id.btnAddFavorite);
        btnRemoveFav = (Button) findViewById(R.id.btnRemoveFavorite);

        promocion = (Promocion) getIntent().getSerializableExtra("PROMOCION");
        usuario_id = (int) getIntent().getIntExtra("USUARIO_ID", 1);
        isFavorite = (boolean) getIntent().getBooleanExtra("IS_FAVORITE", false);

        if (isFavorite){
            btnRemoveFav.setVisibility(View.VISIBLE);
            btnAddFav.setVisibility(View.INVISIBLE);
        }
        else{
            btnAddFav.setVisibility(View.VISIBLE);
            btnRemoveFav.setVisibility(View.INVISIBLE);
        }

        Picasso.get().load(promocion.getUrl()).into(ivPromocion);
        tvTitle.setText(promocion.getNombre());
        tvDescription.setText(promocion.getDescripcion());
        tvPlace.setText(promocion.getEmpresa()+", "+promocion.getUbicacion());
        tvTime.setText(promocion.getFechas()+", "+promocion.getHorario()+ " hrs");

        btnAddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavorite();
                Toast.makeText(PromocionActivity.this,"Se agregó la promoción a favoritos", Toast.LENGTH_SHORT).show();
            }
        });

        btnRemoveFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFavorite();
                Toast.makeText(PromocionActivity.this,"Quitó la promoción de favoritos", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void addFavorite(){
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://192.168.43.211:8000/api/v1/toggleFavorita?usuario_id="+usuario_id+"&promocion_id="+promocion.getPromocion_id();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Log.e("Success", response.toString());
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
}
