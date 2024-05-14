package com.example.tfg_inmaperez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
/*
public class MyRecyclerVIewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    //al mData le pasamos el tipo correspondiente, sea string, Objeto, etc
    private List<Pais> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;



    //esto se queda igual MENOS el parametro de entrada
    //dentro del constructor se pasan los datos de cada parametro
    //en el parametro se añade tambien el tipo de data
    MyRecyclerViewAdapter(Context contexto, List<Pais>data){
        this.mInflater=LayoutInflater.from(contexto);
        this.mData=data;

    }
    //esto es igual
    //carga cada linea del layout de la lista cuando se necesite para no perderla

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View parten=mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return  new ViewHolder(parten);
    }

    //esto se CAMBIA segun la clase del adaptador
    //une el dato al textView en cada fila,  en cada posicion y en cada texto del array
    //esto viene del ViewHolder

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        Pais pais=mData.get(position);
        holder.myTextView.setText(pais.getNombrePais());
        holder.imgenPais.setImageResource(pais.getImagenPais());
        holder.numAlum.setText(String.valueOf(pais.getNumero()));

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
        TextView myTextView;
        ImageView imgenPais;
        TextView numAlum;
        ViewHolder (View itemView){
            super(itemView);
            //aqui tambien se cambian cosillas
            myTextView= itemView.findViewById(R.id.txtPais);
            itemView.setOnClickListener(this);

            imgenPais=itemView.findViewById(R.id.imgPais);
            numAlum=itemView.findViewById(R.id.txtNumPers);


        }
        //NO cambia
        @Override
        public void onClick(View view) {
            if(mClickListener != null)
                mClickListener.onItemClick(view,getAdapterPosition());
        }
    }
    //obtiene la posicion donde se está situado en la pantalla
    Pais getItem(int id){
        return mData.get(id);
    }

    //todos los clicks por los que va pasando
    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener=itemClickListener;
    }
    //datos que se pasan a activity que implementará el metodo para responder al click
    public interface  ItemClickListener {
        void onItemClick(View activista, int position);
    }
}


}
*/