//Procura o menor número do conjunto e coloca-o na primeira posição. 
//Depois procura o menor número restante e coloca-o na segunda posição, e assim sucessivamente.

public class SortingAlgorithms {

        public void selectionSort(int[] numeros) { 
            int menorIndide = 0;   // Assumimos inicialmente que o primeiro número é o menor

                // começamos a procurar a partir da segunda posição < Continuamos até ao fim do array
            for (int j =1; j < numeros.length; j++) { // j++ para avançar uma posição por vez
                if (numeros[j] < numeros[menorIndide]) { // compara o elemento atual com o menor encontrado
                    menorIndide = j; // atualiza o índice do menor número encontrado
                }
            }   

            int temporario = numeros[0]; // guarda o valor do primeiro elemento
            numeros[0] = numeros[menorIndide]; // coloca o menor número encontrado na primeira posição
            numeros[menorIndide] = temporario; // coloca o valor do primeiro elemento
        }
}