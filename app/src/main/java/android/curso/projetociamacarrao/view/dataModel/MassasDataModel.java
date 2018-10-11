package android.curso.projetociamacarrao.view.dataModel;

public class MassasDataModel {

    private final static String TABELA = "Massas";
    private final static String id_massa = "id_massa";
    private final static String nm_massa = "nm_massa";
    private final static String  vl_preco = "vl_preco";
    private final static String  qtd_massa = "qtd_massa";


    private static String queryCriarTabela = "";

    public static String criarTabela(){

        queryCriarTabela = "CREATE TABLE " + getTABELA();
        queryCriarTabela += " (";
        queryCriarTabela += getId_massa() + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += getNm_massa() + " TEXT, ";
        queryCriarTabela += getVl_preco() + " REAL, ";
        queryCriarTabela += getQtd_massa() + " INTEGER ";
        queryCriarTabela += " )";


        return queryCriarTabela;
    }




    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        MassasDataModel.queryCriarTabela = queryCriarTabela;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getId_massa() {
        return id_massa;
    }

    public static String getNm_massa() {
        return nm_massa;
    }

    public static String getVl_preco() {
        return vl_preco;
    }

    public static String getQtd_massa() {
        return qtd_massa;
    }
}
