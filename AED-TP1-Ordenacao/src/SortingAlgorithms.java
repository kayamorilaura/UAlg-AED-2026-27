//Procura o menor número do conjunto e coloca-o na primeira posição. 
//Depois procura o menor número restante e coloca-o na segunda posição, e assim sucessivamente.

public class SortingAlgorithms {

        public void selectionSort(int[] numeros) { 
            for (int i = 0; i < numeros.length - 1; i++) {
            int menorIndice = i;   // Assumimos inicialmente que o primeiro número é o menor

                // começamos a procurar a partir da segunda posição < Continuamos até ao fim do array
            for (int j = i + 1; j < numeros.length; j++) { // j++ para avançar uma posição por vez
                if (numeros[j] < numeros[menorIndice]) { // compara o elemento atual com o menor encontrado
                    menorIndice  = j; // atualiza o índice do menor número encontrado
                }
            }   

            int temporario = numeros[i]; // guarda o valor do elemento na posição i
            numeros[i] = numeros[menorIndice]; // coloca o menor número encontrado na posição i
            numeros[menorIndice] = temporario; // coloca o valor do elemento na posição i
        }
    }
}