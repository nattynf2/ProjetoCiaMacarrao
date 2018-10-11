package android.curso.projetociamacarrao.view.model;

public class Add {



    private int id_adicional;
    private String nm_adicional;
    private double vl_preco;
    private int qtd_adicional;



public Add(){

}


    public int getId_adicional() {
        return id_adicional;
    }

    public void setId_adicional(int id_adicional) {
        this.id_adicional = id_adicional;
    }

    public String getNm_adicional() {
        return nm_adicional;
    }

    public void setNm_adicional(String nm_adicional) {
        this.nm_adicional = nm_adicional;
    }

    public double getVl_preco() {
        return vl_preco;
    }

    public void setVl_preco(double vl_preco) {
        this.vl_preco = vl_preco;
    }

    public int getQtd_adicional() {
        return qtd_adicional;
    }

    public void setQtd_adicional(int qtd_adicional) {
        this.qtd_adicional = qtd_adicional;
    }
}
