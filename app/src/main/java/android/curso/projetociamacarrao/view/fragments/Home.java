package android.curso.projetociamacarrao.view.fragments;

import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.adapter.ResultadoUsuarios;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Home extends Fragment {



    View view;

    public Home() {
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
        view = inflater.inflate(R.layout.fragment_home, container, false);


        return view;
    }

}
