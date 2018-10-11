package android.curso.projetociamacarrao.view.model;

public class Bebidas {



    private int id_bebida;
    private String nm_bebida;
    private double vl_preco;
    private int qtd_bebida;



public Bebidas(){

}


    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
    }

    public String getNm_bebida() {
        return nm_bebida;
    }

    public void setNm_bebida(String nm_bebida) {
        this.nm_bebida = nm_bebida;
    }

    public double getVl_preco() {
        return vl_preco;
    }

    public void setVl_preco(double vl_preco) {
        this.vl_preco = vl_preco;
    }

    public int getQtd_bebida() {
        return qtd_bebida;
    }

    public void setQtd_bebida(int qtd_bebida) {
        this.qtd_bebida = qtd_bebida;
    }
}
