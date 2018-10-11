package android.curso.projetociamacarrao.view.dataModel;

import java.util.Date;

public class PedidosClientesDataModel {


    private final static String TABELA = "Pedidos";
    private final static String id_pedido = "id_pedido";
    private final static String nm_massa = "nm_massa";
    private final static String  nm_molho= "nm_molho";
    private final static String  nm_adicional = "nm_adicional";
    private final static String  qtd_comida = "qtd_comida";
    private final static String  nm_bebida = "nm_bebida";
    private final static String  qtd_bebida = "qtd_bebida";
    private final static String  valor_total = "valor_total";
    private final static String dt_pedido = "dt_pedido";
    private final static String  nm_usuario = "nm_usuario";

    private static String queryCriarTabela = "";

    public static String criarTabela(){

        queryCriarTabela = "CREATE TABLE " + getTABELA();
        queryCriarTabela = getQueryCriarTabela() + " (";
        queryCriarTabela = getQueryCriarTabela() + getId_pedido() + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        queryCriarTabela = getQueryCriarTabela() + getNm_massa() + " TEXT, ";
        queryCriarTabela = getQueryCriarTabela() + getNm_molho() + " TEXT, ";
        queryCriarTabela = getQueryCriarTabela() + getNm_adicional() + " TEXT, ";
        queryCriarTabela = getQueryCriarTabela() + getQtd_comida() + " INTEGER, ";
        queryCriarTabela = getQueryCriarTabela() + getNm_bebida() + " TEXT, ";
        queryCriarTabela = getQueryCriarTabela() + getQtd_bebida() + " INTEGER, ";
        queryCriarTabela = getQueryCriarTabela() + getValor_total() + " REAL, ";
        queryCriarTabela = getQueryCriarTabela() + getDt_pedido() + " TEXT, ";
        queryCriarTabela = getQueryCriarTabela() + getNm_usuario() + " TEXT ";
        queryCriarTabela = getQueryCriarTabela() + " )";


        return getQueryCriarTabela();
    }


    public static String getTABELA() {
        return TABELA;
    }

    public static String getId_pedido() {
        return id_pedido;
    }

    public static String getNm_massa() {
        return nm_massa;
    }

    public static String getNm_molho() {
        return nm_molho;
    }

    public static String getNm_adicional() {
        return nm_adicional;
    }

    public static String getQtd_comida() {
        return qtd_comida;
    }

    public static String getNm_bebida() {
        return nm_bebida;
    }

    public static String getQtd_bebida() {
        return qtd_bebida;
    }

    public static String getValor_total() {
        return valor_total;
    }

    public static String getDt_pedido() {
        return dt_pedido;
    }

    public static String getNm_usuario() {
        return nm_usuario;
    }


    public static String getQueryCriarTabela() {
        return queryCriarTabela;
    }




    public static void setQueryCriarTabela(String queryCriarTabela) {
        PedidosClientesDataModel.queryCriarTabela = queryCriarTabela;
    }
}
