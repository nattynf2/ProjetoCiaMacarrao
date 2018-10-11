package android.curso.projetociamacarrao.view.util;

import android.content.Context;
import android.curso.projetociamacarrao.R;
import android.curso.projetociamacarrao.view.MainActivity;
import android.curso.projetociamacarrao.view.model.CadastroUsuario;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class UtilCadastro {




        public static boolean isCPF(String CPF) {
// considera-se erro CPF's formados por uma sequencia de numeros iguais
            if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                    CPF.equals("22222222222") || CPF.equals("33333333333") ||
                    CPF.equals("44444444444") || CPF.equals("55555555555") ||
                    CPF.equals("66666666666") || CPF.equals("77777777777") ||
                    CPF.equals("88888888888") || CPF.equals("99999999999") ||
                    (CPF.length() != 11))
                return(false);

            char dig10, dig11;
            int sm, i, r, num, peso;

// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
            try {
// Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 10;
                for (i=0; i<9; i++) {
// converte o i-esimo caractere do CPF em um numero:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posicao de '0' na tabela ASCII)
                    num = (int)(CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

// Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for(i=0; i<10; i++) {
                    num = (int)(CPF.charAt(i) - 48);
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }

                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig11 = '0';
                else dig11 = (char)(r + 48);

// Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                    return(true);
                else return(false);
            } catch (InputMismatchException erro) {
                return(false);
            }
        }




    public static final String URL_WEB_SERVICE = "http://guiaindicadorlitoralsul.com.br/CiaMacarrao/";


    //tempo máximo para tentar a conexão
    public static final int CONNECTION_TIMEOUT = 10000; //10 segundos

    //tempo máximo para retorno da leitura do BD
    public static final int READ_TIMEOUT = 15000;


    public static void showMensagem(Context context, String mensagem){

        Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
    }
    public static String formatarValorDecimal(Double valor)
    {
        DecimalFormat df = new DecimalFormat("R$#,###,##0.00");
        return df.format(valor);
    }
    public static Double formatarValorString(String valor)
    {
        valor = valor.replace("R$", "");
        valor = valor.replace(",", ".");

        return Double.parseDouble(valor);
    }
    public static String pegarData(){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        String data = simpleFormat.format( new Date() );


        return data;
    }

    public static void carregarFoto(String url, ImageView imageView){

        if(url.equals("PENNE")){
            url = "https://imagizer.imageshack.com/v2/262x192q90/923/AEnYYl.jpg";
        }else if(url.equals("TALHARIM")){
            url = "https://imagizer.imageshack.com/v2/338x225q90/921/6Kdg88.jpg";
        }else if(url.equals("SPAGHETTI")){
            url = "https://imagizer.imageshack.com/v2/259x194q90/921/ywZQ9c.jpg";
        }else if(url.equals("PARAFUSO")){
            url = "https://imagizer.imageshack.com/v2/812x770q90/923/hbnKrg.jpg";
        }else if(url.equals("FARFALLE")){
            url = "https://imagizer.imageshack.com/v2/612x406q90/921/5DjVra.jpg";
        }else if(url.equals("LETRINHAS")){
            url = "https://imagizer.imageshack.com/v2/1024x683q90/923/SphY4B.jpg";
        }else if(url.equals("COCA-COLA")){
            url = "https://imagizer.imageshack.com/v2/895x468q90/921/NJn2jq.jpg";
        }else if(url.equals("CERVEJA")){
            url = "https://imagizer.imageshack.com/v2/721x721q90/924/TrP5E7.jpg";
        }else if(url.equals("AGUA")){
            url = "https://imagizer.imageshack.com/v2/721x721q90/921/eyDd1Y.jpg";
        }else if(url.equals("SUCO")){
            url = "https://imagizer.imageshack.com/v2/580x400q90/922/Cr9Hk8.jpg";
        }else if(url.equals("SUGO")){
            url = "https://imagizer.imageshack.com/v2/1081x721q90/924/NG8OfR.jpg";
        }else if(url.equals("BOLONHESA")){
            url = "https://imagizer.imageshack.com/v2/612x408q90/921/RSNuiT.jpg";
        }else if(url.equals("BRANCO")){
            url = "https://imagizer.imageshack.com/v2/400x268q90/924/41Tw53.jpg";
        }else if(url.equals("BACON")){
            url = "https://imagizer.imageshack.com/v2/768x384q90/923/e6pDwy.jpg";
        }else if(url.equals("AZEITONA")){
            url = "https://imagizer.imageshack.com/v2/265x265q90/921/1I4cwY.jpg";
        }else if(url.equals("BROCOLIS")){
            url = "https://imagizer.imageshack.com/v2/600x600q90/922/QteRlv.jpg";
        }else if(url.equals("OVO")){
            url = "https://imagizer.imageshack.com/v2/720x439q90/924/nJCRRF.jpg";
        }else if(url.equals("PEITOPERU")){
            url = "https://imagizer.imageshack.com/v2/780x496q90/924/2MeMbW.jpg";
        }
        Picasso.get()
                .load(url)
                .resize(180, 85)
                .centerCrop()
                .into(imageView);


    }

    public static void limparCampos(EditText txtNome,EditText txtCPF,EditText txtEmail, EditText txtEnd, EditText txtTel, EditText txtNumero, EditText txtLogin, EditText txtSenha) {

      txtNome.setText("");
      txtCPF.setText("");
      txtEmail.setText("");
      txtEnd.setText("");
      txtTel.setText("");
      txtNumero.setText("");
      txtLogin.setText("");
      txtSenha.setText("");

    }
}
