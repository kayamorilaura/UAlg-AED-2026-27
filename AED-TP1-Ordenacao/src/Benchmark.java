
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    public static void main(String[] args) {

        int[] tamanhos = {1000, 5000, 10000}; //cria um array com os tamanhos dos testes.
        int repeticoes = 3; //indica que cada algoritmo será executado três vezes.

        SortingAlgorithms ordenador = new SortingAlgorithms(); //objeto responsável pela ordenação.

        for (int tamanho : tamanhos) { //ciclo for que percorre os valores de uma coleção ou array.

            Random random = new Random(42); //objeto Random 42 é uma semente (seed)
            int[] original = new int[tamanho]; //serve para gerar a mesma sequência de números ao repetir a experiência nas mesmas condições.

            for (int i = 0; i < tamanho; i++) {
                original[i] = random.nextInt(100000);
            }

            int[] esperado = Arrays.copyOf(
                original, original.length
            );
            Arrays.sort(esperado);

            System.out.println("\nQuantidade: " + tamanho);

            String[] nomes = {
                "Selection Sort",
                "Bubble Sort",
                "Insertion Sort"
            };

            for (int algoritmo = 0; algoritmo < 3; algoritmo++) { // o algortimo executa 3 vezes

                double somaTempos = 0;

                for (int repeticao = 1;
                     repeticao <= repeticoes;
                     repeticao++) {

                    int[] copia = Arrays.copyOf(
                        original, original.length
                    );
                  // Executar o algoritmo de ordenação
                    long inicio = System.nanoTime();

                    if (algoritmo == 0) {
                        ordenador.selectionSort(copia);
                    } else if (algoritmo == 1) {
                        ordenador.bubbleSort(copia);
                    } else {
                        ordenador.insertionSort(copia);
                    }

                    long fim = System.nanoTime();

                    if (!Arrays.equals(copia, esperado)) {
                        throw new IllegalStateException(
                            "Resultado incorreto: " + nomes[algoritmo]
                        );
                    }

                    double tempoMs =
                        (fim - inicio) / 1_000_000.0;

                    somaTempos += tempoMs; //somaTempos = somaTempos + tempoMs;
                }

                double media = somaTempos / repeticoes;

                System.out.printf(
                    "%s: %.3f ms (media de %d testes)%n",
                    nomes[algoritmo], media, repeticoes
                );
            }
        }
    }
}
