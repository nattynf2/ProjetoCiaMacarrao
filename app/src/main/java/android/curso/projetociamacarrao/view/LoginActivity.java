package android.curso.projetociamacarrao.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.dataModel.CadastroUsuarioDataModel;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity  {

    private ProgressDialog load;
    Button btnEntrar,btnCadastrar;
    TextView txtEsqueceuSenha;
    EditText txtLogin, txtSenha;
    Context context;
    private ProgressDialog pd = null;
    String validaEmail ="" , validaSenha="",validaUsuario="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        context = getBaseContext();


        String GetLogin = getIntent().getStringExtra("GetLogin");

        btnEntrar = findViewById(R.id.btnEntrar);
        txtEsqueceuSenha = findViewById(R.id.txtEsqueceuSenha);
        txtLogin = findViewById(R.id.txtLogin);
        txtSenha = findViewById(R.id.txtPass);
        txtLogin.setText(GetLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            Intent ic = new Intent(LoginActivity.this,CadastroDeUsuario.class);
            startActivity(ic);

            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                if (txtLogin.getText().toString().isEmpty()) {
                    UtilCadastro.showMensagem(context, "Favor verificar o Login digitado...");
                    txtLogin.setError("*");
                    txtLogin.requestFocus();


                } else if (txtSenha.getText().toString().isEmpty()) {
                    UtilCadastro.showMensagem(context, "Favor verificar a Senha digitada...");
                    txtSenha.setError("*");
                    txtSenha.requestFocus();

                } else {
                    // todo - verificar o login
                    CadastroUsuarioController controller = new CadastroUsuarioController(context);
                    CadastroUsuario obj = new CadastroUsuario();
                    obj.setNm_login(txtLogin.getText().toString());
                    obj.setNm_senha(txtSenha.getText().toString());

                    ValidarLogin task = new ValidarLogin(obj, LoginActivity.this);
                    task.execute();//rodar em background

                    if(!controller.validarLogin(txtLogin.getText().toString(),txtSenha.getText().toString(),obj)){
                        UtilCadastro.showMensagem(context,"Login Inválido...");
                    }
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class ValidarLogin extends AsyncTask<String, String, String> {


        HttpURLConnection conn; // retornar o tipo de conexao 200 conect 404 error page
        URL url = null;
        Uri.Builder builder;



        public  ValidarLogin(CadastroUsuario obj, Context context){// passar um objeto com email e senha


            ProgressDialog pd = new ProgressDialog(context);

            this.builder = new Uri.Builder();//criar o obj - passar parametros para o WEBS
            builder.appendQueryParameter("app","ProjetoCiaMacarrao"); // pode ser um token ou md5 etc - verificar se o app é o que tem que consumir o WEBS

            builder.appendQueryParameter(CadastroUsuarioDataModel.getNm_senha(),obj.getNm_senha());
            builder.appendQueryParameter(CadastroUsuarioDataModel.getNm_login(), obj.getNm_login());
            //passar o id ou o objeto inteiro
        }
        @Override//preparar o caminho * barra de pre execucao executando ....
        protected void onPreExecute(){



        }
        @Override
        //executar em background - consumir os dados do WEBS
        protected String doInBackground(String... strings) {
            // responsavel por montar a URL com o endereço do Script php
            try{
                url =  new URL(UtilCadastro.URL_WEB_SERVICE +"APIValidaLogin.php");


            }catch (MalformedURLException e){

                Log.e("WebService", "MalFormedURLException " + e.getMessage());

            }catch (Exception erro){
                Log.e("WebService", "Erro " + erro.getMessage());

            }

            try{
                conn = (HttpURLConnection) url.openConnection(); // solicitado abertura para conexão
                conn.setConnectTimeout(UtilCadastro.CONNECTION_TIMEOUT);
                conn.setReadTimeout(UtilCadastro.READ_TIMEOUT);
                // qual metodo post ou guest
                conn.setRequestMethod("POST"); // encapsulada
                conn.setRequestProperty("charset","utf-8");// linguagem
                conn.setDoInput(true); // retorno de info
                conn.setDoOutput(true); // passar os parametros


//                conn.connect(); // conexão

                String query = builder.build().getEncodedQuery();// passando o parametro App Media Escolar do append
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8")); // modelo de envio dos dados
                writer.write(query);// executar a query criada
                writer.flush();// para descarregar as infos
                writer.close();
                os.close();

                conn.connect(); // conexão


            }catch (IOException e){

                Log.e("WebService", "IOException " + e.getMessage());


            }

            try{
                int response_code = conn.getResponseCode();// retorno da pagina 200 ok, erro 404, 500 e 403
                if (response_code == HttpURLConnection.HTTP_OK){
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();

                    String line; // para cada item receber em uma linha
                    while ((line = reader.readLine())!= null){//enquanto existir leitura na linha do BD
                        result.append(line);//para pegar cada item do BD resultSet

                    }
                    return (result.toString());
                }else{
                    return "Erro de Conexão";

                }

            }catch (IOException e){

                return e.toString();

            }finally {
                conn.disconnect();
            }

        }

        @Override//tratar as informações que receber e colocar no BD SQLITE = JSON
        protected void onPostExecute(String result){

            try{

                //JSONObject Jasonobject = new JSONObject(result);
                //JSONArray jArray = Jasonobject.getJSONArray(String.valueOf(Jasonobject));
                if (result.equals("{"+"\"sucesso\""+":false}")){
                    UtilCadastro.showMensagem(context,"Favor verificar o Login...");
                }else{
                JSONArray jArray = new JSONArray(result); // convert a result string para JSON
                if (jArray.length()!=0) {//se contem alguma coisa

//                    controller.deletarTabela(MediaEscolarDataModel.getTABELA());// sempre que pegar a tabela usar dataModel -
//                    controller.criarTabela(MediaEscolarDataModel.criarTabela());

                    for (int i = 0; i < jArray.length(); i++) { // para cada linha da Tabela, gerar um objeto JSON
                        JSONObject jsonObject = jArray.getJSONObject(i);// contem todos os dados
                        CadastroUsuario obj = new CadastroUsuario();

                        // pegar todos itens do OBJ que está no BD. - Tabela do MySql que alimenta a SQLite
                        obj.setId_usuario(jsonObject.getInt(CadastroUsuarioDataModel.getId_usuario()));
                        obj.setNm_email(jsonObject.getString(CadastroUsuarioDataModel.getNm_email()));
                        obj.setNm_usuario(jsonObject.getString(CadastroUsuarioDataModel.getNm_usuario()));
                        obj.setNm_senha(jsonObject.getString(CadastroUsuarioDataModel.getNm_senha()));
                        obj.setNm_login(jsonObject.getString(CadastroUsuarioDataModel.getNm_login()));
                        obj.setNr_tel(jsonObject.getString(CadastroUsuarioDataModel.getNr_tel()));
                        obj.setNm_endereco(jsonObject.getString(CadastroUsuarioDataModel.getNm_endereco()));
                        obj.setNr_endereco(jsonObject.getInt(CadastroUsuarioDataModel.getNr_endereco()));
                        obj.setNr_cpf(jsonObject.getString(CadastroUsuarioDataModel.getNr_cpf()));


//
                        //todo - id do sql e lite

                        CadastroUsuarioController controller = new CadastroUsuarioController(context);
                        if (controller.validarLogin(txtLogin.getText().toString(), txtSenha.getText().toString(), obj)) {


                            Intent ih = new Intent(LoginActivity.this, MainActivity.class);
                            ih.putExtra("GetId", String.valueOf(obj.getId_usuario()));
                            ih.putExtra("GetNome", obj.getNm_usuario());
                            ih.putExtra("GetEmail", obj.getNm_email());
                            ih.putExtra("GetLogin", obj.getNm_login());
                            ih.putExtra("GetTel", obj.getNr_tel());
                            ih.putExtra("GetEnd", obj.getNm_endereco());
                            ih.putExtra("GetNrEnd", String.valueOf(obj.getNr_endereco()));
                            ih.putExtra("GetCPF", obj.getNr_cpf());


                            startActivity(ih);
                            txtSenha.setText("");

//                            finish();
                        } else {


                            finish();

                        }


                    }
                }
                }



            }catch (JSONException e){

                Log.e("WebService", "Erro JSONException - " + e.getMessage());

            }finally { // com erro ou não executar um fechamento de processo

                if (pd != null && pd.isShowing()){//verificar se o progressDialog está nulo ou em andamento

                    pd.dismiss();


                }
            }


        }
    }




}
