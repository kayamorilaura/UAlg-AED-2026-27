
import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== TP1 - Ordenacao de Inteiros ===");

        // O programa precisa de receber exatamente 2 argumentos.
        // args[0] = caminho do ficheiro
        // args[1] = quantidade de inteiros
        if (args.length != 2) {
            System.out.println("Uso: java Main <ficheiro> <quantidade>");
            return;
        }

        String ficheiro = args[0];
        int quantidade;

        // Converte a quantidade recebida de String para int.
        try {
            quantidade = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Erro: a quantidade deve ser um inteiro.");
            return;
        }

        // Impede quantidades negativas ou iguais a zero.
        if (quantidade <= 0) {
            System.out.println("Erro: a quantidade deve ser positiva.");
            return;
        }

        try {
            // Cria os objetos responsáveis pelos ficheiros
            // e pelos algoritmos de ordenação.
            FileManager gestor = new FileManager();
            SortingAlgorithms ordenador = new SortingAlgorithms();

            // Lê o ficheiro apenas uma vez.
            int[] numeros = gestor.lerTexto(ficheiro, quantidade);

            System.out.println("Ficheiro: " + ficheiro);
            System.out.println("Quantidade: " + quantidade);

            // Evita mostrar milhares de números no terminal.
            if (quantidade <= 20) {
                System.out.println("Original: "
                        + Arrays.toString(numeros));
            }

            // Cada algoritmo precisa de receber os mesmos
            // números, na mesma ordem inicial.
            int[] numerosSelection = numeros.clone();
            int[] numerosBubble = numeros.clone();
            int[] numerosInsertion = numeros.clone();

            // ==================================================
            // SELECTION SORT
            // ==================================================

            // Guarda o instante antes da ordenação.
            long inicio = System.nanoTime();

            ordenador.selectionSort(numerosSelection);

            // Guarda o instante após a ordenação.
            long fim = System.nanoTime();

            // Calcula a duração em milissegundos.
            double tempoSelection = (fim - inicio) / 1_000_000.0;

            // ==================================================
            // BUBBLE SORT
            // ==================================================

            inicio = System.nanoTime();

            ordenador.bubbleSort(numerosBubble);

            fim = System.nanoTime();

            double tempoBubble = (fim - inicio) / 1_000_000.0;

            // ==================================================
            // INSERTION SORT
            // ==================================================

            inicio = System.nanoTime();

            ordenador.insertionSort(numerosInsertion);

            fim = System.nanoTime();

            double tempoInsertion = (fim - inicio) / 1_000_000.0;

            // ==================================================
            // VERIFICACAO DOS RESULTADOS
            // ==================================================

            // Compara os três arrays.
            // Todos devem conter os mesmos números ordenados.
            boolean iguais =
                    Arrays.equals(numerosSelection, numerosBubble)
                    && Arrays.equals(numerosSelection, numerosInsertion);

            // Cria uma referência ordenada usando a biblioteca
            // Java, apenas para verificar os resultados.
            int[] referencia = numeros.clone(); //evita que o Bubble Sort ou o Insertion Sort recebam um array já ordenado pelo Selection Sort
            Arrays.sort(referencia);

            boolean correto =
                    iguais && Arrays.equals(numerosSelection, referencia);

            if (!correto) {
                throw new IllegalStateException(
                        "Os algoritmos produziram resultados incorretos.");
            }

            System.out.println("\n=== TEMPOS DE EXECUCAO ===");

            System.out.printf("Selection Sort: %.3f ms%n", tempoSelection);
            System.out.printf("Bubble Sort:    %.3f ms%n", tempoBubble);
            System.out.printf("Insertion Sort: %.3f ms%n", tempoInsertion);

            System.out.println("Ordenacao correta: " + correto);

            if (quantidade <= 20) {
                System.out.println("Ordenado: "
                        + Arrays.toString(numerosSelection));
            }

            // ==================================================
            // ESCRITA DOS FICHEIROS
            // ==================================================

            // Cria a pasta de saída, se ainda não existir.
            File pastaSaida = new File("resultados");

            if (!pastaSaida.exists() && !pastaSaida.mkdirs()) {
                throw new IllegalStateException(
                        "Nao foi possivel criar a pasta resultados.");
            }

            String saidaTexto = "resultados/ordenados.txt";
            String saidaBinario = "resultados/ordenados.bin";

            // Guarda os mesmos inteiros ordenados em dois formatos.
            gestor.escreverTexto(saidaTexto, numerosSelection);
            gestor.escreverBinario(saidaBinario, numerosSelection);

            // Lê novamente o binário para confirmar
            // que a escrita e leitura preservaram os dados.
            int[] verificacaoBinaria =
                    gestor.lerBinario(saidaBinario, quantidade);

            if (!Arrays.equals(numerosSelection, verificacaoBinaria)) {
                throw new IllegalStateException(
                        "Erro na verificacao do ficheiro binario.");
            }

            System.out.println("\nFicheiro texto: " + saidaTexto);
            System.out.println("Ficheiro binario: " + saidaBinario);
            System.out.println("Verificacao binaria: OK");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
