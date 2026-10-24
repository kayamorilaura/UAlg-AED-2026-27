import java.util.Arrays;

class Main {

    public static void main(String[] args) { //lista de textos recebidos do terminal

        System.out.println("=== TP1 - Ordenacao de Inteiros ===");

        if (args.length != 2) { // a quantidade de argumentos é diferente de 2
            System.out.println("Uso: java Main <ficheiro> <quantidade>"); // impede que o programa continue com arqumentos em falta
            return;
        }

        String ficheiro = args[0];
        int quantidade; //váriaveis 

    // TRATAMENTO DE EXCEÇÕES - ATUALIZA PERANTE O CÓDIGO FOR DESENVOLVIDO

        try { // tenta fazer esta operação, se não conseguir, vai para o catch
            quantidade = Integer.parseInt(args[1]);

            // catch se ocorrer um erro do tipo indicado, executa o tratamento de erro abaixo
        } catch (NumberFormatException e) {
            System.out.println("Erro: a quantidade deve ser um numero inteiro.");
            return;
        }

        if (quantidade <= 0) {
            System.out.println("Erro: a quantidade deve ser positiva.");
            return;
        }

        System.out.println("Ficheiro: " + ficheiro);
        System.out.println("Quantidade de inteiros: " + quantidade);
        System.out.println("Argumentos validados com sucesso!");


        try {            // gestor = váriavel que guarda a referencia ao objeto
            FileManager gestor = new FileManager(); // cria um objeto da classe FILEMANAGER

            int[] numeros = gestor.lerTexto(ficheiro, quantidade); // Pede ao gestor para ler o ficheiro e guardar os números no array

            System.out.println("Numeros lidos: " // Monstra o texto do array, os número
                    + Arrays.toString(numeros));

            SortingAlgorithms ordenador = new SortingAlgorithms(); // Cria um objeto da classe SortingAlgorithms

            //Cria novo ficheiro com os números ordenados e escreve o array ordenado no ficheiro de saída
            ordenador.selectionSort(numeros); // Ordenar o array

            int[] numerosBubble = gestor.lerTexto(ficheiro, quantidade);
            ordenador.bubbleSort(numerosBubble);
            System.out.println("Array ordenado (Bubble Sort): " + Arrays.toString(numerosBubble));
            String ficheiroSaida = "dados/ordenados.txt";
            gestor.escreverTexto(ficheiroSaida, numeros);
            System.out.println("Ficheiro criado: " + ficheiroSaida);
            System.out.println("Array ordenado (Selection Sort): " + Arrays.toString(numeros));

            // Cria um ficheiro binário com os números ordenados
            gestor.escreverBinario("dados/ordenados.bin", numeros);
            System.out.println("Ficheiro binario criado!");

            //teste de ida e volta: escreve inteiros num ficheiro binárip e depois verificamos que continuaram os mesmos
            int[] numerosBinarios = gestor.lerBinario(
                    "dados/ordenados.bin", quantidade);
            System.out.println("Numeros lidos do binario: "
                    + Arrays.toString(numerosBinarios));

            int[] numerosInsertion = gestor.lerTexto(ficheiro, quantidade);
            ordenador.insertionSort(numerosInsertion);
            System.out.println("Array ordenado (Insertion Sort): "
                    + Arrays.toString(numerosInsertion));


        } catch (Exception e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }
}
