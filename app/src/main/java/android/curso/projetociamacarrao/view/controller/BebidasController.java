package android.curso.projetociamacarrao.view.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.dataModel.BebidasDataModel;
import android.curso.projetociamacarrao.view.dataModel.MassasDataModel;
import android.curso.projetociamacarrao.view.dataSource.DataSourceBebidas;
import android.curso.projetociamacarrao.view.dataSource.DataSourceMassas;
import android.curso.projetociamacarrao.view.model.Bebidas;
import android.curso.projetociamacarrao.view.model.Massas;

import java.util.ArrayList;
import java.util.List;

public class BebidasController extends DataSourceBebidas {
    ContentValues dados;

    public BebidasController(Context context) {
        super(context);
    }


    public boolean salvar(Bebidas obj){

        boolean sucesso = true;

        dados = new ContentValues();
        // key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(BebidasDataModel.getNm_bebida(),obj.getNm_bebida());
        dados.put(BebidasDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(BebidasDataModel.getQtd_bebida(),obj.getQtd_bebida());


        sucesso = insert(BebidasDataModel.getTABELA(),dados);



        return sucesso;

    }

    public boolean deletar(Bebidas obj){
        boolean sucesso = true;

        sucesso = deletar(BebidasDataModel.getTABELA(),obj.getId_bebida());


        return sucesso;
    }
    public boolean alterar(Bebidas obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(BebidasDataModel.getId_bebida(),obj.getId_bebida());
        dados.put(BebidasDataModel.getNm_bebida(),obj.getNm_bebida());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(BebidasDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(BebidasDataModel.getQtd_bebida(),obj.getQtd_bebida());



        sucesso = alterar(BebidasDataModel.getTABELA(),dados);



        return sucesso;

    }

    public List<Bebidas> listar(){

        return getAllBebidas();
    }

    public ArrayList<Bebidas> getResultadoFinal(){

        return getAllBebidas();

    }


}
