package android.curso.projetociamacarrao.view.fragments;

import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.adapter.ResultadoPedidos;
import android.curso.projetociamacarrao.view.adapter.ResultadoUsuarios;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.controller.PedidosClientesController;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CadastroUsuarios extends Fragment {


    ArrayList<CadastroUsuario> dataSet;
    View view;
    ListView listView;
    CadastroUsuarioController controller;


    public CadastroUsuarios() {
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
        view = inflater.inflate(R.layout.fragment_meus_cadastros, container, false);

        controller = new CadastroUsuarioController(getContext());


        listView = view.findViewById(R.id.listview);// pegar o item do fragment resultado final pelo id.
        dataSet = controller.getResultadoFinal();

        //todo - criar o dataAdapter ResultadoUsuarios
        final ResultadoUsuarios adapter = new ResultadoUsuarios(dataSet , getContext());

        listView.setAdapter(adapter);

        //controller.getResultadoFinal();// tem que retornar um arraylist , pois o adapter precisa de um arraylist

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// usar o snackbar ao clicar e segurar o item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CadastroUsuario cadastroUsuario = dataSet.get(position);
                Snackbar.make(view, "Nome: " +cadastroUsuario.getNm_usuario() +  " Endereço: " + cadastroUsuario.getNm_endereco()+  " N: " + cadastroUsuario.getNr_endereco(), Snackbar.LENGTH_LONG)
                        .setAction("No action",null).show();

                //TODO: Para atualizar a listview após alguma alteração no evento do clique na listview

                // dataSet = controller.getAllResultadoFinal(); // lê de novo o dataSet
                //adapter.atualizarLista(dataSet);// e atualiza a lista


            }
        });


        return view;
    }

}
