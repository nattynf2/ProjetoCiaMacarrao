package android.curso.projetociamacarrao.view.dataModel;

public class AddDataModel {

    private final static String TABELA = "Adicionais";
    private final static String id_adicional = "id_adicional";
    private final static String nm_adicional = "nm_adicional";
    private final static String vl_preco = "vl_preco";
    private final static String qtd_adicional = "qtd_adicional";


    private static String queryCriarTabela = "";

    public static String criarTabela(){

        queryCriarTabela = "CREATE TABLE " + getTABELA();
        queryCriarTabela += " (";
        queryCriarTabela += getId_adicional() + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += getNm_adicional() + " TEXT, ";
        queryCriarTabela += getVl_preco() + " REAL, ";
        queryCriarTabela += getQtd_adicional() + " INTEGER ";
        queryCriarTabela += " )";


        return queryCriarTabela;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getId_adicional() {
        return id_adicional;
    }

    public static String getNm_adicional() {
        return nm_adicional;
    }

    public static String getVl_preco() {
        return vl_preco;
    }

    public static String getQtd_adicional() {
        return qtd_adicional;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        AddDataModel.queryCriarTabela = queryCriarTabela;
    }
}