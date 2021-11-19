package com.example.teacherhelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherhelp.R;
import com.example.teacherhelp.model.Programas;

import java.util.List;

public class AdapterProgramas extends RecyclerView.Adapter<AdapterProgramas.MyViewHolder> {



    private List<Programas> listaPrograma;

    public AdapterProgramas(List<Programas> lista ) {
        this.listaPrograma = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemListaPrograma = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_programas,parent,false);

       return new MyViewHolder(itemListaPrograma);
    }

    @Override
    public void onBindViewHolder(AdapterProgramas.MyViewHolder holder, int position) {

        Programas programa = listaPrograma.get( position );

        holder.tituloPrograma.setText(programa.getTituloPrograma());
        holder.resumoPrograma.setText(programa.getResumoPrograma());
        holder.imagePrograma.setImageResource(programa.getImageprograma());

    }

    @Override
    public int getItemCount() {
        return listaPrograma.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView resumoPrograma;
        TextView tituloPrograma;
        ImageView imagePrograma;

        public MyViewHolder(View itemView) {
            super(itemView);

            resumoPrograma = itemView.findViewById(R.id.textResumoPrograma);
            tituloPrograma = itemView.findViewById(R.id.textTituloPrograma);
            imagePrograma = itemView.findViewById(R.id.imagePrograma);



        }
    }


}
