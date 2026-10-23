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
            
            ordenador.selectionSort(numeros); // Ordenar o array

            System.out.println("Apos primeira passagem: " // Mostrar o resultado
                    + Arrays.toString(numeros));


        } catch (Exception e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }
}
