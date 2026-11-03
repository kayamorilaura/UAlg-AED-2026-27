/**
 * Lista simplesmente ligada de pessoas.
 * Mantem referencias ao primeiro e ao ultimo no.
 */
public class ListaLigada {
    private No primeiro;
    private No ultimo;
    private int tamanho;

    /** Insere no fim, preservando a ordem de chegada. O(1). */
    public void adicionar(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("A pessoa nao pode ser nula.");
        }

        No novo = new No(pessoa);
        if (primeiro == null) {
            primeiro = novo;
        } else {
            ultimo.proximo = novo;
        }
        ultimo = novo;
        tamanho++;
    }

    /** Percorre a lista e apresenta os registos. O(n). */
    public void mostrar() {
        No atual = primeiro;
        while (atual != null) {
            System.out.println(atual.pessoa);
            atual = atual.proximo;
        }
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return primeiro == null;
    }
}
