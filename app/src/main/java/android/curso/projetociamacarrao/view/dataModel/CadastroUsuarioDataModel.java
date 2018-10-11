package android.curso.projetociamacarrao.view.dataModel;

public class CadastroUsuarioDataModel {

    private final static String TABELA = "Usuarios";
    private final static String id_usuario = "id_usuario";
    private final static String nm_usuario = "nm_usuario";
    private final static String  nr_cpf = "nr_cpf";
    private final static String  nm_login = "nm_login";
    private final static String  nm_senha = "nm_senha";
    private final static String  nm_email = "nm_email";
    private final static String  nm_endereco = "nm_endereco";
    private final static String  nr_endereco = "nr_endereco";
    private final static String  nr_tel = "nr_tel";

    private static String queryCriarTabela = "";

    public static String criarTabela(){

        queryCriarTabela = "CREATE TABLE " + getTABELA();
        queryCriarTabela += " (";
        queryCriarTabela += getId_usuario() + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela += getNm_usuario() + " TEXT, ";
        queryCriarTabela += getNr_cpf() + " TEXT, ";
        queryCriarTabela += getNm_login() + " TEXT, ";
        queryCriarTabela += getNm_senha() + " TEXT, ";
        queryCriarTabela += getNm_email() + " TEXT, ";
        queryCriarTabela += getNm_endereco() + " TEXT, ";
        queryCriarTabela += getNr_endereco() + " INTEGER, ";
        queryCriarTabela += getNr_tel() + " TEXT ";
        queryCriarTabela += " )";


        return queryCriarTabela;
    }


    public static String getTABELA() {
        return TABELA;
    }

    public static String getId_usuario() {
        return id_usuario;
    }

    public static String getNm_usuario() {
        return nm_usuario;
    }

    public static String getNr_cpf() {
        return nr_cpf;
    }

    public static String getNm_login() {
        return nm_login;
    }

    public static String getNm_senha() {
        return nm_senha;
    }

    public static String getNm_email() {
        return nm_email;
    }

    public static String getNm_endereco() {
        return nm_endereco;
    }

    public static String getNr_endereco() {
        return nr_endereco;
    }

    public static String getNr_tel() {
        return nr_tel;
    }

    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }

    public static void setQueryCriarTabela(String queryCriarTabela) {
        CadastroUsuarioDataModel.queryCriarTabela = queryCriarTabela;
    }
}
