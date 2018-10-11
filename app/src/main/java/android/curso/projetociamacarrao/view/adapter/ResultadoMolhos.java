package android.curso.projetociamacarrao.view.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.MainActivity;
import android.curso.projetociamacarrao.view.controller.MolhosController;
import android.curso.projetociamacarrao.view.controller.PedidosClientesController;
import android.curso.projetociamacarrao.view.dataModel.MassasDataModel;
import android.curso.projetociamacarrao.view.dataModel.MolhosDataModel;
import android.curso.projetociamacarrao.view.model.Massas;
import android.curso.projetociamacarrao.view.model.Molhos;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.ArrayList;

// metodo adapter para popular cada item a ser injetado no resultado final, mostrando item a item que vem do BD
public class ResultadoMolhos extends ArrayAdapter<Molhos> implements View.OnClickListener {

    Context context;



    AlertDialog.Builder builder; // cria o texto a ser exibido
    AlertDialog alert; // vai mostrar o que deve ser exibido

    //Objetos globais e coleção de dados (DataSet)
    ArrayList<Molhos> dados;
    Molhos molhos;
    MolhosController controller;
    ViewHolder linha;



    private static class ViewHolder{ // componentes de cada linha da listview
        TextView txtMolho,txtPreco;

        ImageView imgLogo;


    }



    @Override
    public void onClick(View v) {

        int posicao = (Integer) v.getTag();//numero do clique na lista
        Object object = getItem(posicao);//cria o objeto da classe
        molhos = (Molhos) object; //  transforma o objeto para a classe MediaEscolar para usar seus atributos
        controller = new MolhosController(getContext());
        switch (v.getId()){

            case R.id.imgLogo:
                MainActivity.txtValorTotalMo.setText(String.valueOf(UtilCadastro.formatarValorDecimal(molhos.getVl_preco()))+ " > ");

                MainActivity.nomeMolho = molhos.getNm_molho();
                MainActivity.valorTotalMo = molhos.getVl_preco();
                MainActivity.txtValorTotal.setText(String.valueOf(UtilCadastro.formatarValorDecimal(MainActivity.valorTotalMo + MainActivity.valorTotalMa )));


            break;






        }

    }



    @NonNull
    @Override
    public View getView(int position, View dataSet, @NonNull ViewGroup parent){// popular os listview com os dados do DataSet


        molhos = getItem(position);


        if (dataSet == null){

            linha = new ViewHolder();// estanciar um novo item da lista

            LayoutInflater layoutResultadoFinalList =  LayoutInflater.from(getContext());// injetar na lista

            dataSet = layoutResultadoFinalList.inflate(R.layout.listview_molhos, parent, false);

            linha.txtMolho = dataSet.findViewById(R.id.txtMolho);// todos os itens a serem injetados
            linha.txtPreco = dataSet.findViewById(R.id.txtPreco);
            linha.imgLogo = dataSet.findViewById(R.id.imgLogo);


            dataSet.setTag(linha); // insere o obj na tag

        }else{
            linha = (ViewHolder)dataSet.getTag();

        }

        linha.txtMolho.setText( molhos.getNm_molho());
        linha.txtPreco.setText("Valor: " +String.valueOf(UtilCadastro.formatarValorDecimal(molhos.getVl_preco())));
        linha.imgLogo.setOnClickListener(this);
        linha.imgLogo.setTag(position);// para identificar cada logo ao ser clicado
        UtilCadastro.carregarFoto(linha.txtMolho.getText().toString(),linha.imgLogo);




        return dataSet;
    }

    public ResultadoMolhos(ArrayList<Molhos> dataSet, Context context) {
        super(context, R.layout.listview_molhos, dataSet);

        this.dados = dataSet;
        this.context = context;


    }

    //metodo para atualizar a view assim que houver alguma alteração no dataSet
    public  void atualizarLista(ArrayList<Molhos> novosDados){

        this.dados.clear();
        this.dados.addAll(novosDados);

        notifyDataSetChanged();

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer){

        super.registerDataSetObserver(observer);
    }

    //Herdar o ArrayAdapter - Pedidos extends

    //Implementar o OnclickListener - implements





  public static class SincronizarMolhosAsyncTask extends AsyncTask<String, String, String> {


        ProgressDialog progressDialog;
        HttpURLConnection conn;
        URL url= null;
        Uri.Builder builder;

        private MolhosController controller;
        Context context;


        public SincronizarMolhosAsyncTask(Context cont) {

            this.builder = new Uri.Builder();//criar o obj - passar parametros para o WEBS
            builder.appendQueryParameter("app", "CiaMacarrao"); // pode ser um token ou md5 etc - verificar se o app é o que tem que consumir o WEBS
            context = cont;

            //passar o id ou o objeto inteiro
        }

        @Override//preparar o caminho * barra de pre execucao executando ....
        protected void onPreExecute() {

//        progressDialog.setMessage("Selecionando os melhores ingredientes! ");
//        progressDialog.setCancelable(false);
//        progressDialog.show();

        }

        @Override
        //executar em background - consumir os dados do WEBS
        protected String doInBackground(String... strings) {
            // responsavel por montar a URL com o endereço do Script php
            try {
                url = new URL(UtilCadastro.URL_WEB_SERVICE + "APISincronizarMolhos.php");


            } catch (MalformedURLException e) {

                Log.e("WebService", "MalFormedURLException " + e.getMessage());

            } catch (Exception erro) {
                Log.e("WebService", "Erro " + erro.getMessage());

            }

            try {
                conn = (HttpURLConnection) url.openConnection(); // solicitado abertura para conexão
                conn.setConnectTimeout(UtilCadastro.CONNECTION_TIMEOUT);
                conn.setReadTimeout(UtilCadastro.READ_TIMEOUT);
                // qual metodo post ou guest
                conn.setRequestMethod("POST"); // encapsulada
                conn.setRequestProperty("charset", "utf-8");// linguagem
                conn.setDoInput(true); // retorno de info
                conn.setDoOutput(true); // passar os parametros


//                conn.connect(); // conexão

                String query = builder.build().getEncodedQuery();// passando o parametro App Media Escolar do append
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); // modelo de envio dos dados
                writer.write(query);// executar a query criada
                writer.flush();// para descarregar as infos
                writer.close();
                os.close();

                conn.connect(); // conexão


            } catch (IOException e) {

                Log.e("WebService", "IOException " + e.getMessage());


            }

            try {
                int response_code = conn.getResponseCode();// retorno da pagina 200 ok, erro 404, 500 e 403
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();

                    String line; // para cada item receber em uma linha
                    while ((line = reader.readLine()) != null) {//enquanto existir leitura na linha do BD
                        result.append(line);//para pegar cada item do BD resultSet

                    }
                    return (result.toString());
                } else {
                    return "Erro de Conexão";

                }

            } catch (IOException e) {

                return e.toString();

            } finally {
                conn.disconnect();
            }

        }

        @Override//tratar as informações que receber e colocar no BD SQLITE = JSON
        protected void onPostExecute(String result) {

            try {
                JSONArray jArray = new JSONArray(result); // convert a result string para JSON

//            JSONArray jArray = new JSONArray("[{\"id_usuario\":1,\"nm_usuario\":\"danilo felix\",\"nr_cpf\":\"12312312312\",\"nm_login\":\"felixmda\",\"nm_senha\":\"123\",\"nm_email\":\"danilo_fmore@hotmail.com\",\"nm_endereco\":\"rua cuscuz\",\"nr_endereco\":123,\"nr_tel\":\"12312312\"}]");
                if (jArray.length() != 0) {//se contem alguma coisa
                    controller = new MolhosController(context);
                    controller.deletarTabela(MolhosDataModel.getTABELA());// sempre que pegar a tabela usar dataModel -
                    controller.criarTabela(MolhosDataModel.criarTabela());

                    for (int i = 0; i < jArray.length(); i++) { // para cada linha da Tabela, gerar um objeto JSON
                        JSONObject jsonObject = jArray.getJSONObject(i);// contem todos os dados
                        Molhos obj = new Molhos();

                        // pegar todos itens do OBJ que está no BD. - Tabela do MySql que alimenta a SQLite
                        obj.setId_molho(jsonObject.getInt(MolhosDataModel.getId_molho()));
                        obj.setNm_molho(jsonObject.getString(MolhosDataModel.getNm_molho()));
                        obj.setVl_preco(jsonObject.getDouble(MolhosDataModel.getVl_preco()));
                        obj.setQtd_molho(jsonObject.getInt(MolhosDataModel.getQtd_molho()));

                        controller.salvar(obj);
                        //todo - id do sql e lite

                    }

                } else {
                    UtilCadastro.showMensagem(context, "Nenhum registro encontrado.");
                }

            } catch (JSONException e) {

                Log.e("WebService", "Erro JSONException - " + e.getMessage());

            } finally { // com erro ou não executar um fechamento de processo

                if (progressDialog != null && progressDialog.isShowing()) {//verificar se o progressDialog está nulo ou em andamento

                    progressDialog.dismiss();
//                    UtilCadastro.showMensagem(context, "Monte seu prato selecionando os ingredientes" );


                }
            }


        }
    }




}
