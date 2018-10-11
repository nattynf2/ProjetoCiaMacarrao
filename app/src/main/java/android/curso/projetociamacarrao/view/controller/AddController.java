package android.curso.projetociamacarrao.view.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.dataModel.AddDataModel;
import android.curso.projetociamacarrao.view.dataModel.MassasDataModel;
import android.curso.projetociamacarrao.view.dataSource.DataSourceAdd;
import android.curso.projetociamacarrao.view.dataSource.DataSourceMassas;
import android.curso.projetociamacarrao.view.model.Add;
import android.curso.projetociamacarrao.view.model.Massas;

import java.util.ArrayList;
import java.util.List;

public class AddController extends DataSourceAdd {
    ContentValues dados;

    public AddController(Context context) {
        super(context);
    }


    public boolean salvar(Add obj){

        boolean sucesso = true;

        dados = new ContentValues();
        // key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(AddDataModel.getNm_adicional(),obj.getNm_adicional());
        dados.put(AddDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(AddDataModel.getQtd_adicional(),obj.getQtd_adicional());


        sucesso = insert(AddDataModel.getTABELA(),dados);



        return sucesso;

    }

    public boolean deletar(Add obj){
        boolean sucesso = true;

        sucesso = deletar(AddDataModel.getTABELA(),obj.getId_adicional());


        return sucesso;
    }
    public boolean alterar(Add obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(AddDataModel.getId_adicional(),obj.getId_adicional());
        dados.put(AddDataModel.getNm_adicional(),obj.getNm_adicional());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(AddDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(AddDataModel.getQtd_adicional(),obj.getQtd_adicional());



        sucesso = alterar(AddDataModel.getTABELA(),dados);



        return sucesso;

    }

    public List<Add> listar(){

        return getAllAdds();
    }

    public ArrayList<Add> getResultadoFinal(){

        return getAllAdds();

    }


}
