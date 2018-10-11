package android.curso.projetociamacarrao.view.dataModel;

public class MolhosDataModel {

    private final static String TABELA = "Molhos";
    private final static String id_molho = "id_molho";
    private final static String nm_molho = "nm_molho";
    private final static String  vl_preco = "vl_preco";
    private final static String  qtd_molho = "qtd_molho";


    private static String queryCriarTabela = "";

    public static String criarTabela(){

        queryCriarTabela = "CREATE TABLE " + getTABELA();
        queryCriarTabela += " (";
        queryCriarTabela += getId_molho() + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += getNm_molho() + " TEXT, ";
        queryCriarTabela += getVl_preco() + " REAL, ";
        queryCriarTabela += getQtd_molho() + " INTEGER ";
        queryCriarTabela += " )";


        return queryCriarTabela;
    }


    public static String getTABELA() {
        return TABELA;
    }

    public static String getId_molho() {
        return id_molho;
    }

    public static String getNm_molho() {
        return nm_molho;
    }

    public static String getVl_preco() {
        return vl_preco;
    }

    public static String getQtd_molho() {
        return qtd_molho;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        MolhosDataModel.queryCriarTabela = queryCriarTabela;
    }
}
