import java.util.Arrays;

class Main {

    public static void main(String[] args) { //lista de textos recebidos do terminal

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

        try {
            FileManager gestor = new FileManager();

            int[] numeros = gestor.lerTexto(ficheiro, quantidade);

            System.out.println("Numeros lidos: "
                    + Arrays.toString(numeros));

        } catch (Exception e) {
            System.out.println("Erro ao ler ficheiro: " + e.getMessage());
        }
    }
}
