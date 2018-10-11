package android.curso.projetociamacarrao.view.controller;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.dataModel.CadastroUsuarioDataModel;
import android.curso.projetociamacarrao.view.dataSource.DataSource;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;

import java.util.ArrayList;
import java.util.List;

public class CadastroUsuarioController extends DataSource {

    ContentValues dados;

    public CadastroUsuarioController(Context context) {
        super(context);
    }

    public boolean salvar(CadastroUsuario obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(CadastroUsuarioDataModel.getNm_usuario(),obj.getNm_usuario());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(CadastroUsuarioDataModel.getNr_cpf(),obj.getNr_cpf());
        dados.put(CadastroUsuarioDataModel.getNm_login(),obj.getNm_login());
        dados.put(CadastroUsuarioDataModel.getNm_senha(),obj.getNm_senha());
        dados.put(CadastroUsuarioDataModel.getNm_email(),obj.getNm_email());
        dados.put(CadastroUsuarioDataModel.getNm_endereco(),obj.getNm_endereco());
        dados.put(CadastroUsuarioDataModel.getNr_endereco(),obj.getNr_endereco());
        dados.put(CadastroUsuarioDataModel.getNr_tel(),obj.getNr_tel());

        sucesso = insert(CadastroUsuarioDataModel.getTABELA(),dados);



        return sucesso;

    }

    public boolean validarLogin(String vLogin, String vSenha, CadastroUsuario obj){
        if (vLogin.equals(obj.getNm_login()) && vSenha.equals(obj.getNm_senha())){


            return true;
        }else{
            return false;
        }
    }
    public boolean deletar(CadastroUsuario obj){
        boolean sucesso = true;

        sucesso = deletar(CadastroUsuarioDataModel.getTABELA(),obj.getId_usuario());


        return sucesso;
    }
    public boolean alterar(CadastroUsuario obj){

        boolean sucesso = true;

        dados = new ContentValues();
        dados.put(CadastroUsuarioDataModel.getId_usuario(),obj.getId_usuario());
        dados.put(CadastroUsuarioDataModel.getNm_usuario(),obj.getNm_usuario());// key é o nome da coluna e o valor é o getters e setters do oBJ
        dados.put(CadastroUsuarioDataModel.getNr_cpf(),obj.getNr_cpf());
        dados.put(CadastroUsuarioDataModel.getNm_login(),obj.getNm_login());
        dados.put(CadastroUsuarioDataModel.getNm_senha(),obj.getNm_senha());
        dados.put(CadastroUsuarioDataModel.getNm_email(),obj.getNm_email());
        dados.put(CadastroUsuarioDataModel.getNm_endereco(),obj.getNm_endereco());
        dados.put(CadastroUsuarioDataModel.getNr_endereco(),obj.getNr_endereco());
        dados.put(CadastroUsuarioDataModel.getNr_tel(),obj.getNr_tel());


        sucesso = alterar(CadastroUsuarioDataModel.getTABELA(),dados);



        return sucesso;

    }

    public List<CadastroUsuario> listar(){

        return getAllCadastroUsuario();
    }

    public ArrayList<CadastroUsuario> getResultadoFinal(){

        return getAllResultadoFinal();

    }

    public boolean validar(CadastroUsuario obj){

        boolean sucesso = true;
        dados = new ContentValues();

        dados.put(CadastroUsuarioDataModel.getNm_senha(),obj.getNm_senha());
        dados.put(CadastroUsuarioDataModel.getNm_email(),obj.getNm_email());


        return sucesso;

    }



}
