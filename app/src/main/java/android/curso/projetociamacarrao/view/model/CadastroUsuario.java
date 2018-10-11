package android.curso.projetociamacarrao.view.model;

import android.support.v4.app.Fragment;

public class CadastroUsuario extends Fragment {

    private int id_usuario;
    private String nm_usuario;
    private String nr_cpf;
    private String nm_login;
    private String nm_senha;
    private String nm_email;
    private String nm_endereco;
    private int nr_endereco;
    private String nr_tel;


    public CadastroUsuario(){

    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getNr_cpf() {
        return nr_cpf;
    }

    public void setNr_cpf(String nr_cpf) {
        this.nr_cpf = nr_cpf;
    }

    public String getNm_login() {
        return nm_login;
    }

    public void setNm_login(String nm_login) {
        this.nm_login = nm_login;
    }

    public String getNm_senha() {
        return nm_senha;
    }

    public void setNm_senha(String nm_senha) {
        this.nm_senha = nm_senha;
    }

    public String getNm_email() {
        return nm_email;
    }

    public void setNm_email(String nm_email) {
        this.nm_email = nm_email;
    }

    public String getNm_endereco() {
        return nm_endereco;
    }

    public void setNm_endereco(String nm_endereco) {
        this.nm_endereco = nm_endereco;
    }

    public int getNr_endereco() {
        return nr_endereco;
    }

    public void setNr_endereco(int nr_endereco) {
        this.nr_endereco = nr_endereco;
    }

    public String getNr_tel() {
        return nr_tel;
    }

    public void setNr_tel(String nr_tel) {
        this.nr_tel = nr_tel;
    }
}
