
public class SortingAlgorithms {

    // =====================================================
    // 1. SELECTION SORT
    // Procura o menor número e coloca-o na posição correta.
    // =====================================================

    public void selectionSort(int[] numeros) {

        for (int i = 0; i < numeros.length - 1; i++) {

            // Assume que o elemento atual é o menor.
            int menorIndice = i;

            // Procura o menor na parte ainda não ordenada.
            for (int j = i + 1; j < numeros.length; j++) {

                if (numeros[j] < numeros[menorIndice]) {
                    menorIndice = j;
                }
            }

            // Troca o menor encontrado com o elemento atual.
            int temporario = numeros[i];
            numeros[i] = numeros[menorIndice];
            numeros[menorIndice] = temporario;
        }
    }


    // =====================================================
    // 2. BUBBLE SORT
    // Compara elementos vizinhos e troca-os quando necessário.
    // =====================================================

    public void bubbleSort(int[] numeros) {

        for (int i = 0; i < numeros.length - 1; i++) {

            for (int j = 0; j < numeros.length - 1 - i; j++) {

                // Se o número da esquerda for maior, trocamos.
                if (numeros[j] > numeros[j + 1]) {

                    int temporario = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temporario;
                }
            }
        }
    }


    // =====================================================
    // 3. INSERTION SORT
    // Insere cada número na posição correta.
    // =====================================================

    public void insertionSort(int[] numeros) {

        // O primeiro elemento é considerado ordenado.
        for (int i = 1; i < numeros.length; i++) {

            // Número que queremos colocar na posição correta.
            int chave = numeros[i];

            // Começamos a comparar com o elemento anterior.
            int j = i - 1;

            // Deslocamos os elementos maiores para a direita.
            while (j >= 0 && numeros[j] > chave) {

                numeros[j + 1] = numeros[j];
                j--;
            }

            // Inserimos a chave no espaço encontrado.
            numeros[j + 1] = chave;
        }
    }


    // =====================================================
    // 4. SHELL SORT
    // Baseado no Insertion Sort, mas usa intervalos.
    // Algoritmo estudado na Aula TP4.
    // =====================================================

    public void shellSort(int[] numeros) {

        // Começamos com um intervalo igual a metade do vetor.
        // A cada passagem, reduzimos o intervalo pela metade.
        for (int intervalo = numeros.length / 2;
             intervalo > 0;
             intervalo /= 2) {

            // Percorremos os elementos a partir do intervalo.
            for (int i = intervalo; i < numeros.length; i++) {

                // Guardamos o número que queremos inserir.
                int temporario = numeros[i];

                int j = i;

                // Comparamos elementos separados pelo intervalo.
                // Se o anterior for maior, deslocamo-lo.
                while (j >= intervalo &&
                       numeros[j - intervalo] > temporario) {

                    numeros[j] = numeros[j - intervalo];

                    // Recuamos uma posição do intervalo.
                    j -= intervalo;
                }

                // Colocamos o número na posição correta.
                numeros[j] = temporario;
            }
        }
    }


    // =====================================================
    // 5. QUICK SORT
    // Utiliza um pivot para dividir o vetor em partes.
    // Algoritmo estudado na Aula TP4.
    // =====================================================

    public void quickSort(int[] numeros) {

        // Só precisamos ordenar se houver mais de um elemento.
        if (numeros.length > 1) {

            quickSortRecursivo(
                numeros,
                0,
                numeros.length - 1
            );
        }
    }


    // Método auxiliar que ordena as partições do vetor.
    private void quickSortRecursivo(
        int[] numeros,
        int inicio,
        int fim
    ) {

        while (inicio < fim) {

            // Organiza os elementos relativamente ao pivot.
            // Devolve a posição final desse pivot.
            int indicePivot = particionar(numeros, inicio, fim);

            // Ordenamos recursivamente a partição menor.
            // A maior é tratada pelo ciclo while.
            // Isto reduz o uso de memória da recursividade.
            if (indicePivot - inicio < fim - indicePivot) {

                quickSortRecursivo(
                    numeros,
                    inicio,
                    indicePivot - 1
                );

                inicio = indicePivot + 1;

            } else {

                quickSortRecursivo(
                    numeros,
                    indicePivot + 1,
                    fim
                );

                fim = indicePivot - 1;
            }
        }
    }


    // Método que coloca o pivot na posição correta.
    private int particionar(
        int[] numeros,
        int inicio,
        int fim
    ) {

        // Escolhemos o elemento central como pivot.
        int meio = inicio + (fim - inicio) / 2;

        // Movemos o pivot temporariamente para o final.
        int auxiliar = numeros[meio];
        numeros[meio] = numeros[fim];
        numeros[fim] = auxiliar;

        int pivot = numeros[fim];

        // Indica a próxima posição para um elemento menor.
        int posicao = inicio;

        for (int j = inicio; j < fim; j++) {

            // Se o elemento for menor que o pivot,
            // colocamo-lo na parte esquerda.
            if (numeros[j] < pivot) {

                int temporario = numeros[posicao];
                numeros[posicao] = numeros[j];
                numeros[j] = temporario;

                posicao++;
            }
        }

        // Colocamos o pivot entre as duas partições.
        int temporario = numeros[posicao];
        numeros[posicao] = numeros[fim];
        numeros[fim] = temporario;

        // Devolvemos o índice final do pivot.
        return posicao;
    }
}
