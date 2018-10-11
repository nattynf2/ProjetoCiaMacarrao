package android.curso.projetociamacarrao.view.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.controller.CadastroUsuarioController;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// metodo adapter para popular cada item a ser injetado no resultado final, mostrando item a item que vem do BD
public class ResultadoUsuarios extends ArrayAdapter<CadastroUsuario> implements View.OnClickListener {

    Context context;

    private int ultimaPosicao = -1;

    AlertDialog.Builder builder; // cria o texto a ser exibido
    AlertDialog alert; // vai mostrar o que deve ser exibido

    //Objetos globais e coleção de dados (DataSet)
    ArrayList<CadastroUsuario> dados;
    CadastroUsuario cadastroUsuario;
    CadastroUsuarioController controller;
    ViewHolder linha;
    private static class ViewHolder{ // componentes de cada linha da listview
        TextView txtNome,txtEnd,txtNumero,txtCPF,txtEmail;

        ImageView  imgConsultar, imgDeletar, imgEditar;


    }

    @Override
    public void onClick(View v) {
        int posicao = (Integer) v.getTag();//numero do clique na lista
        Object object = getItem(posicao);//cria o objeto da classe
        cadastroUsuario = (CadastroUsuario) object; //  transforma o objeto para a classe MediaEscolar para usar seus atributos
        controller = new CadastroUsuarioController(getContext());
        switch (v.getId()){

            case R.id.imgDeletar:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Delete");
                builder.setMessage("Deseja DELETAR este pedido ?");
                builder.setCancelable(true);// se tem a opção de cancelar
                builder.setIcon(R.mipmap.ic_launcher_round);

                builder.setPositiveButton("SIM", new Dialog.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        // confirmar o deletar o registro

                        //todo - verificar o controller
                        controller.deletar(cadastroUsuario);
                        linha.imgDeletar.setEnabled(false);
                        //todo - verificar o datasource metodos
                        atualizarLista(controller.getResultadoFinal());
                        //notifyDataSetChanged();//avisar que houve uma alteração
                        //todo - criado e usado com um metodo para mensagem
                        UtilCadastro.showMensagem(context,"Deletado com sucesso!");


                    }
                });
                builder.setNegativeButton("CANCELAR", new Dialog.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        // Cancelar o deletar do registro
                        dialog.cancel();
                    }
                });

                alert = builder.create();
                alert.show();
                break;

//            case R.id.imgEditar:
//
//                //TODO - Para chamar um layout na view atual. - como uma caixa de dialogo
//                View alertView = v.inflate(getContext(),R.layout.alert_dialog_editar_listview,null);
//
//                final EditText editMateria,editNotaProva,editNotaTrabalho;
//
//                editMateria = alertView.findViewById(R.id.editMateria);// indicar onde busca os id's dos campos - alertView ( do layout - alert_dialog)
//                editNotaProva = alertView.findViewById(R.id.editNotaProva);// indicar onde busca os id's dos campos - alertView ( do layout - alert_dialog)
//                editNotaTrabalho = alertView.findViewById(R.id.editNotaTrabalho);// indicar onde busca os id's dos campos - alertView ( do layout - alert_dialog)
//
//                editMateria.setText(mediaEscolar.getMateria());
//                editNotaProva.setText(String.valueOf(mediaEscolar.getNotaProva()));
//                editNotaTrabalho.setText(String.valueOf(mediaEscolar.getNotaTrabalho()));
//
//                AlertDialog.Builder alertbox = new AlertDialog.Builder(alertView.getRootView().getContext());
//                alertbox.setMessage(mediaEscolar.getBimestre());
//                alertbox.setTitle("Editando...");
//                alertbox.setView(alertView);
//                //TODO - Inserir os botoes no alertBox
//                alertbox.setNeutralButton("Salvar",
//                        new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface arg0,
//                                                int arg1) {
//
//                                //TODO - falta fazer validação.
//
//                                mediaEscolar.setMateria(editMateria.getText().toString());
//                                mediaEscolar.setNotaProva(Double.parseDouble(editNotaProva.getText().toString()));
//                                mediaEscolar.setNotaTrabalho(Double.parseDouble(editNotaTrabalho.getText().toString()));
//
//                                Double mediaFinal = controller.calcularMedia(mediaEscolar);
//                                UtilMediaEscolar.formatarValorDecimal(mediaFinal);
//                                mediaEscolar.setMediaFinal(mediaFinal);
//                                mediaEscolar.setSituacao(controller.resultadoFinal(mediaFinal));
//
////                                try {
////                                    controller.alterar(mediaEscolar);
////                                    AlterarAsyncTask task = new AlterarAsyncTask(mediaEscolar, context);
////                                    task.execute();
////                                }catch (Exception e){
////                                    Log.e("Adapter" ,"Erro: " + e.getMessage());
////                                }
//
//                                atualizarLista(controller.getAllResultadoFinal());
//
////                                AlterarAsyncTask task = new AlterarAsyncTask(mediaEscolar,context);
////                                task.execute();
//                                UtilCadastro.showMensagem(context,"Alterado com sucesso!");
//
//                            }
//                        });
//                alertbox.show();
//                break;
            case R.id.imgConsultar:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Pedido");
                builder.setMessage("Nome: " + cadastroUsuario.getNm_usuario() + "\nEmail: " + cadastroUsuario.getNm_email() +"\nCPF: "+ cadastroUsuario.getNr_cpf());
                builder.setCancelable(true);// se tem a opção de cancelar
                builder.setIcon(R.mipmap.ic_launcher_round);

                builder.setPositiveButton("VOLTAR", new Dialog.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });


                alert = builder.create();
                alert.show();

                break;




        }

    }

    @NonNull
    @Override
    public View getView(int position, View dataSet, @NonNull ViewGroup parent){// popular os listview com os dados do DataSet


        cadastroUsuario = getItem(position);


        if (dataSet == null){

            linha = new ViewHolder();// estanciar um novo item da lista

            LayoutInflater layoutResultadoFinalList =  LayoutInflater.from(getContext());// injetar na lista

            dataSet = layoutResultadoFinalList.inflate(R.layout.listview_resultado_cadastro, parent, false);

            linha.txtNome = dataSet.findViewById(R.id.txtNome);// todos os itens a serem injetados
            linha.txtEnd = dataSet.findViewById(R.id.txtEnd);
            linha.txtNumero = dataSet.findViewById(R.id.txtNumero);
            linha.txtCPF = dataSet.findViewById(R.id.txtCPF);
            linha.txtEmail = dataSet.findViewById(R.id.txtLogin);
            linha.imgConsultar = dataSet.findViewById(R.id.imgConsultar);
            linha.imgDeletar = dataSet.findViewById(R.id.imgDeletar);


            dataSet.setTag(linha); // insere o obj na tag

        }else{
            linha = (ViewHolder)dataSet.getTag();

        }

        linha.txtNome.setText( cadastroUsuario.getNm_usuario());
        linha.txtEnd.setText("Rua: " +cadastroUsuario.getNm_endereco());
        linha.txtNumero.setText("N: " +String.valueOf(cadastroUsuario.getNr_endereco()));
        linha.txtEmail.setText("Email: " +cadastroUsuario.getNm_email());
        linha.txtCPF.setText("CPF: "  + cadastroUsuario.getNr_cpf());// para formatar o valor



        linha.imgConsultar.setOnClickListener(this);
        linha.imgConsultar.setTag(position);// para identificar cada logo ao ser clicado

        linha.imgDeletar.setOnClickListener(this);
        linha.imgDeletar.setTag(position);// para identificar cada logo ao ser clicado



        return dataSet;
    }

    public ResultadoUsuarios(ArrayList<CadastroUsuario> dataSet, Context context) {
        super(context, R.layout.listview_resultado_cadastro, dataSet);

        this.dados = dataSet;
        this.context = context;


    }

    //metodo para atualizar a view assim que houver alguma alteração no dataSet
    public  void atualizarLista(ArrayList<CadastroUsuario> novosDados){

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



}

