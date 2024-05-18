package com.example.tfg_inmaperez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfg_inmaperez.Domain.Peliseri;

import java.util.List;

public class MyRecyclerVIewAdapter extends RecyclerView.Adapter<MyRecyclerVIewAdapter.ViewHolder> {

    //al mData le pasamos el tipo correspondiente, sea string, Objeto, etc
    private List<Peliseri> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    //esto se queda igual MENOS el parametro de entrada
    //dentro del constructor se pasan los datos de cada parametro
    //en el parametro se añade tambien el tipo de data

    public MyRecyclerVIewAdapter(Context contexto, List<Peliseri>data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(contexto);
    }


    //esto es igual
    //carga cada linea del layout de la lista cuando se necesite para no perderla

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View parten=mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return  new ViewHolder(parten);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Peliseri peli=mData.get(position);
       // holder.myTextView.setText(peli.getImagen());
        holder.portada.setImageResource(R.drawable.p1899);
        // holder.numAlum.setText(String.valueOf(pais.getNumero()));
    }




    //esto es igual
    // total de numeros de elementos del array, devuelve tamaño de la lista
    @Override
    public int getItemCount() {
        return mData.size();
    }


    //aqui se cambia
    //va reciclando y almacenando las views para que funcione el scroll de la pantalla
    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
      //  TextView myTextView;
       ImageView portada;
      //  TextView numAlum;
        ViewHolder (View itemView){
            super(itemView);
            //aqui tambien se cambian cosillas
          //  myTextView= itemView.findViewById(R.id.txtPais);
           // itemView.setOnClickListener(this);

            portada=itemView.findViewById(R.id.imagenPS);
            //numAlum=itemView.findViewById(R.id.txtNumPers);

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
    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener=itemClickListener;
    }
    //datos que se pasan a activity que implementará el metodo para responder al click
    public interface ItemClickListener {
        void onItemClick(View activista, int position);
    }
}



