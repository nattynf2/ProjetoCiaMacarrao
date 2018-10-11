package android.curso.projetociamacarrao.view.fragments;

import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.adapter.ResultadoAdd;
import android.curso.projetociamacarrao.view.adapter.ResultadoMolhos;
import android.curso.projetociamacarrao.view.controller.AddController;
import android.curso.projetociamacarrao.view.controller.MolhosController;
import android.curso.projetociamacarrao.view.model.Add;
import android.curso.projetociamacarrao.view.model.Molhos;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MeusAdds extends Fragment{

    ArrayList<Add> dataSet;
    View view;
    GridView listView;
    AddController controller;


    public MeusAdds() {
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
        view = inflater.inflate(R.layout.fragment_adds, container, false);

        controller = new AddController(getContext());


        listView = view.findViewById(R.id.listview);// pegar o item do fragment resultado final pelo id.
        dataSet = controller.getAllAdds();


        final ResultadoAdd adapter = new ResultadoAdd(dataSet , getContext());

        listView.setAdapter(adapter);

        //controller.getResultadoFinal();// tem que retornar um arraylist , pois o adapter precisa de um arraylist

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {// usar o snackbar ao clicar e segurar o item
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Add add = dataSet.get(position);
                Snackbar.make(view, add.getId_adicional() + "\nNome" + add.getNm_adicional() + " - Valor: " + add.getVl_preco(), Snackbar.LENGTH_LONG)
                        .setAction("No action",null).show();


                //TODO: Para atualizar a listview após alguma alteração no evento do clique na listview

                // dataSet = controller.getAllResultadoFinal(); // lê de novo o dataSet
                //adapter.atualizarLista(dataSet);// e atualiza a lista


            }
        });


        return view;
    }

}
