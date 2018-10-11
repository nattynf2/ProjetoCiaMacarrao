package android.curso.projetociamacarrao.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.MainActivity;
import android.curso.projetociamacarrao.view.controller.MassasController;
import android.curso.projetociamacarrao.view.fragments.MinhasMassas;
import android.curso.projetociamacarrao.view.model.Massas;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

// metodo adapter para popular cada item a ser injetado no resultado final, mostrando item a item que vem do BD
public class ResultadoMassas extends ArrayAdapter<Massas> implements View.OnClickListener {

    Context context;
    MinhasMassas minhasMassas = new MinhasMassas();

    private int ultimaPosicao = -1;

    AlertDialog.Builder builder; // cria o texto a ser exibido
    AlertDialog alert; // vai mostrar o que deve ser exibido

    //Objetos globais e coleção de dados (DataSet)
    ArrayList<Massas> dados;
    Massas massas;
    MassasController controller;
    ViewHolder linha;
    android.support.v4.app.FragmentManager fragmentManager;
    private static class ViewHolder{ // componentes de cada linha da listview
        TextView txtMassa,txtPreco;

        ImageView imgLogo;
        LinearLayout btMassa;

    }

    @Override
    public void onClick(View v) {
        int posicao = (Integer) v.getTag();//numero do clique na lista
        Object object = getItem(posicao);//cria o objeto da classe
        massas = (Massas) object; //  transforma o objeto para a classe Massa para usar seus atributos

        controller = new MassasController(getContext());
        switch (v.getId()){

            case R.id.imgLogo:


                MainActivity.txtValorTotalMa.setText(String.valueOf(UtilCadastro.formatarValorDecimal(massas.getVl_preco()))+" > ");

                MainActivity.nomeMassa = massas.getNm_massa();
                MainActivity.valorTotalMa = massas.getVl_preco();
                MainActivity.txtValorTotal.setText(String.valueOf(UtilCadastro.formatarValorDecimal(MainActivity.valorTotalMa)));
            break;


        }

    }

    @NonNull
    @Override
    public View getView(int position, View dataSet, @NonNull ViewGroup parent){// popular os listview com os dados do DataSet


        massas = getItem(position);


        if (dataSet == null){

            linha = new ViewHolder();// estanciar um novo item da lista

            LayoutInflater layoutResultadoFinalList =  LayoutInflater.from(getContext());// injetar na lista

            dataSet = layoutResultadoFinalList.inflate(R.layout.listview_massas, parent, false);

            linha.txtMassa = dataSet.findViewById(R.id.txtMassa);// todos os itens a serem injetados
            linha.txtPreco = dataSet.findViewById(R.id.txtPreco);
            linha.imgLogo = dataSet.findViewById(R.id.imgLogo);

            linha.btMassa = dataSet.findViewById(R.id.btMassa);



            dataSet.setTag(linha); // insere o obj na tag

        }else{
            linha = (ViewHolder)dataSet.getTag();

        }

        linha.txtMassa.setText( massas.getNm_massa());
        linha.txtPreco.setText("Valor: " +String.valueOf(UtilCadastro.formatarValorDecimal(massas.getVl_preco())));

        linha.imgLogo.setOnClickListener(this);
        linha.btMassa.setOnClickListener(this);
        linha.imgLogo.setTag(position);// para identificar cada logo ao ser clicado
        linha.btMassa.setTag(position);// para identificar cada logo ao ser clicado
        UtilCadastro.carregarFoto(linha.txtMassa.getText().toString(),linha.imgLogo);




        return dataSet;
    }

    public ResultadoMassas(ArrayList<Massas> dataSet, Context context) {
        super(context, R.layout.listview_massas, dataSet);

        this.dados = dataSet;
        this.context = context;


    }

    //metodo para atualizar a view assim que houver alguma alteração no dataSet
    public  void atualizarLista(ArrayList<Massas> novosDados){

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

