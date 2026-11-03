/**
 * Elemento (no) de uma lista simplesmente ligada.
 * Cada no guarda um registo e uma referencia para o seguinte.
 */
class No {
    final Pessoa pessoa;
    No proximo;

    No(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.proximo = null;
    }
}
