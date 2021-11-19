package com.example.teacherhelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.teacherhelp.R;
import com.example.teacherhelp.model.Respostas;

import java.util.List;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.MyViewHolderComentarios>{

    private List<Respostas> listaRespostas;


    public AdapterComentario(List<Respostas> listaRespostas) {
        this.listaRespostas = listaRespostas;

    }



    public MyViewHolderComentarios onCreateViewHolder(ViewGroup parent, int viewType) {

        View comentarioLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_comentario, parent, false);


        return new MyViewHolderComentarios(comentarioLista);
    }


    @Override
    public void onBindViewHolder(AdapterComentario.MyViewHolderComentarios holder, int position) {

        Respostas respostas = listaRespostas.get(position);

        holder.comentarios.setText(respostas.getResposta());

        holder.nomeComentario.setText(respostas.getUsuario());



    }


    @Override
    public int getItemCount() {
        return listaRespostas.size();
    }

    public class MyViewHolderComentarios extends RecyclerView.ViewHolder {

        TextView comentarios, nomeComentario;


        public MyViewHolderComentarios(View itemView) {
            super(itemView);

            comentarios = itemView.findViewById(R.id.textComentario);
            nomeComentario = itemView.findViewById(R.id.textNomeComentario);


        }
    }


}
