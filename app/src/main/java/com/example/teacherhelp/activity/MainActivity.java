package com.example.teacherhelp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.teacherhelp.Fragment.HomeFragment;
import com.example.teacherhelp.Fragment.ProgramasFragment;
import com.example.teacherhelp.R;
import com.example.teacherhelp.adapter.Adapter;
import com.example.teacherhelp.configFireBase.ConfiguracaoFirebase;
import com.example.teacherhelp.model.Enquetes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Enquetes> listaPrograma = new ArrayList<>();
    private FirebaseAuth autenticacao;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private FloatingActionButton buttonAdicionar;
    private Adapter adapter;
    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);

        commitFragment("Home",new HomeFragment());
        recyclerView = findViewById(R.id.recyclerViewMain);
        buttonAdicionar = findViewById(R.id.buttonAdicionarActivity);
        smartTabLayout = findViewById(R.id.viewPagerTab);
        viewPager = findViewById(R.id.viewpager);



        //Configurar adapter para as abas
        FragmentPagerItemAdapter adapterTab = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Duvidas", HomeFragment.class)
                        .add("Programas", ProgramasFragment.class)
                .create()
        );

        viewPager.setAdapter(adapterTab);
        smartTabLayout.setViewPager(viewPager);



        //evento de clique
        /*recyclerView.addOnItemTouchListener(

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


        );*/
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


    public void commitFragment(String title, Fragment selectedFragment) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#464545\">" + title + "</font>"));
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.ActivityMAIN, selectedFragment).commit();
    }



}