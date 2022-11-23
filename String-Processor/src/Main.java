import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static String NomeArquivo = "teste-pratica3-2.txt";
    // padroes
    private static final String[] padroes = new String[]{ "TATATAAGAAAAAAG", "AGACTCTG", "GAGAGCGG", "TCCTCCCAC"};

    // metodo para ler cada linha do txt
    private static String lerDoTxt() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
            new FileReader(NomeArquivo)
        );

        String conteudoString = "";
        String linha = bufferedReader.readLine();
    
        while (linha != null) {
            conteudoString += linha;
            linha = bufferedReader.readLine();
        }
        bufferedReader.close();

        return conteudoString;
    }

    public static void main(String[] args) {
        try {
            String arquivo = lerDoTxt();

            // exibir usando os metodos do Processador
            for(int i = 0; i < 4; i++){
                System.out.println("\n------------------------------");
                System.out.println("Padrão: " + padroes[i]);
                System.out.println("Força Bruta:");
                Processador.brutalForce(arquivo, padroes[i]); //força bruta 

                System.out.println("\nKMP:");
                Processador.KMP(arquivo, padroes[i]); // KMP
                System.out.println("-------------------------------");
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}