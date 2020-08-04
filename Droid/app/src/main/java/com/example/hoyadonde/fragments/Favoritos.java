package com.example.hoyadonde.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoyadonde.CardAdapter;
import com.example.hoyadonde.Promocion;
import com.example.hoyadonde.PromocionActivity;
import com.example.hoyadonde.R;
import com.example.hoyadonde.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Favoritos extends Fragment {

    private List<Promocion> promociones;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private int counter = 0;
    public Usuario usuario;

    public Favoritos() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        promociones = new ArrayList<Promocion>();

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPromociones();
            }
        });

        usuario = (Usuario) getArguments().getSerializable("USUARIO");

        getPromociones();
        return view;
    }

    private void getPromociones(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url ="http://192.168.43.211:8000/api/v1/promociones_favoritas/"+usuario.getUsuario_id();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Type usePromocionesList = new TypeToken<List<Promocion>>(){}.getType();
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONArray promocionesArray = jsonResponse.getJSONArray("data");
                            Gson gson = new Gson();
                            promociones = (List<Promocion>) gson.fromJson(promocionesArray.toString(), usePromocionesList);
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                        mAdapter = new CardAdapter(promociones, R.layout.recycler_view_item, new CardAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClicked(Promocion promocion, int position) {
                                Intent intent = new Intent(getActivity(), PromocionActivity.class);
                                intent.putExtra("PROMOCION", promocion);
                                intent.putExtra("USUARIO_ID", usuario.getUsuario_id());
                                intent.putExtra("IS_FAVORITE", true);
                                startActivity(intent);
                            }
                        });

                        mRecyclerView.setAdapter(mAdapter);
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
