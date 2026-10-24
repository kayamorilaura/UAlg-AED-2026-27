
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    public static void main(String[] args) {

        int[] tamanhos = {1000, 5000, 10000};

        SortingAlgorithms ordenador = new SortingAlgorithms();
        Random random = new Random(42);

        for (int tamanho : tamanhos) {

            int[] original = new int[tamanho];

            for (int i = 0; i < tamanho; i++) {
                original[i] = random.nextInt(100000);
            }

            System.out.println("\nQuantidade: " + tamanho);

            for (int algoritmo = 1; algoritmo <= 3; algoritmo++) {

                int[] copia = Arrays.copyOf(
                    original, original.length //Porque cada algoritmo deve receber os mesmos dados originais, sem alterações dos anteriores
                );

                long inicio = System.nanoTime();

                if (algoritmo == 1) {
                    ordenador.selectionSort(copia);
                } else if (algoritmo == 2) {
                    ordenador.bubbleSort(copia);
                } else {
                    ordenador.insertionSort(copia);
                }

                long fim = System.nanoTime();

                double tempoMs = (fim - inicio) / 1_000_000.0;

                for (int i = 1; i < copia.length; i++) {
                    if (copia[i - 1] > copia[i]) {
                        throw new IllegalStateException(
                            "Array nao ordenado!"
                        );
                    }
                }

                String nome;

                if (algoritmo == 1) {
                    nome = "Selection Sort";
                } else if (algoritmo == 2) {
                    nome = "Bubble Sort";
                } else {
                    nome = "Insertion Sort";
                }

                System.out.printf(
                    "%s: %.3f ms%n", nome, tempoMs
                );
            }
        }
    }
}
