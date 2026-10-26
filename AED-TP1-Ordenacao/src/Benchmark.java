//Benchmark é um teste de desempenho. Serve para descobrir quanto tempo demora cada algoritmo a ordenar a mesma quantidade de números
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

//=======================================================
// INTERPRETAÇÃO DO COMANDO DO TERMINAL
//=======================================================
    public static void main(String[] args) {

        // Executa apenas os tamanhos pequenos.
        // Os maiores devem ser pedidos explicitamente.
        int[] tamanhos = {1000, 5000, 10000};

        if (args.length > 0) {  //se a quantidade de args recebido no terminal for maior que zero
            tamanhos = new int[args.length]; //usa os valores de tamanho

            try {
                for (int i = 0; i < args.length; i++) { // for (começa no primeiro argumento; continua enquanto existirem argumentos por percorrer; avança para a próxima posição)
                    tamanhos[i] = Integer.parseInt(args[i]); // converter em numeros interios a string que foi dada pelo ficheiro

                    if (tamanhos[i] <= 0) { // a quantidade for maior que 0 args
                        throw new NumberFormatException(); //NumberFormatException confirma que seja positivo e inteiro
                    }
                }
            } catch (NumberFormatException e) { //Identificação e mensagem de erro
                System.out.println("Erro: indique tamanhos positivos.");
                return; //termina main
            }
        }
//=======================================================
//PREPARAÇÃO DOS NÚMEROS E EXECUÇÃO DOS ALGORTIMOS E CRIAÇÃO DO CSV
//=======================================================
        int repeticoes = 3; // cada algoritmo será executado 3 vezes
        // já que o tempo do algoritmo pode variar dependendo da JVM
        SortingAlgorithms ordenador = new SortingAlgorithms(); // Lista de ordenação dos metodos

        String[] nomes = { //guarda os nomes dos algoritmos nas posições 0, 1 e 2.
            "Selection Sort",
            "Bubble Sort", 
            "Insertion Sort"
        };

        // Prepara o ficheiro csv na pasta dos resultados.
        File pasta = new File("resultados");

        if (!pasta.exists() && !pasta.mkdirs()) { // if(verifica se o caminho existe E "negação" tenta criar a pasta e os diretórios necessários)
            System.out.println("Erro ao criar a pasta resultados.");
            return;
        }

        StringBuilder nome = new StringBuilder("benchmark"); // construcão de textos por partes

        for (int tamanho : tamanhos) {
            nome.append("_").append(tamanho); //benchmark_1000_5000_10000.csv
        }

        nome.append(".csv");

        File ficheiroCSV = new File(pasta, nome.toString()); // Representa o ficheiro dentro da pasta resultados.
        int versao = 2; // Variavel com int 2 caso já exista uma versão desse arquivo
        while (ficheiroCSV.exists()) { // Procura os nomes.
            String nomeAlternativo =
                nome.toString().replace(".csv", "_" + versao + ".csv"); 

            ficheiroCSV = new File(pasta, nomeAlternativo);
            versao++;
}
        // CREATE_NEW cria um novo ficheiro e impede substituir um existente.
        try (PrintWriter csv = new PrintWriter(
                java.nio.file.Files.newBufferedWriter(
                    ficheiroCSV.toPath(),
                    java.nio.charset.StandardCharsets.UTF_8,
                    java.nio.file.StandardOpenOption.CREATE_NEW,
                    java.nio.file.StandardOpenOption.WRITE
                ))) {
            csv.println("tamanho;algoritmo;repeticao;tempo_ms"); //cabeçalho


// ===================================================
// AQUECIMENTO DA JVM SEM MEDIR O TEMPO
// ===================================================

            int[] aquecimento = new int[1000];  
            Random aquecerRandom = new Random(42); //semente fixa de 42, significa que escolhemos sempre o mesmo valor inicial.
//No Benchmark queremos que os três algoritmos recebam exatamente os mesmos números, na mesma ordem inicial
            for (int i = 0; i < aquecimento.length; i++) { 
                aquecimento[i] = aquecerRandom.nextInt(100000); //gera valores entre 0 e 99999
            }

            for (int algoritmo = 0; algoritmo < 3; algoritmo++) { // executa uma vez cada algoritmo sem medir o tempo.
                int[] copia = aquecimento.clone();
                ordenar(ordenador, algoritmo, copia); 
            }

// ===================================================
// INICIO DO BENCHMARK
// ===================================================     

            for (int tamanho : tamanhos) {
                System.out.println("\nQuantidade: " + tamanho);

                // A mesma seed permite reproduzir os dados.
                Random random = new Random(42); // gera os mesmos números do aquecimento porque tem a mesma seed
                int[] original = new int[tamanho]; //cria uma linha vazia com o tamanho indicado no terminal

                for (int i = 0; i < tamanho; i++) { // preenche essa linha vazia com o index correspondente do seed 42
                    original[i] = random.nextInt(100000); // gera 10.000
                }
                    //variavel com gabarito         // aqui se garante que usamos o original
                int[] esperado = original.clone(); //para não chegar ao outro algoritmo já ordenado
                Arrays.sort(esperado);  // o Arrays.sort já calculou como deve ser devolvido o resultado nos nossos algoritmos

                // Executa os três algoritmos.
                for (int algoritmo = 0; algoritmo < 3; algoritmo++) {  // 0 = Selection, 1 = Bubble, 2 = Insertion.

                    double soma = 0; //guarda a soma dos 3 tempos de execuções de cada algoritmo
                    for (int repeticao = 1;
                         repeticao <= repeticoes;
                         repeticao++) {
                    //Primeiro, o Selection Sort executa três vezes. 
                    //Só depois começa o Bubble Sort, também três vezes.
                    //Finalmente, o Insertion Sort executa três vezes.

                        // Cada execução começa com os mesmos dados.
                        int[] copia = original.clone();

                        long inicio = System.nanoTime(); // Regista o instante anterior à ordenação.

                        ordenar(ordenador, algoritmo, copia);
                        //ordenador — o objeto que contém os métodos de ordenação.
                        //algoritmo — o número que identifica qual método executar: 0, 1 ou 2.
                        //copia — o array que deve ser ordenado

                        long fim = System.nanoTime();  // Regista o instante após terminar a ordenação.

                        // A validação fica fora da medição.
                        if (!Arrays.equals(copia, esperado)) {
                            throw new IllegalStateException(
                                "Ordenacao incorreta: "
                                + nomes[algoritmo]
                            );
                        }

                        double tempo = (fim - inicio) / 1_000_000.0; // subtrai o inicio ao fim
                        soma += tempo;
//================================
// ESCREVE OS FICHEIROS EM CSV
//===============================
                        // Guarda o tempo individual no CSV.
                        csv.printf(
                            java.util.Locale.US,
                            "%d;%s;%d;%.3f%n",
                            tamanho, nomes[algoritmo],
                            repeticao, tempo
                        );

                        System.out.printf(
                            "%s - teste %d: %.3f ms%n",
                            nomes[algoritmo], repeticao, tempo
                        );
                    }

                    double media = soma / repeticoes;

                    System.out.printf(
                        "MEDIA %s: %.3f ms%n",
                        nomes[algoritmo], media
                    );
                }

                // Garante que os dados são escritos no CSV.
                csv.flush();
            }

            System.out.println("\nResultados guardados em "
                    + "resultados/benchmark.csv");

        } catch (IOException e) {
            System.out.println("Erro ao guardar: " + e.getMessage());
        }
    }
// =======================================================
// MÉTODO AUXILIAR - ESCOLHER O ALGORITMO
// =======================================================

    // Centraliza a escolha do algoritmo.
    // 0 = Selection, 1 = Bubble, 2 = Insertion.
    private static void ordenar(
            SortingAlgorithms ordenador,
            int algoritmo,
            int[] numeros) {

        switch (algoritmo) {
            case 0:
                ordenador.selectionSort(numeros);
                break;
            case 1:
                ordenador.bubbleSort(numeros);
                break;
            case 2:
                ordenador.insertionSort(numeros);
                break;
            default:
                throw new IllegalArgumentException(
                    "Algoritmo desconhecido.");
        }
    }
}
