import java.util.Random;

public class matrizes01 {

    public static void main(String[] args) {
        int dimensao = 5;  // Altere a dimensão conforme necessário
        int[][] matrizA = criarMatriz(dimensao);
        int[][] matrizB = criarMatriz(dimensao);

        int[][] resultado = multiplicarMatrizes(matrizA, matrizB);

        // Exibindo o resultado
        System.out.println("Matriz A:");
        exibirMatriz(matrizA);
        System.out.println("\nMatriz B:");
        exibirMatriz(matrizB);
        System.out.println("\nResultado da multiplicação:");
        exibirMatriz(resultado);
    }

    static int[][] criarMatriz(int dimensao) {
        int[][] matriz = new int[dimensao][dimensao];
        Random random = new Random();

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                matriz[i][j] = random.nextInt(10);  // Valores aleatórios entre 0 e 9
            }
        }

        return matriz;
    }

    static int[][] multiplicarMatrizes(int[][] matrizA, int[][] matrizB) {
        int dimensao = matrizA.length;
        int[][] resultado = new int[dimensao][dimensao];

        for (int i = 0; i < dimensao; i++) {
            for (int j = 0; j < dimensao; j++) {
                for (int k = 0; k < dimensao; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }

        return resultado;
    }

    static void exibirMatriz(int[][] matriz) {
        for (int[] linha : matriz) {
            for (int valor : linha) {
                System.out.printf(" %4d", valor);
            }
            System.out.println();


        }
    }
}
