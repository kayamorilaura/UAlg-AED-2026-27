/**
 * Representa um registo da lista.
 * Inicialmente contém apenas o nome; outros campos serão adicionados
 * depois de confirmar o formato dos dados fornecidos pelo docente.
 */
public class Pessoa {
    private final String nome;

    public Pessoa(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome nao pode estar vazio.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
