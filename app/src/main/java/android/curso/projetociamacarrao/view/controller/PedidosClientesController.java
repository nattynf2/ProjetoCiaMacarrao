package android.curso.projetociamacarrao.view.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.dataModel.CadastroUsuarioDataModel;
import android.curso.projetociamacarrao.view.dataModel.PedidosClientesDataModel;
import android.curso.projetociamacarrao.view.dataSource.DataSource;
import android.curso.projetociamacarrao.view.dataSource.DataSourcePedidos;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.model.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class PedidosClientesController extends DataSourcePedidos {

    ContentValues dados;

    public PedidosClientesController(Context context) {
        super(context);
    }


    public boolean salvar(Pedidos obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(PedidosClientesDataModel.getNm_massa(),obj.getNm_massa());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(PedidosClientesDataModel.getNm_molho(),obj.getNm_molho());
        dados.put(PedidosClientesDataModel.getNm_adicional(),obj.getNm_adicional());
        dados.put(PedidosClientesDataModel.getQtd_comida(),obj.getQtd_comida());
        dados.put(PedidosClientesDataModel.getNm_bebida(),obj.getNm_bebida());
        dados.put(PedidosClientesDataModel.getQtd_bebida(),obj.getQtd_bebida());
        dados.put(PedidosClientesDataModel.getValor_total(),obj.getValor_total());
        dados.put(PedidosClientesDataModel.getDt_pedido(),obj.getDt_pedido());
        dados.put(PedidosClientesDataModel.getNm_usuario(),obj.getNm_usuario());

        sucesso = insert(PedidosClientesDataModel.getTABELA(),dados);



        return sucesso;

    }

    public boolean deletar(Pedidos obj){
        boolean sucesso = true;

        sucesso = deletarPedido(PedidosClientesDataModel.getTABELA(),obj.getId_pedido());


        return sucesso;
    }
    public boolean alterar(Pedidos obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(PedidosClientesDataModel.getId_pedido(),obj.getId_pedido());
        dados.put(PedidosClientesDataModel.getNm_massa(),obj.getNm_massa());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(PedidosClientesDataModel.getNm_molho(),obj.getNm_molho());
        dados.put(PedidosClientesDataModel.getNm_adicional(),obj.getNm_adicional());
        dados.put(PedidosClientesDataModel.getQtd_comida(),obj.getQtd_comida());
        dados.put(PedidosClientesDataModel.getNm_bebida(),obj.getNm_bebida());
        dados.put(PedidosClientesDataModel.getQtd_bebida(),obj.getQtd_bebida());
        dados.put(PedidosClientesDataModel.getValor_total(),obj.getValor_total());
        dados.put(PedidosClientesDataModel.getDt_pedido(),obj.getDt_pedido());
        dados.put(PedidosClientesDataModel.getNm_usuario(),obj.getNm_usuario());



        sucesso = alterar(PedidosClientesDataModel.getTABELA(),dados);



        return sucesso;

    }

    public List<Pedidos> listar(){

        return getAllPedidos();
    }

    public ArrayList<Pedidos> getResultadoFinal(){

        return getAllPedidos();

    }



}
