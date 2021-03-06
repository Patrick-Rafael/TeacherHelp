package com.example.teacherhelp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.teacherhelp.ClickListener;
import com.example.teacherhelp.R;
import com.example.teacherhelp.activity.ExibirProgramas;
import com.example.teacherhelp.activity.MainActivity;
import com.example.teacherhelp.adapter.AdapterProgramas;
import com.example.teacherhelp.model.Programas;

import java.util.ArrayList;
import java.util.List;


public class ProgramasFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RecyclerView recyclerViewProgramas;
    private List<Programas> listaProgramas = new ArrayList<>();
    private View viewProgramas;
    private AdapterProgramas adapterProgramas;


    private String mParam1;
    private String mParam2;

    public ProgramasFragment() {
        // Required empty public constructor
    }



    public static ProgramasFragment newInstance(String param1, String param2) {
        ProgramasFragment fragment = new ProgramasFragment();
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
        viewProgramas = inflater.inflate(R.layout.fragment_programas, container, false);
        iniciarComponentes();

        //Configurar adapter
        adapterProgramas = new AdapterProgramas(listaProgramas);

        //Listagem
        criarPrograma();


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewProgramas.setLayoutManager(layoutManager);
        recyclerViewProgramas.setHasFixedSize(true);
        recyclerViewProgramas.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerViewProgramas.setAdapter(adapterProgramas);

        //evento de clique
        recyclerViewProgramas.addOnItemTouchListener(

                new ClickListener(
                        getActivity(),
                        recyclerViewProgramas,
                        new ClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Programas programa = listaProgramas.get(position);

                                Bundle bundle = new Bundle();
                                bundle.putSerializable("Programa",programa );

                                Intent intent_programa = new Intent(getActivity(),ExibirProgramas.class);
                                intent_programa.putExtras(bundle);
                                getActivity().startActivity(intent_programa);

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




      return viewProgramas;

    }


    public void criarPrograma(){

        Programas programas = new Programas("Office 365", "Cont??m Word, Excel, PowerPoint entre outros programas gratuitos", R.drawable.logooffice,"https://www.office.com/?auth=1&from=ShellNav",getString(R.string.text_office)
        );
        this.listaProgramas.add(programas);

        programas = new Programas("Zoom", "Reuni??es online, Boa estabilidade e gr??tis.", R.drawable.logozoom,"https://zoom.us/",getString(R.string.text_zoom));
        this.listaProgramas.add(programas);

        programas = new Programas("Google ClassRoom", "Sistema de gerenciamento de conte??do para escolas, de forma online gratuito ", R.drawable.logoclassroom,"https://edu.google.com/intl/ALL_br/products/classroom/", getString(R.string.text_classRoom) );
        this.listaProgramas.add(programas);

        programas = new Programas("Kahoot", "Kahoot ?? uma plataforma de aprendizado baseada em jogos que permite criar divertidos jogos de aprendizagem.", R.drawable.logokahoot,"https://play.google.com/store/apps/details?id=no.mobitroll.kahoot.android&hl=pt_BR&gl=US",getString(R.string.text_kahoot));
        this.listaProgramas.add(programas);

        programas = new Programas("Google Meet", "Google Meet ?? um servi??o de comunica????o por v??deo desenvolvido pelo Google.", R.drawable.logomeet,"https://meet.google.com/#", getString(R.string.text_googlemeet) );
        this.listaProgramas.add(programas);

        programas = new Programas("CommonLit", "CommonLit ?? um banco de dados de materiais de instru????o liter??rios que voc?? pode usar para elaborar aulas.", R.drawable.logocommonlit,
                "https://www.commonlit.org/en", getString(R.string.text_commonlit) );
        this.listaProgramas.add(programas);


    }


    public void iniciarComponentes() {
        recyclerViewProgramas = viewProgramas.findViewById(R.id.recyclerViewProgramas);
    }

}