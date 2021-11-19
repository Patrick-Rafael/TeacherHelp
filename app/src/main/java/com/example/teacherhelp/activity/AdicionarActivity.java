package com.example.teacherhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teacherhelp.R;
import com.example.teacherhelp.config.ConfiguracaoFireBase;
import com.example.teacherhelp.model.Enquetes;
import com.example.teacherhelp.model.Programa;
import com.example.teacherhelp.model.Programas;
import com.example.teacherhelp.model.Registro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdicionarActivity extends AppCompatActivity {

    private EditText textNomeDoPrograma;
    private EditText textResumoDoPrograma;
    private Button buttonEnviar;
    private DatabaseReference programaReferencia;
    private FirebaseAuth autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
    private DatabaseReference fireBaseRef = ConfiguracaoFireBase.getFirebaseDataBase();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario;
    private DatabaseReference enqueteReferencia;

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

        String idUsuario = autenticacao.getCurrentUser().getUid();
        usuarioRef = fireBaseRef.child("Usuarios").child(idUsuario);

        valueEventListenerUsuario = usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Registro usuario = snapshot.getValue(Registro.class);

                //nome.setText(usuario.getNome());

                String titulo = textNomeDoPrograma.getText().toString();
                String resumo = textResumoDoPrograma.getText().toString();


                if (!titulo.isEmpty()) {
                    if (!resumo.isEmpty()) {

                        Enquetes enquete = new Enquetes(titulo, resumo, usuario.getNome());

                        programaReferencia.push().setValue(enquete);


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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    public void ids() {

        textNomeDoPrograma = findViewById(R.id.editTituloDoPrograma);
        textResumoDoPrograma = findViewById(R.id.editResumoDoPrograma);
        buttonEnviar = findViewById(R.id.buttonAdcionar);

    }
}