package android.curso.projetociamacarrao.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.fragments.IncluirDados;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.util.IncluirAsyncTask;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CadastroDeUsuario extends AppCompatActivity
{





    android.support.v4.app.FragmentManager fragmentManager;
    IncluirDados incluirDados = new IncluirDados();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_cad, incluirDados).commit();// para inserir o fragmento


    }
}













