package android.curso.projetociamacarrao.view.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.dataModel.MassasDataModel;
import android.curso.projetociamacarrao.view.dataModel.MolhosDataModel;
import android.curso.projetociamacarrao.view.dataSource.DataSourceMassas;
import android.curso.projetociamacarrao.view.dataSource.DataSourceMolhos;
import android.curso.projetociamacarrao.view.model.Massas;
import android.curso.projetociamacarrao.view.model.Molhos;

import java.util.ArrayList;
import java.util.List;

public class MassasController extends DataSourceMassas {
    ContentValues dados;

    public MassasController(Context context) {
        super(context);
    }


    public boolean salvar(Massas obj){

        boolean sucesso = true;

        dados = new ContentValues();
        // key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(MassasDataModel.getNm_massa(),obj.getNm_massa());
        dados.put(MassasDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(MassasDataModel.getQtd_massa(),obj.getQtd_massa());


        sucesso = insert(MassasDataModel.getTABELA(),dados);



        return sucesso;

    }

    public boolean deletar(Massas obj){
        boolean sucesso = true;

        sucesso = deletar(MassasDataModel.getTABELA(),obj.getId_massa());


        return sucesso;
    }
    public boolean alterar(Massas obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(MassasDataModel.getId_massa(),obj.getId_massa());
        dados.put(MassasDataModel.getNm_massa(),obj.getNm_massa());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(MassasDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(MassasDataModel.getQtd_massa(),obj.getQtd_massa());



        sucesso = alterar(MassasDataModel.getTABELA(),dados);



        return sucesso;

    }

    public List<Massas> listar(){

        return getAllMassas();
    }

    public ArrayList<Massas> getResultadoFinal(){

        return getAllMassas();

    }


}
