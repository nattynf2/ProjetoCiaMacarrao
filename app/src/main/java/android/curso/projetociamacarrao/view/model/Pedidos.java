package android.curso.projetociamacarrao.view.model;

public class Pedidos {



    private int id_pedido;
    private String nm_massa ;
    private String nm_molho;
    private String nm_adicional;
    private int qtd_comida;
    private String nm_bebida;
    private int qtd_bebida;
    private double valor_total ;
    private String dt_pedido;
    private String nm_usuario;


public Pedidos(){

}

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getNm_massa() {
        return nm_massa;
    }

    public void setNm_massa(String nm_massa) {
        this.nm_massa = nm_massa;
    }

    public String getNm_molho() {
        return nm_molho;
    }

    public void setNm_molho(String nm_molho) {
        this.nm_molho = nm_molho;
    }

    public String getNm_adicional() {
        return nm_adicional;
    }

    public void setNm_adicional(String nm_adicional) {
        this.nm_adicional = nm_adicional;
    }

    public int getQtd_comida() {
        return qtd_comida;
    }

    public void setQtd_comida(int qtd_comida) {
        this.qtd_comida = qtd_comida;
    }

    public String getNm_bebida() {
        return nm_bebida;
    }

    public void setNm_bebida(String nm_bebida) {
        this.nm_bebida = nm_bebida;
    }

    public int getQtd_bebida() {
        return qtd_bebida;
    }

    public void setQtd_bebida(int qtd_bebida) {
        this.qtd_bebida = qtd_bebida;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public String getDt_pedido() {
        return dt_pedido;
    }

    public void setDt_pedido(String dt_pedido) {
        this.dt_pedido = dt_pedido;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }


}
