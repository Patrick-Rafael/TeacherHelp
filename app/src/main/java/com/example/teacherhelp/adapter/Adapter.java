package com.example.teacherhelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherhelp.R;
import com.example.teacherhelp.activity.ClickRecycler;
import com.example.teacherhelp.model.Enquetes;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Enquetes> listaEnquetesMain;
    private final ClickRecycler clickRecycler;

    public Adapter(List<Enquetes> lista, ClickRecycler clickRecycler ) {
        this.listaEnquetesMain = lista;
        this.clickRecycler = clickRecycler;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent,false);

        return new MyViewHolder( itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Enquetes enquete = listaEnquetesMain.get( position );

        holder.titulo.setText(enquete.getTitulo());
        holder.resumo.setText(enquete.getResumo());


    }

    @Override
    public int getItemCount() {
        return listaEnquetesMain.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView resumo;
        TextView titulo;

        public MyViewHolder(View itemView) {
            super(itemView);

            resumo = itemView.findViewById(R.id.textResumoAdapter);
            titulo = itemView.findViewById(R.id.textTituloAdapter);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecycler.onItemClick(getAdapterPosition());
                }
            });

        }
    }


}
