package com.example.tfg_inmaperez;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg_inmaperez.Domain.Peliseri;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyRecyclerView1 extends RecyclerView.Adapter<MyRecyclerView1.ViewHolder> {



    //al mData le pasamos el tipo correspondiente, sea string, Objeto, etc
    private List<Peliseri> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;





    //esto se queda igual MENOS el parametro de entrada
    //dentro del constructor se pasan los datos de cada parametro
    //en el parametro se añade tambien el tipo de data

    public MyRecyclerView1(Context contexto, List<Peliseri>data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(contexto);

    }


    //esto es igual
    //carga cada linea del layout de la lista cuando se necesite para no perderla

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View parten=mInflater.inflate(R.layout.recycler_view_item_1, parent, false);
        return new ViewHolder(parten);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerView1.ViewHolder holder, int position) {
        Peliseri peli=mData.get(position);

        if(peli.getBmp()!= null){
            holder.portada.setImageBitmap(peli.getBmp());
        }else{
            holder.portada.setImageResource(R.drawable.error);
        }
        holder.titulo.setText(peli.getTitulo());
        holder.genero.setText(peli.getGenero());


    }



    @Override
    public int getItemCount() {
        return mData.size();
    }



    //aqui se cambia
    //va reciclando y almacenando las views para que funcione el scroll de la pantalla
    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView portada;
        TextView titulo;
        TextView genero;

        ViewHolder (View itemView){
            super(itemView);
            //aqui tambien se cambian cosillas

            portada=itemView.findViewById(R.id.imageView2);
            titulo=itemView.findViewById(R.id.textViewTitulo);
            genero=itemView.findViewById(R.id.textViewgenero);
            itemView.setOnClickListener(this);

        }
        //NO cambia
        @Override
        public void onClick(View view) {
            if(mClickListener != null)
                mClickListener.onItemClick(view,getAdapterPosition());

        }
    }
    //obtiene la posicion donde se está situado en la pantalla
    Peliseri getItem(int id){
        return mData.get(id);
    }

    //todos los clicks por los que va pasando
    void setClickListener(MyRecyclerView1.ItemClickListener itemClickListener){
        this.mClickListener=itemClickListener;
    }
    //datos que se pasan a activity que implementará el metodo para responder al click
    public interface ItemClickListener {
        void onItemClick(View activista, int position);
    }

    public void setLista(List<Peliseri> listaPeliculas){
        this.mData=listaPeliculas;
        notifyDataSetChanged();
    }
}



