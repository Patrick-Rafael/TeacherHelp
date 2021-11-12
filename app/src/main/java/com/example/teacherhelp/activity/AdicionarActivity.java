package com.example.teacherhelp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teacherhelp.R;
import com.example.teacherhelp.model.Programa;
import com.example.teacherhelp.model.Programas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdicionarActivity extends AppCompatActivity {

    private EditText textNomeDoPrograma;
    private EditText textResumoDoPrograma;
    private Button buttonEnviar;
    private DatabaseReference programaReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        ids();

        //Encaminhar para o nó do FireBase

        programaReferencia = FirebaseDatabase.getInstance().getReference().child("Programas");


        //Envia os dados

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarDados();

            }
        });


    }


    public void enviarDados() {


        String nome = textNomeDoPrograma.getText().toString();
        String resumo = textResumoDoPrograma.getText().toString();


        if (!nome.isEmpty()) {
            if (!resumo.isEmpty()) {

                Programa programa = new Programa(nome, resumo);

                programaReferencia.push().setValue(programa);


                Toast.makeText(AdicionarActivity.this, "Enviado com sucesso", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AdicionarActivity.this, MainActivity.class);
                startActivity(intent);
                finish();


            } else {
                Toast.makeText(AdicionarActivity.this, "Preencha o Resumo!", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(AdicionarActivity.this, "Preencha o Titulo!", Toast.LENGTH_LONG).show();
        }


    }


    public void ids() {

        textNomeDoPrograma = findViewById(R.id.editTituloDoPrograma);
        textResumoDoPrograma = findViewById(R.id.editResumoDoPrograma);
        buttonEnviar = findViewById(R.id.buttonAdcionar);

    }
}