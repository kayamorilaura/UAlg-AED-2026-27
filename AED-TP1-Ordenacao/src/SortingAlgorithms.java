//Procura o menor número do conjunto e coloca-o na primeira posição. 
//Depois procura o menor número restante e coloca-o na segunda posição, e assim sucessivamente.

public class SortingAlgorithms {

    // SELECTION SORT
    // Procura o menor número e coloca-o na posição correta.
    public void selectionSort(int[] numeros) {

        for (int i = 0; i < numeros.length - 1; i++) {

            // Assume que o elemento na posição i é o menor
            int menorIndice = i;

            // Procura o menor na parte ainda não ordenada
            for (int j = i + 1; j < numeros.length; j++) {

                if (numeros[j] < numeros[menorIndice]) {
                    menorIndice = j;
                }
            }

            // Troca o menor encontrado com o elemento na posição i
            int temporario = numeros[i];
            numeros[i] = numeros[menorIndice];
            numeros[menorIndice] = temporario;
        }
    }

    // BUBBLE SORT
    // Compara elementos vizinhos e troca-os quando necessário.
    public void bubbleSort(int[] numeros) {

        for (int i = 0; i < numeros.length - 1; i++) {

            for (int j = 0; j < numeros.length - 1 - i; j++) {

                if (numeros[j] > numeros[j + 1]) {

                    int temporario = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temporario;
                }
            }
        }
    }

    public void insertionSort(int[] numeros) {

        for (int i = 1; i < numeros.length; i++) { // comeca em 1 porque o primeiro elemento é na direta e insertSort conta da direita para a esquerda

            int chave = numeros[i]; //Guarda o valor que queremos inserir no lugar correto.
            int j = i - 1; //j começa no elemento imediatamente anterior.

            while (j >= 0 && numeros[j] > chave) { //Enquanto estiver dentro do array e o número anterior for maior que a chave, deslocamos esse número para a direita
                numeros[j + 1] = numeros[j]; //Quando encontrarmos a posição certa, colocamos a chave.
                j--; // diminuir o valor de j em 1
            }

            numeros[j + 1] = chave;
        }
    }
}