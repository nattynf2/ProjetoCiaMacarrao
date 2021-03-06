package android.curso.projetociamacarrao.view.dataSource;

import android.content.ContentValues;
import android.content.Context;
import android.curso.projetociamacarrao.view.dataModel.MassasDataModel;
import android.curso.projetociamacarrao.view.dataModel.MolhosDataModel;
import android.curso.projetociamacarrao.view.model.Massas;
import android.curso.projetociamacarrao.view.model.Molhos;
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

public class DataSourceMassas extends SQLiteOpenHelper {

    private static final String DB_NAME = "Massas.sqlite";
    private static final int DB_VERSION = 1;


    Cursor cursor;
    //id da coluna e conteudo
    SQLiteDatabase db;// permite o CRUD


    public DataSourceMassas(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            db.execSQL(MassasDataModel.criarTabela());
        }catch (Exception e){
            Log.e("Massas", "DB ---->ERRO: " + e.getMessage());


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

    public boolean deletar(String tabela, int id){

        boolean sucesso = true;

        sucesso = db.delete(tabela,"id_massa=?", new String[]{Integer.toString(id)}) > 0; // new string para substituir a ?

        return sucesso;
    }


    public boolean alterar(String tabela, ContentValues dados){

        boolean sucesso = true;
        int id = dados.getAsInteger("id_massa");


        sucesso = db.update(tabela,dados,"id_massa=?", new String[]{Integer.toString(id)}) > 0; // new string para substituir a ?

        return sucesso;
    }



    public List<Massas> getAllCadastroUsuario(){

        Massas obj;

        List<Massas> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + MassasDataModel.getTABELA();
        //devolver os dados conforme o resultSet faz com java
        cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){// se encontrar, move para a 1 posicao valida
            do {
                obj = new Massas();
                obj. setId_massa(cursor.getInt(cursor.getColumnIndex(MassasDataModel.getId_massa())));// pegar o valor do id no obj pelo nome da coluna no datamodel
                obj.setNm_massa(cursor.getString(cursor.getColumnIndex(MassasDataModel.getNm_massa())));// pegar o valor da usuario no obj
                lista.add(obj);

            }while (cursor.moveToNext());

        }
        cursor.close();
        return lista;
    }
    public ArrayList<Massas> getAllMassas(){

        Massas obj;

        ArrayList<Massas> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + MassasDataModel.getTABELA();
        //devolver os dados conforme o resultSet faz com java
        cursor = db.rawQuery(sql,null);

        if (cursor.moveToFirst()){// se encontrar, move para a 1 posicao valida
            do {
                //TODO - busca todos os valores para popular o arraylist objeto MediaEscolar
                obj = new Massas();
                obj.setId_massa(cursor.getInt(cursor.getColumnIndex(MassasDataModel.getId_massa())));
                obj.setNm_massa(cursor.getString(cursor.getColumnIndex(MassasDataModel.getNm_massa())));
                obj.setVl_preco(cursor.getDouble(cursor.getColumnIndex(MassasDataModel.getVl_preco())));
                obj.setQtd_massa(cursor.getInt(cursor.getColumnIndex(MassasDataModel.getQtd_massa())));

                lista.add(obj);

            }while (cursor.moveToNext());

        }
        cursor.close();
        return lista;
    }

    public Massas buscarObjPeloID(String tabela , Massas obj){

        Massas massas = new Massas();

        String consultaSQL = "SELECT * FROM " + tabela + " WHERE id_massa = '" + obj.getId_massa() + "'";

        cursor = db.rawQuery(consultaSQL,null);
        if (cursor.moveToFirst()){
            obj = new Massas();

            obj.setId_massa(cursor.getInt(cursor.getColumnIndex(MassasDataModel.getId_massa())));
            obj.setNm_massa(cursor.getString(cursor.getColumnIndex(MassasDataModel.getNm_massa())));
            obj.setVl_preco(cursor.getDouble(cursor.getColumnIndex(MassasDataModel.getVl_preco())));
            obj.setQtd_massa(cursor.getInt(cursor.getColumnIndex(MassasDataModel.getQtd_massa())));



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


}
