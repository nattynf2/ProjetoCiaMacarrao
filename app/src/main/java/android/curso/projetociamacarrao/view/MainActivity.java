package android.curso.projetociamacarrao.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.adapter.ResultadoAdd;
import android.curso.projetociamacarrao.view.adapter.ResultadoBebidas;
import android.curso.projetociamacarrao.view.adapter.ResultadoMolhos;
import android.curso.projetociamacarrao.view.controller.AddController;
import android.curso.projetociamacarrao.view.controller.BebidasController;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.controller.MassasController;
import android.curso.projetociamacarrao.view.controller.MolhosController;
import android.curso.projetociamacarrao.view.controller.PedidosClientesController;
import android.curso.projetociamacarrao.view.dataModel.CadastroUsuarioDataModel;
import android.curso.projetociamacarrao.view.dataModel.MassasDataModel;
import android.curso.projetociamacarrao.view.dataModel.PedidosClientesDataModel;
import android.curso.projetociamacarrao.view.fragments.CadastroUsuarios;
import android.curso.projetociamacarrao.view.fragments.Compartilhar;
import android.curso.projetociamacarrao.view.fragments.Contato;
import android.curso.projetociamacarrao.view.fragments.Home;
import android.curso.projetociamacarrao.view.fragments.MeusAdds;
import android.curso.projetociamacarrao.view.fragments.MeusDados;
import android.curso.projetociamacarrao.view.fragments.MeusMolhos;
import android.curso.projetociamacarrao.view.fragments.MeusPedidos;
import android.curso.projetociamacarrao.view.fragments.MinhasBebidas;
import android.curso.projetociamacarrao.view.fragments.MinhasMassas;
import android.curso.projetociamacarrao.view.fragments.Sobre;
import android.curso.projetociamacarrao.view.model.Add;
import android.curso.projetociamacarrao.view.model.Bebidas;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.model.Massas;
import android.curso.projetociamacarrao.view.model.Molhos;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.curso.projetociamacarrao.view.util.EnviarEmailAsyncTask;
import android.curso.projetociamacarrao.view.util.IncluirPedidoAsyncTask;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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

import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int cid;
    public static double valorTotalMa;
    public static double valorTotalMo;
    public static Button btFinalizar;
    public static double valorTotalAdd;

    public static String cnome, cemail, clogin, cend, cnrend, ctel, ccpf;
    public static TextView txtValorTotalMa = null;
    public static TextView txtValorTotalMo = null;
    public static TextView txtValorTotalAdd = null;
    public static TextView txtValorTotal = null;

    public static String nomeMassa, nomeMolho, nomeAdd, nomeBebida;
    public static int qtdComida, qtdBebida;




    android.support.v4.app.FragmentManager fragmentManager;
    Context context;
    MeusMolhos meusMolhos = new MeusMolhos();
    Compartilhar shareApp = new Compartilhar();
    Contato contato= new Contato();
    MinhasMassas minhasMassas = new MinhasMassas();
    Sobre nossoSobre = new Sobre();
    Home meuHome = new Home();
    MeusAdds meusAdds = new MeusAdds();
    MeusDados meusDados = new MeusDados();
    MeusPedidos meusPedidos = new MeusPedidos();
    MinhasBebidas minhasBebidas = new MinhasBebidas();
    CadastroUsuarios cadastroUsuario = new CadastroUsuarios();
    Pedidos pedidos;
    Molhos molhos;
    Bebidas bebidas;
    MolhosController controller2;
    BebidasController controller5;
    AddController controller4;
    PedidosClientesController controller;
    Massas massas;
    Add add;
    MassasController controller3;
    CadastroUsuarioController controller1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = getBaseContext();
        controller = new PedidosClientesController(context);
        controller1 = new CadastroUsuarioController(context);
        controller2 = new MolhosController(context);
        controller3 = new MassasController(context);
        controller4 = new AddController(context);
        controller5 = new BebidasController(context);


        txtValorTotalMa = findViewById(R.id.txtValorTotalMa);
        txtValorTotalMo = findViewById(R.id.txtValorTotalMo);
        txtValorTotalAdd = findViewById(R.id.txtValorTotalAdd);
        txtValorTotal = findViewById(R.id.txtValorTotal);
        btFinalizar = findViewById(R.id.btFinalizar);

        final FloatingActionButton cab2 = (FloatingActionButton) findViewById(R.id.fab2);
        cab2.setImageResource(R.mipmap.ic_seta_e_round);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.mipmap.ic_plus_mais);





        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(final View view) {
                if (meusPedidos.isVisible()) {
                    SincronizarMassasAsyncTask task = new SincronizarMassasAsyncTask();
                    task.execute();
                    ResultadoMolhos.SincronizarMolhosAsyncTask task2 = new ResultadoMolhos.SincronizarMolhosAsyncTask(context);
                    task2.execute();
                    ResultadoAdd.SincronizarAddsAsyncTask task3 = new ResultadoAdd.SincronizarAddsAsyncTask(context);
                    task3.execute();
                    ResultadoBebidas.SincronizarAddsAsyncTask task4 = new ResultadoBebidas.SincronizarAddsAsyncTask(context);
                    task4.execute();
                    setTitle("Escolha sua massa");
                    cab2.setVisibility(view.VISIBLE);
                    txtValorTotalMa.setVisibility(INVISIBLE);
                    txtValorTotalMo.setVisibility(INVISIBLE);
                    txtValorTotalAdd.setVisibility(INVISIBLE);
                    txtValorTotal.setVisibility(view.VISIBLE);
                    txtValorTotal.setText("");
                    txtValorTotalMa.setText("");
                    txtValorTotalMo.setText("");
                    txtValorTotalAdd.setText("");

                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, minhasMassas, "Massas").commit();// para inserir o fragmento
                    fab.setImageResource(R.mipmap.ic_seta_d_round);

                } else if (minhasMassas.isVisible()) {


                    if (txtValorTotalMa.getText().equals("")) {
                        UtilCadastro.showMensagem(context, "Escolha uma Massa");
                    } else {
                        setTitle("Escolha seu molho");

                        fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, meusMolhos, "Molhos").commit();// para inserir o fragmento

                    }

                } else if (meusMolhos.isVisible()) {
                    if (txtValorTotalMo.getText().equals("")) {
                        UtilCadastro.showMensagem(context, "Escolha um Molho");
                    } else {
                        setTitle("Escolha seu Adicional");
                        fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, meusAdds, "Adds").commit();// para inserir o fragmento
                    }
                } else if (meusAdds.isVisible()) {
                    btFinalizar.setVisibility(View.VISIBLE);
                    setTitle("Escolha sua Bebida");
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, minhasBebidas, "Bebidas").commit();// para inserir o fragmento

                }

                cab2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (minhasBebidas.isVisible()) {
                            setTitle("Escolha seu Adicional");
                            fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, meusAdds, "Adds").commit();// para inserir o fragmento
//                            txtValorTotalBe.setText(0);
                            txtValorTotal.setText(String.valueOf(UtilCadastro.formatarValorDecimal(valorTotalMa + valorTotalMo + valorTotalAdd)));
                            cab2.setVisibility(v.VISIBLE);


                        } else if (meusAdds.isVisible()) {


                            setTitle("Escolha seu molho");
                            fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, meusMolhos, "Molhos").commit();// para inserir o fragmento
                            txtValorTotalAdd.setText("");
                            txtValorTotal.setText(String.valueOf(UtilCadastro.formatarValorDecimal(valorTotalMa + valorTotalMo)));
                            cab2.setVisibility(v.VISIBLE);
                            btFinalizar.setVisibility(INVISIBLE);

                        } else if (meusMolhos.isVisible()) {
                            txtValorTotalMo.setText("");
                            setTitle("Escolha sua massa");
                            fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, minhasMassas, "Massas").commit();// para inserir o fragmento
                            txtValorTotal.setText(String.valueOf(UtilCadastro.formatarValorDecimal(valorTotalMa)));

                        } else if (minhasMassas.isVisible()) {
                            cab2.setVisibility(INVISIBLE);
                            txtValorTotalMa.setVisibility(INVISIBLE);
                            txtValorTotalMo.setVisibility(INVISIBLE);
                            txtValorTotalAdd.setVisibility(INVISIBLE);
                            txtValorTotal.setText("");
                            txtValorTotal.setVisibility(INVISIBLE);
                            setTitle("Meus pedidos");
                            fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.content_fragment, meusPedidos, "Pedidos").commit();// para inserir o fragmento

                            fab.setImageResource(R.mipmap.ic_plus_mais);

                        }
                    }
                });


            }
        });

        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                if (txtValorTotalMa.getText().toString().equals("") || txtValorTotalMo.getText().toString().equals("")) {
                    UtilCadastro.showMensagem(context, "Favor selecionar o seu pedido!");
                }

                View finalizaView = v.inflate(context, R.layout.alert_dialog_finaliza_listview, null);

                final TextView txtTotal = finalizaView.findViewById(R.id.txtTotal);
                AlertDialog.Builder alertbox = new AlertDialog.Builder(finalizaView.getRootView().getContext());


                alertbox.setTitle("Pagamento");

                alertbox.setCancelable(true);// se tem a opção de cancelar
                alertbox.setIcon(R.mipmap.prato_macarrao);
                alertbox.setView(finalizaView);

                txtTotal.setText("Total do pedido: " + txtValorTotal.getText().toString());

                alertbox.setNeutralButton("Finalizar",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {

                                final FloatingActionButton cab2 = (FloatingActionButton) findViewById(R.id.fab2);

                                Pedidos obj = new Pedidos();

                                if (nomeBebida ==(null)){
                                    nomeBebida = " - ";}
                                if (nomeAdd ==(null)){
                                    nomeAdd = " - ";}
                                controller = new PedidosClientesController(context);
                                obj.setNm_massa(nomeMassa);
                                obj.setNm_molho(nomeMolho);
                                obj.setNm_adicional(nomeAdd);
                                obj.setQtd_comida(1);
                                obj.setNm_bebida(nomeBebida);
                                obj.setQtd_bebida(qtdBebida);
                                obj.setValor_total(UtilCadastro.formatarValorString(MainActivity.txtValorTotal.getText().toString()));
                                obj.setDt_pedido(UtilCadastro.pegarData());
                                obj.setNm_usuario(cnome);

                                controller.salvar(obj);

                                IncluirPedidoAsyncTask task = new IncluirPedidoAsyncTask(obj, context);
                                task.execute();//rodar em background


                                setTitle("Cia do Macarrão");
//                                fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.content_fragment, meuHome).commit();
                                btFinalizar.setVisibility(INVISIBLE);
                                fab.setVisibility(View.INVISIBLE);
                                fab.setImageResource(R.mipmap.ic_plus_round);
                                cab2.setVisibility(INVISIBLE);
                                txtValorTotalMa.setVisibility(INVISIBLE);
                                txtValorTotalMo.setVisibility(INVISIBLE);
                                txtValorTotalAdd.setVisibility(INVISIBLE);

                                txtValorTotal.setText("");
                                txtValorTotalMa.setText("");
                                txtValorTotalMo.setText("");
                                txtValorTotalAdd.setText("");
                                UtilCadastro.showMensagem(context, "Pedido efetuado com sucesso. \n Favor verificar em seus Pedidos!");

                            }
                        });

                alertbox.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0,
                                                int arg1) {
                                arg0.cancel();
                            }
                        });


                alertbox.show();

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Menu menu = navigationView.getMenu();

        MenuItem cadastro = menu.findItem(R.id.nav_usuarios);

//todo - para passar o nome para as id de email e nome na tela drawer
        View hView = navigationView.getHeaderView(0);
        TextView nav_user = (TextView) hView.findViewById(R.id.txtEmailL);
        TextView nav_email = hView.findViewById(R.id.txtUser);
        String GetEmail = getIntent().getStringExtra("GetEmail");
        String GetUser = getIntent().getStringExtra("GetNome");
        String GetLogin = getIntent().getStringExtra("GetLogin");
        String GetTel = getIntent().getStringExtra("GetTel");
        String GetEnd = getIntent().getStringExtra("GetEnd");
        String GetNrEnd = getIntent().getStringExtra("GetNrEnd");
        String GetId = (getIntent().getStringExtra("GetId"));
        String GetCPF = getIntent().getStringExtra("GetCPF");
        cid = Integer.parseInt(GetId);
        cnome = GetUser;
        cemail = GetEmail;
        clogin = GetLogin;
        cend = GetEnd;
        cnrend = GetNrEnd;
        ctel = GetTel;
        ccpf = GetCPF;
        nav_user.setText("Bem vindo - " + GetUser.toUpperCase());
        nav_email.setText(GetEmail);
        FloatingActionButton fabView = (FloatingActionButton) findViewById(R.id.fab);
        txtValorTotalMa = findViewById(R.id.txtValorTotalMa);
        txtValorTotalMo = findViewById(R.id.txtValorTotalMo);
        txtValorTotalAdd = findViewById(R.id.txtValorTotalAdd);
//todo - Ocultar algum menu do drawer
        if (clogin.equals("admin")) {
            cadastro.setVisible(true);
            SincronizarUsuarioAsyncTask task = new SincronizarUsuarioAsyncTask();
            task.execute();//rodar em background
        }
        SincronizarPedidosAsyncTask task1 = new SincronizarPedidosAsyncTask();
        task1.execute();

//        setTitle("Meus Pedidos");
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_fragment, meuHome).commit();// para inserir o fragmento


        txtValorTotalMa.setText("");
        txtValorTotalMo.setText("");
        txtValorTotalAdd.setText("");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Logout) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();
        FloatingActionButton fabView = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton cabView = (FloatingActionButton) findViewById(R.id.fab2);
        if (id == R.id.nav_meus_dados) {

            setTitle("Meus Dados");
            fragmentManager.beginTransaction().replace(R.id.content_fragment, meusDados).commit();
            fabView.setVisibility(INVISIBLE);
            cabView.setVisibility(INVISIBLE);
            txtValorTotalMa.setText("");
            txtValorTotalMo.setText("");
            txtValorTotalAdd.setText("");
            txtValorTotal.setText("");

        } else if (id == R.id.nav_pedidos) {

            setTitle("Meus Pedidos");
            fragmentManager.beginTransaction().replace(R.id.content_fragment, meusPedidos).commit();
            fabView.setImageResource(R.mipmap.ic_plus_round);
            fabView.setVisibility(View.VISIBLE);
            cabView.setVisibility(INVISIBLE);
            txtValorTotal.setText("");
            txtValorTotalMa.setText("");
            txtValorTotalMo.setText("");
            txtValorTotalAdd.setText("");

        } else if (id == R.id.nav_usuarios) {

            setTitle("Usuarios");


            fragmentManager.beginTransaction().replace(R.id.content_fragment, cadastroUsuario).commit();
            fabView.setVisibility(INVISIBLE);
            cabView.setVisibility(INVISIBLE);
            txtValorTotalMa.setText("");
            txtValorTotal.setText("");
            txtValorTotalMo.setText("");
            txtValorTotalAdd.setText("");


        } else if (id == R.id.nav_compartilhar) {
            setTitle("Compartilhe!");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, shareApp).commit();

            txtValorTotal.setText("");
        } else if (id == R.id.nav_sobre) {
            txtValorTotal.setText("");
            setTitle("Sobre Cia Macarrão");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, nossoSobre).commit();

        } else if (id == R.id.nav_mensagem) {
            txtValorTotal.setText("");

            setTitle("Contato");

            fragmentManager.beginTransaction().replace(R.id.content_fragment, contato).commit();





        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class SincronizarUsuarioAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn; // retornar o tipo de conexao 200 conect 404 error page
        URL url = null;
        Uri.Builder builder;


        public SincronizarUsuarioAsyncTask() {

            this.builder = new Uri.Builder();//criar o obj - passar parametros para o WEBS
            builder.appendQueryParameter("app", "CiaMacarrao"); // pode ser um token ou md5 etc - verificar se o app é o que tem que consumir o WEBS

            //passar o id ou o objeto inteiro
        }

        @Override//preparar o caminho * barra de pre execucao executando ....
        protected void onPreExecute() {

            progressDialog.setMessage("Sincronizando sistema, Aguarde...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        //executar em background - consumir os dados do WEBS
        protected String doInBackground(String... strings) {
            // responsavel por montar a URL com o endereço do Script php
            try {
                url = new URL(UtilCadastro.URL_WEB_SERVICE + "APISincronizarSistemaUsuario.php");


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

                    controller1.deletarTabela(CadastroUsuarioDataModel.getTABELA());// sempre que pegar a tabela usar dataModel -
                    controller1.criarTabela(CadastroUsuarioDataModel.criarTabela());

                    for (int i = 0; i < jArray.length(); i++) { // para cada linha da Tabela, gerar um objeto JSON
                        JSONObject jsonObject = jArray.getJSONObject(i);// contem todos os dados
                        CadastroUsuario obj = new CadastroUsuario();

                        // pegar todos itens do OBJ que está no BD. - Tabela do MySql que alimenta a SQLite
                        obj.setId_usuario(jsonObject.getInt(CadastroUsuarioDataModel.getId_usuario()));
                        obj.setNm_usuario(jsonObject.getString(CadastroUsuarioDataModel.getNm_usuario()));
                        obj.setNr_cpf(jsonObject.getString(CadastroUsuarioDataModel.getNr_cpf()));
                        obj.setNm_login(jsonObject.getString(CadastroUsuarioDataModel.getNm_login()));
                        obj.setNm_email(jsonObject.getString(CadastroUsuarioDataModel.getNm_email()));
                        obj.setNm_endereco(jsonObject.getString(CadastroUsuarioDataModel.getNm_endereco()));
                        obj.setNr_endereco(jsonObject.getInt(CadastroUsuarioDataModel.getNr_endereco()));
                        obj.setNr_tel(jsonObject.getString(CadastroUsuarioDataModel.getNr_tel()));

                        controller1.salvar(obj);
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


                }
            }


        }
    }

    private class SincronizarPedidosAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn; // retornar o tipo de conexao 200 conect 404 error page
        URL url = null;
        Uri.Builder builder;


        public SincronizarPedidosAsyncTask() {

            this.builder = new Uri.Builder();//criar o obj - passar parametros para o WEBS
            builder.appendQueryParameter("app", "CiaMacarrao"); // pode ser um token ou md5 etc - verificar se o app é o que tem que consumir o WEBS
            builder.appendQueryParameter(PedidosClientesDataModel.getNm_usuario(), cnome);
            //passar o id ou o objeto inteiro
        }

        @Override//preparar o caminho * barra de pre execucao executando ....
        protected void onPreExecute() {

            progressDialog.setMessage("Sincronizando sistema, Aguarde...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        //executar em background - consumir os dados do WEBS
        protected String doInBackground(String... strings) {
            // responsavel por montar a URL com o endereço do Script php
            try {
                url = new URL(UtilCadastro.URL_WEB_SERVICE + "APISincronizarSistemaPedido.php");


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


                    controller.deletarTabela(PedidosClientesDataModel.getTABELA());// sempre que pegar a tabela usar dataModel -
                    controller.criarTabela(PedidosClientesDataModel.criarTabela());

                    for (int i = 0; i < jArray.length(); i++) { // para cada linha da Tabela, gerar um objeto JSON
                        JSONObject jsonObject = jArray.getJSONObject(i);// contem todos os dados
                        Pedidos obj = new Pedidos();

                        // pegar todos itens do OBJ que está no BD. - Tabela do MySql que alimenta a SQLite
                        obj.setId_pedido(jsonObject.getInt(PedidosClientesDataModel.getId_pedido()));
                        obj.setNm_massa(jsonObject.getString(PedidosClientesDataModel.getNm_massa()));
                        obj.setNm_molho(jsonObject.getString(PedidosClientesDataModel.getNm_molho()));
                        obj.setNm_adicional(jsonObject.getString(PedidosClientesDataModel.getNm_adicional()));
                        obj.setQtd_comida(jsonObject.getInt(PedidosClientesDataModel.getQtd_comida()));
                        obj.setNm_bebida(jsonObject.getString(PedidosClientesDataModel.getNm_bebida()));
                        obj.setQtd_bebida(jsonObject.getInt(PedidosClientesDataModel.getQtd_bebida()));
                        obj.setValor_total(jsonObject.getDouble(PedidosClientesDataModel.getValor_total()));
                        obj.setDt_pedido(jsonObject.getString(PedidosClientesDataModel.getDt_pedido()));
                        obj.setNm_usuario(jsonObject.getString(PedidosClientesDataModel.getNm_usuario()));

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


                }
            }


        }
    }


    private class SincronizarMassasAsyncTask extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn; // retornar o tipo de conexao 200 conect 404 error page
        URL url = null;
        Uri.Builder builder;


        public SincronizarMassasAsyncTask() {

            this.builder = new Uri.Builder();//criar o obj - passar parametros para o WEBS
            builder.appendQueryParameter("app", "CiaMacarrao"); // pode ser um token ou md5 etc - verificar se o app é o que tem que consumir o WEBS

            //passar o id ou o objeto inteiro
        }

        @Override//preparar o caminho * barra de pre execucao executando ....
        protected void onPreExecute() {

            progressDialog.setMessage("Selecionando os melhores ingredientes! ");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        //executar em background - consumir os dados do WEBS
        protected String doInBackground(String... strings) {
            // responsavel por montar a URL com o endereço do Script php
            try {
                url = new URL(UtilCadastro.URL_WEB_SERVICE + "APISincronizarMassas.php");


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

                    controller3.deletarTabela(MassasDataModel.getTABELA());// sempre que pegar a tabela usar dataModel -
                    controller3.criarTabela(MassasDataModel.criarTabela());

                    for (int i = 0; i < jArray.length(); i++) { // para cada linha da Tabela, gerar um objeto JSON
                        JSONObject jsonObject = jArray.getJSONObject(i);// contem todos os dados
                        Massas obj = new Massas();

                        // pegar todos itens do OBJ que está no BD. - Tabela do MySql que alimenta a SQLite
                        obj.setId_massa(jsonObject.getInt(MassasDataModel.getId_massa()));
                        obj.setNm_massa(jsonObject.getString(MassasDataModel.getNm_massa()));
                        obj.setVl_preco(jsonObject.getDouble(MassasDataModel.getVl_preco()));
                        obj.setQtd_massa(jsonObject.getInt(MassasDataModel.getQtd_massa()));

                        controller3.salvar(obj);
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

//  pedidos = new Pedidos();
////                 controller = new PedidosClientesController(context);
////                 pedidos.setNm_massa("Spaghetti");
////                 pedidos.setNm_molho("Sugo");
////                 pedidos.setNm_adicional("Bacon");
////                 pedidos.setQtd_comida(2);
////                 pedidos.setNm_bebida("Coca-cola");
////                 pedidos.setQtd_bebida(2);
////                 pedidos.setValor_total(16.45);
////                 pedidos.setDt_pedido("14/05/2018");
////                 pedidos.setNm_usuario("ilo");
////
////                 controller.salvar(pedidos);


//
//                bebidas = new Bebidas();
//                controller5 = new BebidasController(context);
//                bebidas.setNm_bebida("Cerveja");
//                bebidas.setVl_preco(6.25);
//                bebidas.setQtd_bebida(1);
//                controller5.salvar(bebidas);

//                molhos = new Molhos();
//                 controller2 = new MolhosController(context);
//                 molhos.setNm_molho("Sugo");
//                 molhos.setVl_preco(12.35);
//                 molhos.setQtd_molho(10);
//                   controller2.salvar(molhos);
//
//                   add = new Add();
//                   controller4 = new AddController(context);
//                   add.setNm_adicional("Bacon");
//                   add.setVl_preco(4.80);
//                   add.setQtd_adicional(10);
//                   controller4.salvar(add);


//                 pedidos = new Pedidos();
//                 controller = new PedidosClientesController(context);
//
//                 pedidos.setNm_massa("Penne");
//                 pedidos.setNm_molho("Bolonhesa");
//                 pedidos.setNm_adicional("Parmesão");
//                 pedidos.setQtd_comida(2);
//                 pedidos.setNm_bebida("Coca-cola");
//                 pedidos.setQtd_bebida(3);
//                 pedidos.setValor_total(21.8);
//                 pedidos.setDt_pedido("24/04/2018");
//                 pedidos.setNm_usuario("Danilo Felix");
//
//                  molhos = new Molhos();
////                 controller2 = new MolhosController(context);
//                 molhos.setNm_molho("Branco");
//                 molhos.setVl_preco(15.20);
//                 molhos.setQtd_molho(10);
//                   controller2.salvar(molhos);
//
//                 controller.salvar(pedidos);
//

//                    massas  = new Massas();
//                    controller3 = new MassasController(context);
//                    massas.setNm_massa("Talharim");
//                    massas.setVl_preco(10.64);
//                    massas.setQtd_massa(10);
//                    controller3.salvar(massas);
