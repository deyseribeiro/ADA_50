# ADA_50
Nesse diretório estão disponíveis as atividades de pesquisa e execução realizadas no desafio 07.
# Desafio 07 - Matrizes – Opcional
Contexto
• O código abaixo resolve o problema de multiplicação de matrizes 2x2.
• Avalie, pesquise e implemente, se necessário, as etapas a seguir
• Esta atividade vale +1 ponto extra

# Etapa 1
• Implemente antes um algoritmo que cria uma matriz para você dada uma dimensão passada por parâmetro
• Agora faça um algoritmo que permita também multiplicar por 3x3, 5x5, 10x10, 100x100, 1000x1000

# Etapa 2
• À medida que a matriz cresce, que mudou na lógica?
O aumento do tempo da execução e a utilização muito mais memória, pois quanto maior a matriz, maior o consumo.
• Avalie também pelo JConsole o consumo de recursos por dimensionamentos
◦ Qual foi o comportamento entre diferentes dimensões?
O aumento nas dimensões geralmente resulta em maior consumo de CPU e memória.

# Etapa 3
• Quantas multiplicações foram realizadas em cada caso? Implemente um contador.
static int multiplicacoesRealizadas = 0;

      static int[][] multiplicarMatrizes(int[][] matrizA, int[][] matrizB) {
          int dimensao = matrizA.length;
          int[][] resultado = new int[dimensao][dimensao];
      
          for (int i = 0; i < dimensao; i++) {
              for (int j = 0; j < dimensao; j++) {
                  for (int k = 0; k < dimensao; k++) {
                      resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                      multiplicacoesRealizadas++;
                  }
              }
         }
      
          return resultado;
      }
    
     public static void main(String[] args) {
         // ... (Código anterior)
     
         System.out.println("Número de multiplicações realizadas: " + multiplicacoesRealizadas);
     }
     Há alguma forma de diminuir o número de múltiplicações? Se sim, qual?
        ◦ Veja que as operações de adição podem ser realizadas em um único ciclo de clock de cpu
        ◦ Operações de multiplicação geralmente exigem vários ciclos de clock







# Código
public class Matriz {

    public static void main(String[] args) {

        int[][] a = new int[][] {  {1,2}, {3,4} };
        int[][] b = new int[][] { {-1,3}, {4,2} };
        int[][] c = new int[2][2];

        System.out.println(b.length); // qtde linhas
        System.out.println( b[0].length); // qtde cols da linha 0

        for (int i = 0; i < a.length; i++) { // i-linha
            for (int j = 0; j < b.length; j++) { // j-coluna
                for (int k = 0; k < b.length; k++) { // k-fator
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
            System.out.println(Arrays.toString( c[i] ) );
        }

    }
}
# Referências
• Multiplicação de Matrizes
• Ciclos de Clock por Instrução