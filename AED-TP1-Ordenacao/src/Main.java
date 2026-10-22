public class Main {

    public static void main(String[] args) {

        System.out.println("=== TP1 - Ordenacao de Inteiros ===");

        if (args.length != 2) {
            System.out.println("Uso: java Main <ficheiro> <quantidade>");
            return;
        }

        String ficheiro = args[0];
        int quantidade;

        try {
            quantidade = Integer.parseInt(args[1]);
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
    }
}
