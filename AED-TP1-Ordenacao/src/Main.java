
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== TP1 - Ordenacao de Inteiros ===");

        // =================================================
        // 1. VALIDAR ARGUMENTOS
        // =================================================

        // O programa deve receber exatamente 2 argumentos:
        // 1 - Caminho do ficheiro
        // 2 - Quantidade de inteiros
        if (args.length != 2) {
            System.out.println(
                "Uso: java -cp out Main <ficheiro> <quantidade>"
            );
            return;
        }

        String caminho = args[0];
        int quantidade;

        // Converter a quantidade de String para int.
        try {
            quantidade = Integer.parseInt(args[1]);

        } catch (NumberFormatException e) {

            System.out.println(
                "Erro: a quantidade deve ser um numero inteiro."
            );
            return;
        }

        // Não permitimos quantidades negativas ou zero.
        if (quantidade <= 0) {
            System.out.println(
                "Erro: a quantidade deve ser positiva."
            );
            return;
        }

        // Identificamos o tipo de ficheiro pela extensão.
        boolean binario = caminho.toLowerCase().endsWith(".bin");
        boolean texto = caminho.toLowerCase().endsWith(".txt");

        if (!binario && !texto) {
            System.out.println(
                "Erro: use um ficheiro .txt ou .bin."
            );
            return;
        }


        // =================================================
        // 2. LER OS INTEIROS
        // =================================================

        try {

            // Criamos um objeto responsável pelos ficheiros.
            FileManager gestor = new FileManager();

            int[] numeros;

            if (binario) {

                // Leitura de ficheiro binário.
                numeros = gestor.lerBinario(caminho, quantidade);

            } else {

                // Leitura de ficheiro de texto.
                numeros = gestor.lerTexto(caminho, quantidade);
            }

            // Mostramos os valores apenas nos vetores pequenos.
            // Evitamos imprimir milhares de números.
            if (quantidade <= 30) {
                System.out.println(
                    "Numeros lidos: " + Arrays.toString(numeros)
                );
            }


            // =================================================
            // 3. ORDENAR COM QUICK SORT
            // =================================================

            SortingAlgorithms ordenador = new SortingAlgorithms();

            // Guardamos o instante inicial da ordenação.
            long inicio = System.nanoTime();

            // Executamos o algoritmo escolhido.
            ordenador.quickSort(numeros);

            // Guardamos o instante final.
            long fim = System.nanoTime();

            // Convertimos nanossegundos em milissegundos.
            double tempoMs = (fim - inicio) / 1_000_000.0;

            System.out.printf(
                "Tempo Quick Sort: %.3f ms%n",
                tempoMs
            );


            // =================================================
            // 4. PREPARAR O NOVO FICHEIRO
            // =================================================

            // Obtemos apenas o nome do ficheiro original.
            File entrada = new File(caminho);
            String nome = entrada.getName();

            // Descobrimos a posição do último ponto.
            int ponto = nome.lastIndexOf('.');

            // Criamos um nome com o sufixo "_ordenado".
            String nomeSaida =
                nome.substring(0, ponto)
                + "_ordenado"
                + nome.substring(ponto);

            // Criamos a pasta resultados, se necessário.
            File pastaResultados = new File("resultados");

            if (!pastaResultados.exists()
                    && !pastaResultados.mkdirs()) {

                throw new IOException(
                    "Nao foi possivel criar a pasta resultados."
                );
            }

            // Caminho completo para o novo ficheiro.
            String destino = new File(
                pastaResultados, nomeSaida
            ).getPath();


            // =================================================
            // 5. ESCREVER O FICHEIRO ORDENADO
            // =================================================

            if (binario) {

                // Guardamos os inteiros em formato binário.
                gestor.escreverBinario(destino, numeros);

            } else {

                // Guardamos os inteiros em formato texto.
                gestor.escreverTexto(destino, numeros);
            }

            if (quantidade <= 30) {
                System.out.println(
                    "Array ordenado: " + Arrays.toString(numeros)
                );
            }

            System.out.println(
                "Resultado guardado em: " + destino
            );

        } catch (IOException | RuntimeException e) {

            // Tratamento de erros de leitura, escrita
            // ou execução.
            System.err.println(
                "Erro: " + e.getMessage()
            );
        }
    }
}
