public class Processador {

    /*
    * Consiste em verificar, em todas as posições do texto entre 0 e n – m, se uma 
    ocorrência do padrão existe ou não. 

    Isto é feito deslocando o padrão sobre o texto por, exatamente, uma posição 
    à direita
    */
    public static void brutalForce(String linhaDoTxt, String padrao) {
        int txtTam = linhaDoTxt.length();
        int tamPadrao = padrao.length();
        int ocorrencias = 0;
        int comparacoes = 0;
    
        // força bruta 
        for (int i = 0; i < (txtTam - tamPadrao); i++) {
        for (int j = 0; j < tamPadrao; j++) {
            comparacoes++; // sempre incrementa comparaçoes
    
            if (linhaDoTxt.charAt(i + j) != padrao.charAt(j)) {
            break; // não achou=> não incrementa => break
            }
            if (j != tamPadrao - 1) {
            continue; // verifica o proximo pra ver se bate com o padrao
            }
            ocorrencias++; // chegou aqui encontrou 
    
            System.out.println("Comparações até achar: " + comparacoes); 
            comparacoes = 0; // seta pra 0
        }
        }
        System.out.println("Total de ocorrências: " + ocorrencias); // resumo das ocorrencias 
    }
    
    /*
        * Sempre que houver a detecção de um caractere diferente, após algumas igualdades, 
        já se conhecem alguns caracteres do texto no próximo emparelhamento.
    
        Essas informações são importantes para evitar comparações que já são compatíveis
    */
    public static void KMP(String linhaDoTxt, String padrao) {
        int prefixTable[] = criarTablePreFixo(padrao);
        int txtTam = linhaDoTxt.length();
        int tamPadrao = padrao.length();
        int ocorrencias = 0;
        int comparacoes = 0;
    
        int i = 0, j = 0;
        while (i < txtTam) { 
        if (padrao.charAt(j) == linhaDoTxt.charAt(i)) {
            comparacoes++; // aumenta comparações
            j++;
            i++;
        }
        if (j == tamPadrao) { // for igual => incrementa ocorrencias 
            ocorrencias++;
            System.out.println("Comparações até achar: " + comparacoes); // mostra a que achou
            comparacoes = 0;
            j = prefixTable[j - 1];
        } else if (i < txtTam && padrao.charAt(j) != linhaDoTxt.charAt(i)) {
            comparacoes++;
            if (j != 0) {
            j = prefixTable[j - 1];
            } else {
            i++;
            }
        }
        }
        System.out.println("Total de ocorrências: " + ocorrencias);  // resumo das ocorrencias 
    }

    /*
        * Pré-processamento: calcular prefixo.
        Essa função encapsula conhecimento sobre quantas posições deve-se caminhar e continuar.

        Na ocorrência de falha na tentativa de casamento:
        O algoritmo KMP consulta a tabela (vetor) de prefixo para evitar deslocamentos inválidos.
    */
    private static int[] criarTablePreFixo(String padrao) {
        int tamPadrao = padrao.length();
        int tabelaPreFixo[] = new int[tamPadrao];
        int prefixSize = 0;
        int i = 1;
        
        tabelaPreFixo[0] = 0;
        while (i < tamPadrao) {
            if (padrao.charAt(i) == padrao.charAt(prefixSize)) {
                prefixSize++;
                tabelaPreFixo[i] = prefixSize;
                i++;
            } else {
                if (prefixSize != 0) {
                    prefixSize = tabelaPreFixo[prefixSize - 1];
                } else {
                    tabelaPreFixo[i] = prefixSize;
                    i++;
                }
            }
        }
        return tabelaPreFixo;
    }
}

