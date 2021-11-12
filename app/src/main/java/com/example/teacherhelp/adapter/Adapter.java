package com.example.teacherhelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherhelp.R;
import com.example.teacherhelp.model.Programas;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Programas> listaProgramas;

    public Adapter(List<Programas> lista ) {
        this.listaProgramas = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent,false);

        return new MyViewHolder( itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Programas programa = listaProgramas.get( position );

        holder.titulo.setText(programa.getTitulo());
        holder.resumo.setText(programa.getResumo());


    }

    @Override
    public int getItemCount() {
        return listaProgramas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView resumo;
        TextView titulo;

        public MyViewHolder(View itemView) {
            super(itemView);

            resumo = itemView.findViewById(R.id.textResumoAdapter);
            titulo = itemView.findViewById(R.id.textTituloAdapter);



        }
    }


}
