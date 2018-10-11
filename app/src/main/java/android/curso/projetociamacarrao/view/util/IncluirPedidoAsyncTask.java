package android.curso.projetociamacarrao.view.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.controller.PedidosClientesController;
import android.curso.projetociamacarrao.view.dataModel.CadastroUsuarioDataModel;
import android.curso.projetociamacarrao.view.dataModel.PedidosClientesDataModel;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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

public class IncluirPedidoAsyncTask extends AsyncTask<String ,String, String> {


    ProgressDialog progressDialog;
    HttpURLConnection conn;
    URL url= null;
    Uri.Builder builder;

    private PedidosClientesController controller;
    Context context;


    public IncluirPedidoAsyncTask(Pedidos obj, Context context){
        this.builder = new Uri.Builder();

        this.context = context;

        builder.appendQueryParameter("app","ProjetoCiaMacarrao");
        builder.appendQueryParameter(PedidosClientesDataModel.getNm_massa(), obj.getNm_massa());
        builder.appendQueryParameter(PedidosClientesDataModel.getNm_molho(), obj.getNm_molho());
        builder.appendQueryParameter(PedidosClientesDataModel.getNm_adicional(), obj.getNm_adicional());
        builder.appendQueryParameter(PedidosClientesDataModel.getQtd_comida(),String.valueOf(obj.getQtd_comida()));
        builder.appendQueryParameter(PedidosClientesDataModel.getNm_bebida(), obj.getNm_bebida());
        builder.appendQueryParameter(PedidosClientesDataModel.getQtd_bebida(), String.valueOf(obj.getQtd_bebida()));
        builder.appendQueryParameter(PedidosClientesDataModel.getValor_total(), String.valueOf(obj.getValor_total()));
        builder.appendQueryParameter(PedidosClientesDataModel.getDt_pedido(), obj.getDt_pedido());
        builder.appendQueryParameter(PedidosClientesDataModel.getNm_usuario(), obj.getNm_usuario());

    }

    @Override
    protected void onPreExecute() {
        Log.i("WebService", "IncluirPedidoAsyncTask()");

//        progressDialog = new ProgressDialog(context);
//
//        progressDialog.setMessage("Incluindo, por favor aguarde...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            url = new URL(UtilCadastro.URL_WEB_SERVICE + "APIIncluirPedidosUsuario.php");

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
            UtilCadastro.showMensagem(context,"Pedido criado com sucesso!");



        }

    }

}
