package android.curso.projetociamacarrao.view.fragments;

import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.adapter.ResultadoMassas;
import android.curso.projetociamacarrao.view.adapter.ResultadoMolhos;
import android.curso.projetociamacarrao.view.controller.MassasController;
import android.curso.projetociamacarrao.view.controller.MolhosController;
import android.curso.projetociamacarrao.view.model.Massas;
import android.curso.projetociamacarrao.view.model.Molhos;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MinhasMassas extends Fragment{

    ArrayList<Massas> dataSet;
    View view;
    GridView listView;

    MassasController controller;


    public MinhasMassas() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_massas, container, false);

        controller = new MassasController(getContext());


        listView = view.findViewById(R.id.listview);// pegar o item do fragment resultado final pelo id.
        dataSet = controller.getAllMassas();


        final ResultadoMassas adapter = new ResultadoMassas(dataSet , getContext());

        listView.setAdapter(adapter);

        //controller.getResultadoFinal();// tem que retornar um arraylist , pois o adapter precisa de um arraylist

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// usar o snackbar ao clicar e segurar o item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Massas massas = dataSet.get(position);
//                Snackbar.make(view, massas.getId_massa() + "\nNome" + massas.getNm_massa() + " - Valor: " + massas.getVl_preco(), Snackbar.LENGTH_LONG)
//                        .setAction("No action",null).show();






                //TODO: Para atualizar a listview após alguma alteração no evento do clique na listview

                // dataSet = controller.getAllResultadoFinal(); // lê de novo o dataSet
                //adapter.atualizarLista(dataSet);// e atualiza a lista


            }
        });


        return view;
    }

}
