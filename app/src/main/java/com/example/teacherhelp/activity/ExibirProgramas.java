package com.example.teacherhelp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teacherhelp.R;
import com.example.teacherhelp.model.Programas;

public class ExibirProgramas extends AppCompatActivity  {

    private TextView tituloDoPrograma,descricaoDoPrograma;
    private ImageView imageDoPrograma;
    private Programas programaSelecionado;
    private Button buttonlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_programas);

        ids();


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        Programas programa=
                ( Programas) bundle.getSerializable("Programa");

        if(programa != null){
            programaSelecionado = programa;
        }

        validacao();

        //Log.i("testee","rr" + programaSelecionado.toString() );


    }


    public void validacao(){

        if(programaSelecionado != null){

            tituloDoPrograma.setText(programaSelecionado.getTituloPrograma());
            descricaoDoPrograma.setText(programaSelecionado.getDescricaoPrograma());
            imageDoPrograma.setImageResource(programaSelecionado.getImageprograma());

            buttonlink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    levarLink();
                }
            });

        }


    }


    public void levarLink(){

        String linkPrograma = programaSelecionado.getLinkPrograma();

        Intent link = new Intent(Intent.ACTION_VIEW,Uri.parse(linkPrograma));
        startActivity(link);


    }

    public void ids(){

        tituloDoPrograma = findViewById(R.id.textTituloDoPrograma);
        descricaoDoPrograma = findViewById(R.id.textDescricaoDoPrograma);
        imageDoPrograma = findViewById(R.id.imageDoPrograma);
        buttonlink = findViewById(R.id.buttonLink);

    }



}