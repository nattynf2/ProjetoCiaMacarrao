package android.curso.projetociamacarrao.view.fragments;

import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.adapter.ResultadoMolhos;
import android.curso.projetociamacarrao.view.adapter.ResultadoPedidos;
import android.curso.projetociamacarrao.view.controller.MolhosController;
import android.curso.projetociamacarrao.view.controller.PedidosClientesController;
import android.curso.projetociamacarrao.view.model.Molhos;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MeusMolhos extends Fragment{

    ArrayList<Molhos> dataSet;
    View view;
    GridView listView;
    MolhosController controller;


    public MeusMolhos() {
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
        view = inflater.inflate(R.layout.fragment_molhos, container, false);

        controller = new MolhosController(getContext());


        listView = view.findViewById(R.id.listview);// pegar o item do fragment resultado final pelo id.
        dataSet = controller.getAllMolhos();


        final ResultadoMolhos adapter = new ResultadoMolhos(dataSet , getContext());

        listView.setAdapter(adapter);

        //controller.getResultadoFinal();// tem que retornar um arraylist , pois o adapter precisa de um arraylist

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// usar o snackbar ao clicar e segurar o item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Molhos molhos = dataSet.get(position);
//                Snackbar.make(view, molhos.getId_molho() + "\nNome" + molhos.getNm_molho() + " - Valor: " + molhos.getVl_preco(), Snackbar.LENGTH_LONG)
//                        .setAction("No action",null).show();


                //TODO: Para atualizar a listview após alguma alteração no evento do clique na listview

                // dataSet = controller.getAllResultadoFinal(); // lê de novo o dataSet
                //adapter.atualizarLista(dataSet);// e atualiza a lista


            }
        });


        return view;
    }

}
