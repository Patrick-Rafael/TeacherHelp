package com.example.teacherhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teacherhelp.R;
import com.example.teacherhelp.adapter.AdapterComentario;
import com.example.teacherhelp.config.ConfiguracaoFireBase;
import com.example.teacherhelp.model.Enquetes;
import com.example.teacherhelp.model.Registro;
import com.example.teacherhelp.model.Respostas;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComentarioActivity extends AppCompatActivity {

    private RecyclerView recyclerComentarios;
    private List<Respostas> listaRespostas = new ArrayList<>();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference comentarioReferenica;
    private TextView titulo, nome;
    private ImageButton buttonEnviar;
    private TextInputEditText textComentario;
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario;
    private Registro usuario;
    private final FirebaseAuth autenticacao = ConfiguracaoFireBase.getFireBaseAutenticacao();
    private final DatabaseReference fireBaseRef = ConfiguracaoFireBase.getFirebaseDataBase();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);

        recuperarUsuario();
        ids();


        //Pegando as chaves de cada item
        Bundle bundle = getIntent().getExtras();
        String chave = bundle.getString("chave");
        String textTitulo = bundle.getString("Titulo");

        //Log.i("texte", "chave: " + chave);


        //Recupera Enquete

        usuarioRef = fireBaseRef.child("Programas").child(chave);

        valueEventListenerUsuario = usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Enquetes enquetes = snapshot.getValue(Enquetes.class);

                  assert enquetes != null;
                  nome.setText(enquetes.getAutor());

               // Log.i("teste","nome: " + enquetes.getAutor());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Encaminhar para o nó do FireBase
        comentarioReferenica = FirebaseDatabase.getInstance().getReference().child("Programas").child(chave).child("Resposta");



        //Pegando Titulo da postagem
        titulo.setText(textTitulo);


        //Envia o comentario
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarComentario();
                textComentario.setText("");
                listaRespostas.clear();
            }
        });


        //recuperaResumo();
        recyclerComentarios = findViewById(R.id.recyclerComentarios);


        //Configurar adapter
        AdapterComentario adapterComentarios = new AdapterComentario(listaRespostas);


        //Configurar RecyclerView

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerComentarios.setLayoutManager(layoutManager);
        recyclerComentarios.setHasFixedSize(true);
        recyclerComentarios.setAdapter(adapterComentarios);
        recyclerComentarios.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));


        //Puxando a lista do Firebase e exibindo no RecyclerView

        database = FirebaseDatabase.getInstance().getReference().child("Programas").child(chave).child("Resposta");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Respostas respostas = dataSnapshot.getValue(Respostas.class);

                    listaRespostas.add(respostas);

                }

                adapterComentarios.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    //Envia a resposta para o nó do FireBase
    public void enviarComentario() {

        String resposta = textComentario.getText().toString();


        if (!resposta.isEmpty()) {

            Respostas respostas = new Respostas(resposta);
            respostas.setUsuario(usuario.getNome());

            comentarioReferenica.push().setValue(respostas);

            Toast.makeText(ComentarioActivity.this, "Resposta Enviada", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(ComentarioActivity.this, "Preencha o Campo", Toast.LENGTH_LONG).show();
        }

    }

    public void ids() {
        titulo = findViewById(R.id.tituloComentario);
        buttonEnviar = findViewById(R.id.buttonEnvivarTeste);
        textComentario = findViewById(R.id.textComentarioTeste);
        nome = findViewById(R.id.textNomeUsuario);
    }


    private void recuperarUsuario(){

        String idUsuario = autenticacao.getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(idUsuario);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuario = snapshot.getValue(Registro.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}