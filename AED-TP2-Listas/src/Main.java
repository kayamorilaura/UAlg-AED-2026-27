/**
 * Ponto de entrada provisório do TP2.
 * Demonstra somente a estrutura da lista; nao implementa ainda o enunciado.
 */
public class Main {
    public static void main(String[] args) {
        ListaLigada lista = new ListaLigada();

        lista.adicionar(new Pessoa("Ana"));
        lista.adicionar(new Pessoa("Bruno"));
        lista.adicionar(new Pessoa("Carla"));

        System.out.println("=== TP2 - Listas Ligadas (demonstracao inicial) ===");
        lista.mostrar();
        System.out.println("Total: " + lista.tamanho());
    }
}
