package android.curso.projetociamacarrao.view.dataModel;

public class BebidasDataModel {

    private final static String TABELA = "Bebidas";
    private final static String id_bebida = "id_bebida";
    private final static String nm_bebida = "nm_bebida";
    private final static String  vl_preco = "vl_preco";
    private final static String  qtd_bebida = "qtd_bebida";


    private static String queryCriarTabela = "";

    public static String criarTabela(){

        queryCriarTabela = "CREATE TABLE " + getTABELA();
        queryCriarTabela += " (";
        queryCriarTabela += getId_bebida() + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += getNm_bebida() + " TEXT, ";
        queryCriarTabela += getVl_preco() + " REAL, ";
        queryCriarTabela += getQtd_bebida() + " INTEGER ";
        queryCriarTabela += " )";


        return queryCriarTabela;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getId_bebida() {
        return id_bebida;
    }

    public static String getNm_bebida() {
        return nm_bebida;
    }

    public static String getVl_preco() {
        return vl_preco;
    }

    public static String getQtd_bebida() {
        return qtd_bebida;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        BebidasDataModel.queryCriarTabela = queryCriarTabela;
    }
}
