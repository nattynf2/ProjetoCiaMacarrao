package android.curso.projetociamacarrao.view.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.dataModel.CadastroUsuarioDataModel;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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

public class EnviarEmailAsyncTask extends AsyncTask<String ,String, String> {


    ProgressDialog progressDialog;
    HttpURLConnection conn;
    URL url= null;
    Uri.Builder builder;

    private CadastroUsuarioController controller;
    Context context;


    public EnviarEmailAsyncTask(TextView nome,TextView email,TextView mensagem, Context context){
        this.builder = new Uri.Builder();

        this.context = context;

        builder.appendQueryParameter("app","ProjetoCiaMacarrao");
//        builder.appendQueryParameter(CadastroUsuarioDataModel.getId_usuario(), String.valueOf(obj.getId_usuario()));
        builder.appendQueryParameter("nome", nome.getText().toString());
        builder.appendQueryParameter("email", email.getText().toString());
        builder.appendQueryParameter("mensagem", mensagem.getText().toString());

    }

    @Override
    protected void onPreExecute() {
        Log.i("WebService", "EnviarEmailAsyncTask()");

        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Enviando email, por favor aguarde...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            url = new URL(UtilCadastro.URL_WEB_SERVICE + "APIEnviarEmail.php");

        } catch (MalformedURLException e) {

            Log.e("WebService", "MalformedURLException - " + e.getMessage());

        } catch (Exception e) {

            Log.e("WebService", "Exception - " + e.getMessage());

        }

        try {

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(UtilCadastro.READ_TIMEOUT);
            conn.setConnectTimeout(UtilCadastro.CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("charset", "UTF-8");

            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.connect();

            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
        } catch (IOException erro) {


            Log.e("WebService", "IOException - " + erro.getMessage());

        }

        try {

            int response_code = conn.getResponseCode();

            if (response_code == HttpURLConnection.HTTP_OK) {

                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                return (result.toString());

            } else {
                return ("Erro de conexão");
            }

        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            conn.disconnect();
        }

    }

    @Override
    protected void onPostExecute(String result) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
//            UtilCadastro.showMensagem(context,"Adicionado com sucesso!");



        }

    }

}
