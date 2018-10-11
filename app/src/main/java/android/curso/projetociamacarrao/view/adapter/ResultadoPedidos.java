package android.curso.projetociamacarrao.view.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.controller.PedidosClientesController;
import android.curso.projetociamacarrao.view.model.Pedidos;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// metodo adapter para popular cada item a ser injetado no resultado final, mostrando item a item que vem do BD
public class ResultadoPedidos extends ArrayAdapter<Pedidos> implements View.OnClickListener {

    Context context;

    private int ultimaPosicao = -1;

    AlertDialog.Builder builder; // cria o texto a ser exibido
    AlertDialog alert; // vai mostrar o que deve ser exibido

    //Objetos globais e coleção de dados (DataSet)
    ArrayList<Pedidos> dados;
    Pedidos pedidos;
    PedidosClientesController controller;
    ViewHolder linha;
    private static class ViewHolder{ // componentes de cada linha da listview
        TextView txtNome,txtValorTotal,txtIdPedido,txtData;

        ImageView  imgConsultar, imgDeletar;


    }

    @Override
    public void onClick(View v) {
        int posicao = (Integer) v.getTag();//numero do clique na lista
        Object object = getItem(posicao);//cria o objeto da classe
        pedidos = (Pedidos) object; //  transforma o objeto para a classe MediaEscolar para usar seus atributos
        controller = new PedidosClientesController(getContext());
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
                        controller.deletar(pedidos);
                        linha.imgDeletar.setEnabled(false);
                        //todo - verificar o datasource metodos
                        atualizarLista(controller.getAllPedidos());
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

            case R.id.imgConsultar:


                builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Pedido");
                builder.setMessage("Pedido: " + pedidos.getId_pedido() +"\nMassa: " + pedidos.getNm_massa() +"\nMolho: " + pedidos.getNm_molho()+"\nAdd: " + pedidos.getNm_adicional() + "\nBebida: " + pedidos.getNm_bebida()+"\nValor: "+ UtilCadastro.formatarValorDecimal(pedidos.getValor_total()) +"\nData: " + pedidos.getDt_pedido());
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


        pedidos = getItem(position);


        if (dataSet == null){

            linha = new ViewHolder();// estanciar um novo item da lista

            LayoutInflater layoutResultadoFinalList =  LayoutInflater.from(getContext());// injetar na lista

            dataSet = layoutResultadoFinalList.inflate(R.layout.listview_resultado_pedido, parent, false);

            linha.txtData = dataSet.findViewById(R.id.txtData);// todos os itens a serem injetados
            linha.txtIdPedido = dataSet.findViewById(R.id.txtIdPedido);
            linha.txtNome = dataSet.findViewById(R.id.txtNome);
            linha.imgConsultar = dataSet.findViewById(R.id.imgConsultar);
            linha.imgDeletar = dataSet.findViewById(R.id.imgDeletar);
            linha.txtValorTotal = dataSet.findViewById(R.id.txtValorTotal);

            dataSet.setTag(linha); // insere o obj na tag

        }else{
            linha = (ViewHolder)dataSet.getTag();

        }

        linha.txtData.setText( pedidos.getDt_pedido());
        linha.txtIdPedido.setText("N Pedido: " +String.valueOf(pedidos.getId_pedido()));
        linha.txtNome.setText("Nome: " +pedidos.getNm_usuario());
        linha.txtValorTotal.setText("Valor: " + String.valueOf(UtilCadastro.formatarValorDecimal(pedidos.getValor_total())));// para formatar o valor



        linha.imgConsultar.setOnClickListener(this);
        linha.imgConsultar.setTag(position);// para identificar cada logo ao ser clicado

        linha.imgDeletar.setOnClickListener(this);
        linha.imgDeletar.setTag(position);// para identificar cada logo ao ser clicado



        return dataSet;
    }

    public ResultadoPedidos(ArrayList<Pedidos> dataSet, Context context) {
        super(context, R.layout.listview_resultado_pedido, dataSet);

        this.dados = dataSet;
        this.context = context;


    }

    //metodo para atualizar a view assim que houver alguma alteração no dataSet
    public  void atualizarLista(ArrayList<Pedidos> novosDados){

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

