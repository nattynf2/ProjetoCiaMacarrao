package android.curso.projetociamacarrao.view.model;

public class Massas {



    private int id_massa;
    private String nm_massa;
    private double vl_preco;
    private int qtd_massa;



public Massas(){

}


    public int getId_massa() {
        return id_massa;
    }

    public void setId_massa(int id_massa) {
        this.id_massa = id_massa;
    }

    public String getNm_massa() {
        return nm_massa;
    }

    public void setNm_massa(String nm_massa) {
        this.nm_massa = nm_massa;
    }

    public double getVl_preco() {
        return vl_preco;
    }

    public void setVl_preco(double vl_preco) {
        this.vl_preco = vl_preco;
    }

    public int getQtd_massa() {
        return qtd_massa;
    }

    public void setQtd_massa(int qtd_massa) {
        this.qtd_massa = qtd_massa;
    }
}
