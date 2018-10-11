package android.curso.projetociamacarrao.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.curso.projetociamacarrao.view.MainActivity;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.util.AlterarAsyncTask;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.curso.projetociamacarrao.R;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class MeusDados extends Fragment {
    View view;
    CadastroUsuario cadastroUsuario;
    CadastroUsuarioController controller;

    public static final int PICK_IMAGE = 1234;
    Button btnAtualizar;
    EditText txtNome,txtCPF,txtEmail,txtEnd,txtTel,txtNumero,txtLogin,txtSenha;

    boolean dadosValidados = false;
    ImageView imgUser;

    Context context;

    public MeusDados() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        controller = new CadastroUsuarioController(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_meus_dados, container, false);

        context = getContext();

        txtCPF = view.findViewById(R.id.txtCPF);
        txtSenha = view.findViewById(R.id.txtSenha);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtEnd = view.findViewById(R.id.txtEnd);
        txtLogin = view.findViewById(R.id.txtLogin);
        txtNumero = view.findViewById(R.id.txtNumero);
        txtTel = view.findViewById(R.id.txtTel);
        txtNome = view.findViewById(R.id.txtNome);
        imgUser = view.findViewById(R.id.imgUser);



        txtNome.setText(MainActivity.cnome);
        txtEmail.setText(MainActivity.cemail);
        txtLogin.setText(MainActivity.clogin);
        txtEnd.setText(MainActivity.cend);
        txtNumero.setText(MainActivity.cnrend);
        txtTel.setText(MainActivity.ctel);
        txtCPF.setText(MainActivity.ccpf);
        txtSenha.requestFocus();

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE);
            }
        });
        btnAtualizar = view.findViewById(R.id.btnAtualizar);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (txtSenha.getText().toString().isEmpty() ||txtEmail.getText().toString().isEmpty() ||
                            txtEnd.getText().toString().isEmpty() || txtNumero.getText().toString().isEmpty() ||
                            txtTel.getText().toString().isEmpty()){

                        UtilCadastro.showMensagem(context,"Favor preencher todos os campos!");
                        txtNome.requestFocus();
                    }else{
                        dadosValidados = true;
                    }

                    if (!txtEmail.getText().toString().contains("@")){
                        UtilCadastro.showMensagem(context,"Favor verificar o Email inserido!");
                        dadosValidados = false;
                        txtEmail.requestFocus();
                    }



                    // Após Validação
                    if (dadosValidados) {

                        cadastroUsuario = new CadastroUsuario();

                        cadastroUsuario.setNm_usuario(txtNome.getText().toString());
                        cadastroUsuario.setNr_cpf(txtCPF.getText().toString());
                        cadastroUsuario.setNm_login(txtLogin.getText().toString());
                        cadastroUsuario.setNm_senha(txtSenha.getText().toString());
                        cadastroUsuario.setNm_email(txtEmail.getText().toString());
                        cadastroUsuario.setNm_endereco(txtEnd.getText().toString());
                        cadastroUsuario.setNr_endereco(Integer.parseInt(txtNumero.getText().toString()));
                        cadastroUsuario.setNr_tel(txtTel.getText().toString());


                        //salvarSharedPreferences();

                        if (controller.alterar(cadastroUsuario)){// passando o objeto com Cadastro
                            cadastroUsuario.setId_usuario(MainActivity.cid);
                            //obj salvo com sucesso no BD
                            AlterarAsyncTask task = new AlterarAsyncTask(cadastroUsuario,context);
                            task.execute();

                            UtilCadastro.showMensagem(context,"Dados Atualizados com sucesso");




                        }else{
                            //obj Falha ao salvar no BD
                            UtilCadastro.showMensagem(context,"Falha ao atualizar os Dados");
                        }

                    }

                } catch (Exception e) {

                    UtilCadastro.showMensagem(context, "Não foi possível executar a tarefa...");

                }
            }
        });


        return view;
   }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_CANCELED){
            if(requestCode == PICK_IMAGE){
                Uri selectedImage = data.getData();
                UtilCadastro.showMensagem(context, selectedImage.toString());
                imgUser.setImageURI(selectedImage);
            }
        }
    }

}
