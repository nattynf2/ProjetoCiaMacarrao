package android.curso.projetociamacarrao.view.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.MainActivity;
import android.curso.projetociamacarrao.view.dataModel.CadastroUsuarioDataModel;
import android.curso.projetociamacarrao.view.dataModel.PedidosClientesDataModel;

import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class DataSourcePedidos extends SQLiteOpenHelper {

    private static final String DB_NAME = "Pedidos.sqlite";
    private static final int DB_VERSION = 1;


    Cursor cursor;
    //id da coluna e conteudo
    SQLiteDatabase db;// permite o CRUD


    public DataSourcePedidos(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(PedidosClientesDataModel.criarTabela());
        }catch (Exception e){
            Log.e("Pedidos", "DB ---->ERRO: " + e.getMessage());


        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvarDados(){

    }
    public void deletarDados(){

    }
    public void atualizarDados(){

    }
    public boolean insert(String tabela, ContentValues dados){

        boolean sucesso = true;

        try{
            sucesso = db.insert(tabela,null, dados)>0;

        }catch (Exception e){

            sucesso = false;
        }

        return sucesso;
    } // para validar os campos e tabela e verificar


    public boolean deletarPedido(String tabela, int id){

        boolean sucesso = true;

        sucesso = db.delete(tabela,"id_pedido=?", new String[]{Integer.toString(id)}) > 0; // new string para substituir a ?

        return sucesso;
    }
    public boolean alterar(String tabela, ContentValues dados){

        boolean sucesso = true;
        int id = dados.getAsInteger("id");


        sucesso = db.update(tabela,dados,"id=?", new String[]{Integer.toString(id)}) > 0; // new string para substituir a ?

        return sucesso;
    }

    public List<Pedidos> getAllCadastroUsuario(){

        Pedidos obj;

        List<Pedidos> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + PedidosClientesDataModel.getTABELA() + " WHERE id_usuario =" + MainActivity.cid;
        //devolver os dados conforme o resultSet faz com java
        cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){// se encontrar, move para a 1 posicao valida
            do {
                obj = new Pedidos();
                obj. setId_pedido(cursor.getInt(cursor.getColumnIndex(PedidosClientesDataModel.getId_pedido())));// pegar o valor do id no obj pelo nome da coluna no datamodel
                obj.setNm_usuario(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_usuario())));// pegar o valor da usuario no obj
                lista.add(obj);

            }while (cursor.moveToNext());

        }
        cursor.close();
        return lista;
    }





    public Pedidos buscarObjPeloID(String tabela , Pedidos obj){

        Pedidos pedidos = new Pedidos();

        String consultaSQL = "SELECT * FROM " + tabela + " WHERE id_pedido = '" + obj.getId_pedido() + "'";

        cursor = db.rawQuery(consultaSQL,null);
        if (cursor.moveToFirst()){
            obj = new Pedidos();




            obj.setId_pedido(cursor.getInt(cursor.getColumnIndex(PedidosClientesDataModel.getId_pedido())));
            obj.setNm_massa(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_massa())));
            obj.setNm_molho(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_molho())));
            obj.setNm_adicional(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_adicional())));
            obj.setQtd_comida(cursor.getInt(cursor.getColumnIndex(PedidosClientesDataModel.getQtd_comida())));
            obj.setNm_bebida(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_bebida())));
            obj.setQtd_bebida(cursor.getInt(cursor.getColumnIndex(PedidosClientesDataModel.getQtd_bebida())));
            obj.setValor_total(cursor.getDouble(cursor.getColumnIndex(PedidosClientesDataModel.getValor_total())));
            obj.setDt_pedido(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getDt_pedido())));
            obj.setNm_usuario(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_usuario())));


        }
        return obj;
    }

    public void backupBancoDeDados(){
        File sd; // caminho destino - Download
        File data; // caminho de Origem - data/data/pacote
        File arquivoBancoDeDados; // Nome do banco de dados
        File arquivoBackupBancoDeDados; // Nome do backup
        FileChannel origem; // Leitura do arquivo original
        FileChannel destino; // Gravação com o arquivo destino com o backup

        try{

            sd =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            data = Environment.getDataDirectory();

            Log.v("DB","SD - " + sd.getAbsolutePath());
            Log.v("DB","DATA - " + data.getAbsolutePath());

            if (sd.canWrite()){

                String nomeBancoDeDados = "//data//android.curso.projetociamacarrao//databases/" + DB_NAME;
                String nomeDoArquivoDeBackup= "bkp_"+DB_NAME;

                arquivoBancoDeDados = new File(data,nomeBancoDeDados);
                arquivoBackupBancoDeDados = new File(sd,nomeDoArquivoDeBackup);

                if (arquivoBancoDeDados.exists()){
                    origem = new FileInputStream(arquivoBancoDeDados).getChannel();

                    destino = new FileOutputStream(arquivoBackupBancoDeDados).getChannel();
                    destino.transferFrom(origem,0,origem.size());// do primeiro Byte até o último origem.size();

                    origem.close();
                    destino.close();


                }
            }
        }catch (Exception e){
            Log.e("DB","Erro: " + e.getMessage());

        }

    }

    //para WEBS - quando sincronizar os dados - deletar a tabela atual
    public void deletarTabela(String tabela){
        try{
            db.execSQL("DROP TABLE IF EXISTS " + tabela);
        }catch (Exception e){

        }
    }

    public void criarTabela(String queryCriarTabela){
        try{
            db.execSQL(queryCriarTabela);

        }catch (SQLiteCantOpenDatabaseException e){

        }

    }

    //public void

    public ArrayList<Pedidos> getAllPedidos(){

        Pedidos obj;
        String sql;
        ArrayList<Pedidos> lista = new ArrayList<>();
//todo - lembrar quando for o admin listar todos os pedidos
        if(MainActivity.cnome.equals("administrador")){
            sql = "SELECT * FROM " + PedidosClientesDataModel.getTABELA();
        }else {
            sql = "SELECT * FROM " + PedidosClientesDataModel.getTABELA() + " WHERE nm_usuario = " + "'" + MainActivity.cnome + "'";
        }
        //devolver os dados conforme o resultSet faz com java
        cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){// se encontrar, move para a 1 posicao valida
            do {
                //TODO - busca todos os valores para popular o arraylist objeto Pedidos
                obj = new Pedidos();
                obj.setId_pedido(cursor.getInt(cursor.getColumnIndex(PedidosClientesDataModel.getId_pedido())));
                obj.setNm_massa(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_massa())));
                obj.setNm_molho(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_molho())));
                obj.setNm_adicional(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_adicional())));
                obj.setQtd_comida(cursor.getInt(cursor.getColumnIndex(PedidosClientesDataModel.getQtd_comida())));
                obj.setNm_bebida(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_bebida())));
                obj.setQtd_bebida(cursor.getInt(cursor.getColumnIndex(PedidosClientesDataModel.getQtd_bebida())));
                obj.setValor_total(cursor.getDouble(cursor.getColumnIndex(PedidosClientesDataModel.getValor_total())));
                obj.setDt_pedido(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getDt_pedido())));
                obj.setNm_usuario(cursor.getString(cursor.getColumnIndex(PedidosClientesDataModel.getNm_usuario())));

                lista.add(obj);

            }while (cursor.moveToNext());

        }
        cursor.close();
        return lista;
    }

}
