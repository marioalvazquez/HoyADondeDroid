package com.example.hoyadonde;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Promocion> promociones;
    private int layout;
    private OnItemClickListener itemClickListener;

    public CardAdapter(List<Promocion> promociones, int layout, OnItemClickListener listener){
        this.promociones = promociones;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(promociones.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        try{
            return promociones.size();
        }catch (Exception e){
            return 0;
        }
    }

    public void updateList(List<Promocion> promociones){
        promociones = promociones;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewPlace;
        public TextView textViewTime;
        public TextView textViewDate;
        public ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
            textViewPlace = (TextView) itemView.findViewById(R.id.textViewPlace);

        }

        public void bind(final Promocion promocion, final OnItemClickListener listener){
            this.textViewName.setText(promocion.getNombre());
            this.textViewPlace.setText(promocion.getEmpresa()+", "+promocion.getUbicacion());
            this.textViewDate.setText(promocion.getFechas());
            this.textViewTime.setText(promocion.getHorario());
            //imageView.setImageResource(R.drawable.hamburguesa);
            Picasso.get().load(promocion.getUrl()).into(imageView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    listener.onItemClicked(promocion, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Promocion promocion, int position);
    }
}
