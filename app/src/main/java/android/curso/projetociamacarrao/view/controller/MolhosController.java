package android.curso.projetociamacarrao.view.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.dataModel.MolhosDataModel;
import android.curso.projetociamacarrao.view.dataModel.PedidosClientesDataModel;
import android.curso.projetociamacarrao.view.dataSource.DataSourceMolhos;
import android.curso.projetociamacarrao.view.model.Molhos;
import android.curso.projetociamacarrao.view.model.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class MolhosController extends DataSourceMolhos {
    ContentValues dados;

    public MolhosController(Context context) {
        super(context);
    }


    public boolean salvar(Molhos obj){

        boolean sucesso = true;

        dados = new ContentValues();
        // key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(MolhosDataModel.getNm_molho(),obj.getNm_molho());
        dados.put(MolhosDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(MolhosDataModel.getQtd_molho(),obj.getQtd_molho());


        sucesso = insert(MolhosDataModel.getTABELA(),dados);



        return sucesso;

    }

    public boolean deletar(Molhos obj){
        boolean sucesso = true;

        sucesso = deletar(MolhosDataModel.getTABELA(),obj.getId_molho());


        return sucesso;
    }
    public boolean alterar(Molhos obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(MolhosDataModel.getId_molho(),obj.getId_molho());
        dados.put(MolhosDataModel.getNm_molho(),obj.getNm_molho());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(MolhosDataModel.getVl_preco(),obj.getVl_preco());
        dados.put(MolhosDataModel.getQtd_molho(),obj.getQtd_molho());



        sucesso = alterar(MolhosDataModel.getTABELA(),dados);



        return sucesso;

    }

    public List<Molhos> listar(){

        return getAllMolhos();
    }

    public ArrayList<Molhos> getResultadoFinal(){

        return getAllMolhos();

    }


}
