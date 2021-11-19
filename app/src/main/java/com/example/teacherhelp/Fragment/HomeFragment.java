package com.example.teacherhelp.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.teacherhelp.ClickListener;
import com.example.teacherhelp.R;
import com.example.teacherhelp.activity.AdicionarActivity;
import com.example.teacherhelp.activity.ClickRecycler;
import com.example.teacherhelp.activity.ComentarioActivity;
import com.example.teacherhelp.adapter.Adapter;
import com.example.teacherhelp.model.Enquetes;
import com.example.teacherhelp.model.Programas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomeFragment extends Fragment implements ClickRecycler {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RecyclerView recyclerView;
    private List<Enquetes> listaEnquete = new ArrayList<>();
    private FirebaseAuth autenticacao;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FloatingActionButton buttonAdicionar;
    private Adapter adapter;
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;
    private View view;


    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        iniciarComponentes();


        //Configurar adapter
        adapter = new Adapter(listaEnquete,this);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        pegarlista();


        //Redireciona para a pagina de adicionar

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), AdicionarActivity.class);
                startActivity(intent);


            }
        });



        return view;


    }

    public void iniciarComponentes() {
        recyclerView = view.findViewById(R.id.recyclerViewMain);
        buttonAdicionar = view.findViewById(R.id.buttonAdicionarActivity);
    }


    public void pegarlista() {

        database = FirebaseDatabase.getInstance().getReference().child("Programas");


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listaEnquete.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Enquetes programas = dataSnapshot.getValue(Enquetes.class);

                    programas.setChave(dataSnapshot.getKey());
                    listaEnquete.add(programas);

                }

                Collections.reverse(listaEnquete);

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //Clique

    @Override
    public void onItemClick(int position) {


        Enquetes item = listaEnquete.get(position);
        Intent intent_comentarios = new Intent(getActivity(), ComentarioActivity.class);
        intent_comentarios.putExtra("chave", item.getChave());
        intent_comentarios.putExtra("Titulo", item.getTitulo());
        startActivity(intent_comentarios);

       // Log.i("texte", "chave: " + item.getChave());

    }

    @Override
    public void onLongItemClick(int position) {

    }

}