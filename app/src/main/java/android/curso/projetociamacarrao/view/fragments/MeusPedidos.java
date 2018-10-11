package android.curso.projetociamacarrao.view.fragments;

import android.content.Context;
import android.curso.projetociamacarrao.view.adapter.ResultadoPedidos;
import android.curso.projetociamacarrao.view.controller.PedidosClientesController;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.curso.projetociamacarrao.R;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MeusPedidos extends Fragment {


    ArrayList<Pedidos> dataSet;
    View view;
    ListView listView;
    PedidosClientesController controller;


    public MeusPedidos() {
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
        view = inflater.inflate(R.layout.fragment_meus_pedidos, container, false);

        controller = new PedidosClientesController(getContext());


        listView = view.findViewById(R.id.listview);// pegar o item do fragment resultado final pelo id.
        dataSet = controller.getAllPedidos();


        if (dataSet!= null) {
            final ResultadoPedidos adapter = new ResultadoPedidos(dataSet, getContext());

            listView.setAdapter(adapter);
        }
        //controller.getResultadoFinal();// tem que retornar um arraylist , pois o adapter precisa de um arraylist

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// usar o snackbar ao clicar e segurar o item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pedidos pedidos = dataSet.get(position);
                Snackbar.make(view, pedidos.getNm_usuario() + "\n" + pedidos.getId_pedido() + " Valor: " + pedidos.getValor_total(), Snackbar.LENGTH_LONG)
                        .setAction("No action",null).show();

                //TODO: Para atualizar a listview após alguma alteração no evento do clique na listview

                // dataSet = controller.getAllResultadoFinal(); // lê de novo o dataSet
                //adapter.atualizarLista(dataSet);// e atualiza a lista


            }
        });


        return view;
    }

}
