package android.curso.projetociamacarrao.view.fragments;

import android.content.Context;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.MainActivity;
import android.curso.projetociamacarrao.view.util.EnviarEmailAsyncTask;
import android.curso.projetociamacarrao.view.util.UtilCadastro;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Contato extends Fragment {

    public Contato() {
        // Required empty public constructor
    }

    Context context;
    View view;
    TextView txtNomeC, txtEmailC, txtMsgC;
    Button btEnviarC;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contato, container, false);

        txtNomeC = view.findViewById(R.id.txtNomeC);
        txtEmailC =  view.findViewById(R.id.txtEmailC);
        txtMsgC = view.findViewById(R.id.txtMsgC);
        btEnviarC = view.findViewById(R.id.btEnviarC);
        context = getContext();

        txtNomeC.setText(MainActivity.cnome);
        txtEmailC.setText(MainActivity.cemail);
        txtMsgC.requestFocus();


        btEnviarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(txtNomeC.getText().toString().equals("") || txtEmailC.getText().toString().equals("") || txtMsgC.getText().toString().equals("")){
                    UtilCadastro.showMensagem(context, "Favor preencher todos os campos!");
                    txtMsgC.requestFocus();

                }else{
                    EnviarEmailAsyncTask task = new EnviarEmailAsyncTask(txtNomeC,txtEmailC,txtMsgC,context);
                    task.execute();
                    UtilCadastro.showMensagem(context,"Enviado com sucesso!");
                    txtMsgC.setText("");
                    txtMsgC.requestFocus();
                }



            }
        });

        return view;
    }

}
