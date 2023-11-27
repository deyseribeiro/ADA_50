//Criado por Deyse Ribeiro

//Instanciando bibliotecas
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.Comparator;

//Iniciando classe
public class BINGO
{
    //Iniciando função de seleção de jogadores
    public static String[] jogadores()
    {
        //Instanciando variaveis
        Scanner scanner = new Scanner(System.in);
        int numeroDeJogadores;
        System.out.println("Digite o numero de jogadores:");
        numeroDeJogadores = scanner.nextInt();
        String[] apelido = new String[numeroDeJogadores];
        //Gerando jogadores
        for (int jogador = 1; jogador <= numeroDeJogadores; jogador++)
        {
            System.out.println("\nJogador " + jogador + ":");
            apelido[jogador - 1] = scanner.next();
        }
        //Exibindo jogadores
        for (int jogador = 1; jogador <= numeroDeJogadores; jogador++)
        {
            System.out.print(apelido[jogador - 1] + "-");
        }
        System.out.print("\n");
        //Retornando lista de jogadores
        return apelido;
    }

    //Iniciando função de sorteio de numeros
    public static int sorteio()
    {
        //Gerando numeros randômicos
        Random random = new Random();
        int jogada = random.nextInt(59)+1;
        //Retornando numero randômico
        return jogada;
    }

    //Iniciando função de preenchimento de cartelas
    public static String[][] cartelas(String[] jogadores)
    {
        //Instanciando variaveis
        Scanner entrada = new Scanner(System.in);
        int modo = 1;
        String[][] cartelas = new String[jogadores.length][7];
        String num_manual;
        String[] individual;
        String[] digitos;
        //Definindo modo de preenchimento de cartelas
        System.out.println("Preenchimento de cartela automático(0) ou manual(1)?:");
        modo = entrada.nextInt();
        if(modo ==0)
        {
            System.out.println("MODO AUTOMÁTICO");
            //Preenchendo cartelas automaticamente
            for (int nomes = 0; nomes < jogadores.length; nomes++)
            {
                cartelas [nomes][0] = jogadores[nomes];
                cartelas [nomes][6] = "0";
                for (int numeros = 1; numeros <= 5; numeros++)
                {
                    cartelas [nomes][numeros] = Integer.toString(sorteio());
                }
            }

        }else if(modo == 1)
        {
            System.out.println("MODO MANUAL");
            //Preenchendo cartelas manualmente
            System.out.println("Insira os numeros das cartelas:");
            num_manual = entrada.next();
            digitos = num_manual.split("[, -]+");
            for (int nomes = 0; nomes < jogadores.length; nomes++)
            {
                cartelas [nomes][0] = jogadores[nomes];
                cartelas [nomes][6] = "0";
                for (int numeros = 1; numeros <= 5; numeros++)
                {
                    cartelas [nomes][numeros] = digitos[(nomes+1)*(numeros-1)];
                }
            }

        }
        //Exibindo cartelas(jogadores e numeros)
        System.out.println("CARTELAS:");
        for (int nomes = 0; nomes < jogadores.length; nomes++)
        {
            for (int numeros = 0; numeros < 6; numeros++)
            {
                System.out.print(cartelas[nomes][numeros]+"\t");
            }
            System.out.println("\n");
        }
        //Retornando cartelas
        return cartelas;
    }

    //Iniciando função de ordenação de matrizes
    public static void ordenar(String lista[][], int col)
    {
        Arrays.sort(lista, new Comparator<String[]>() {
            @Override
            // Compara valores de acordo com colunas e ordena as linhas
            public int compare(final String[] valor1,
                               final String[] valor2)
            {
                if (Integer.valueOf(valor1[col]) < Integer.valueOf(valor2[col]))
                    return 1;
                else
                    return -1;
            }
        });
    }

    //Iniciando função de conferencia de numeros sorteados
    public static void conferencia(String[][] cartelas)
    {
        //Instanciando variaveis
        Scanner entrada = new Scanner(System.in);
        int modo = 0;
        String prosseguir;
        int status = 0;
        int rodada = 1;
        int num_sort;
        int pontos;
        int[] presort = new int[60];
        int conf = 0;
        int pula = 0;
        String temp;
        String[][] visual = new String[cartelas.length][6];
        String[][] ranking = new String[cartelas.length][2];
        //Criando clone da matriz de cartelas original(item por item)
        //OBS: A matriz clone pode ser modificada sem interferir na original
        for(int linha = 0; linha< cartelas.length; linha++)
        {
            for(int coluna = 0; coluna <= 5; coluna++ )
            {
                visual[linha][coluna] = cartelas[linha][coluna];
            }
        }
        //Preenchendo uma lista com numeros aleatorios e conferindo se há repetição
        while (status == 0)
        {
            num_sort=sorteio();
            for(int ler = 0; ler <= conf; ler++)
            {
                if(presort[ler] == num_sort)
                {
                    pula = 1;
                    conf--;
                }
                if(presort[ler] == 0 && pula == 0)
                {
                    presort[conf] = num_sort;
                }
            }
            pula = 0;
            conf++;
            if(presort[58] != 0)
            {
                status = 1;
            }
        }
        status = 0;
        //System.out.println(Arrays.toString(presort));
        //Definindo modo de sorteido de numeros
        System.out.println("Sorteio automático(0) ou manual(1)?");
        modo = entrada.nextInt();
        while(status == 0)
        {
            //Sempre pergunta antes de sortear o próximo numero
            if(modo == 1)
            {
                System.out.println("Sortear?");
                prosseguir = entrada.nextLine();
            }
            //Exibe Rodada e numero sorteado
            System.out.println("Rodada"+rodada+":");
            System.out.println("Numero sorteado:"+presort[rodada-1]);
            //Exibe quem pontuou, marca numero sorteado e grava pontuação dos jogadores
            for (int jogadores = 0; jogadores < cartelas.length; jogadores++)
            {
                for (int numeros = 1; numeros <= 5; numeros++)
                {
                    if(Integer.valueOf(cartelas[jogadores][numeros]) == presort[rodada-1])
                    {
                        pontos = Integer.valueOf(cartelas[jogadores][6]);
                        pontos = pontos + 1;
                        cartelas[jogadores][6] = Integer.toString(pontos);
                        temp = visual[jogadores][numeros];
                        visual[jogadores][numeros] = "["+temp+"]";
                        System.out.println(cartelas[jogadores][0]+" pontuou!!!");
                        System.out.println(cartelas[jogadores][0]+" tem "+cartelas[jogadores][6]+" acertos...");
                    }
                }
                pula = 0;
                conf++;
                //Exibe quem ganhou o jogo e finaliza loop de repetição
                if (Integer.valueOf(cartelas[jogadores][6]) == 5)
                {
                    System.out.println(cartelas[jogadores][0]+" venceu o jogo!!!");
                    status = 1;
                }
            }
            //Exibe cartelas já marcadas
            for (int linha = 0; linha < cartelas.length; linha++)
            {
                for (int coluna = 0; coluna <= 5; coluna++)
                {
                    System.out.print(visual[linha][coluna]+"\t");
                }
                System.out.println("\n");
            }
            rodada++;
        }
        //Ranqueia jogadores com base na pontuação
        System.out.println("TABELA DE ACERTOS:");
        for (int linha = 0; linha < cartelas.length; linha++)
        {
            //System.out.print(cartelas[linha][0]+"\t"+cartelas[linha][6]);
            ranking[linha][0] = cartelas[linha][0];
            ranking[linha][1] = cartelas[linha][6];
            //System.out.println(ranking[linha][0]+"\t"+ranking[linha][1]);
            //System.out.println("\n");
        }
        ordenar(ranking,1);
        //Exibe ranking de jogadores e pontuação
        for (int linha = 0; linha < cartelas.length; linha++)
        {
            //System.out.print(cartelas[linha][0]+"\t"+cartelas[linha][6]);
            System.out.println(ranking[linha][0]+"\t"+ranking[linha][1]);
            //System.out.println("\n");
        }

    }

    //Iniciando função principal
    public static void main(String[] args)
    {
        //Titulo do jogo
        System.out.println("**********************************");
        System.out.println("**********************************");
        System.out.println(" *  BEM VINDO AO BINGO BINGODEY *");
        System.out.println("**********************************");
        System.out.println("**********************************");
        System.out.println("********Por: Deyse Ribeiro********");

        //Chamada de referência das funções do jogo
        conferencia(cartelas(jogadores()));

    }
}
//Fim do programa
