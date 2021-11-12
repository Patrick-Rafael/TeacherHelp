package com.example.teacherhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.AbstractAccountAuthenticator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.teacherhelp.ClickListener;
import com.example.teacherhelp.R;
import com.example.teacherhelp.adapter.Adapter;
import com.example.teacherhelp.configFireBase.ConfiguracaoFirebase;
import com.example.teacherhelp.model.Programas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Programas> listaPrograma = new ArrayList<>();
    private FirebaseAuth autenticacao;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FloatingActionButton buttonAdicionar;
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        buttonAdicionar = findViewById(R.id.buttonAdicionarActivity);





        //Redireciona para a pagina de adicionar

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AdicionarActivity.class);
                startActivity(intent);



            }
        });

        //Configurar adapter
         adapter = new Adapter(listaPrograma);


        //Configurar RecyclerView

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);


        pegarlista();


        //evento de clique
        recyclerView.addOnItemTouchListener(

                new ClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new ClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Programas programa = listaPrograma.get(position);

                                    Toast.makeText(getApplicationContext(),"Sem link ainda", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )


        );
    }



    //Infla o menu na actionBar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    //Adiciona envento de clique nas opções
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_sair:
                ConfiguracaoFirebase.getFireBaseAutenticacao().signOut();
                Intent intent_sair = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent_sair);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);



    }

    public void pegarlista(){

        database = FirebaseDatabase.getInstance().getReference().child("Programas");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listaPrograma.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Programas programas = dataSnapshot.getValue(Programas.class);

                    listaPrograma.add(programas);

                }

                Collections.reverse(listaPrograma);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}