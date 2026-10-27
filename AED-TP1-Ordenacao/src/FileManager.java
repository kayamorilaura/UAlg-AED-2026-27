
import java.io.*;
import java.util.Scanner;

public class FileManager {

    // =====================================================
    // 1. LER FICHEIRO DE TEXTO
    // =====================================================

    public int[] lerTexto(String caminho, int quantidade)
            throws IOException {

        // Criamos um vetor com a quantidade solicitada.
        int[] numeros = new int[quantidade];

        // Abrimos o ficheiro usando Scanner.
        // O try fecha automaticamente o ficheiro.
        try (Scanner scanner = new Scanner(
                new File(caminho), "UTF-8")) {

            for (int i = 0; i < quantidade; i++) {

                // Verificamos se existe um inteiro para ler.
                if (!scanner.hasNextInt()) {
                    throw new IOException(
                        "Faltam inteiros validos na posicao " + i
                    );
                }

                // Guardamos o inteiro no vetor.
                numeros[i] = scanner.nextInt();
            }

            // Verificamos se existem dados adicionais.
            if (scanner.hasNext()) {
                throw new IOException(
                    "O ficheiro contem dados a mais ou invalidos."
                );
            }
        }

        return numeros;
    }


    // =====================================================
    // 2. ESCREVER FICHEIRO DE TEXTO
    // =====================================================

    public void escreverTexto(String caminho, int[] numeros)
            throws IOException {

        // PrintWriter permite escrever texto no ficheiro.
        try (PrintWriter escritor = new PrintWriter(
                new BufferedWriter(new FileWriter(caminho)))) {

            // Escrevemos um inteiro por linha.
            for (int numero : numeros) {
                escritor.println(numero);
            }

            // Verificamos se ocorreu algum erro na escrita.
            if (escritor.checkError()) {
                throw new IOException(
                    "Erro ao escrever ficheiro de texto."
                );
            }
        }
    }


    // =====================================================
    // 3. LER FICHEIRO BINARIO
    // =====================================================

    public int[] lerBinario(String caminho, int quantidade)
            throws IOException {

        File ficheiro = new File(caminho);

        // Cada int de Java ocupa 4 bytes.
        // O tamanho deve corresponder à quantidade pedida.
        long tamanhoEsperado = (long) quantidade * Integer.BYTES;

        if (ficheiro.length() != tamanhoEsperado) {
            throw new IOException(
                "Tamanho binario invalido. Esperados "
                + tamanhoEsperado + " bytes."
            );
        }

        int[] numeros = new int[quantidade];

        // DataInputStream permite ler inteiros em binario.
        try (DataInputStream entrada = new DataInputStream(
                new BufferedInputStream(
                    new FileInputStream(ficheiro)))) {

            for (int i = 0; i < quantidade; i++) {

                // readInt() lê os 4 bytes de cada inteiro.
                numeros[i] = entrada.readInt();
            }
        }

        return numeros;
    }


    // =====================================================
    // 4. ESCREVER FICHEIRO BINARIO
    // =====================================================

    public void escreverBinario(String caminho, int[] numeros)
            throws IOException {

        // DataOutputStream permite escrever inteiros binarios.
        try (DataOutputStream saida = new DataOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(caminho)))) {

            for (int numero : numeros) {

                // writeInt() guarda um inteiro em 4 bytes.
                saida.writeInt(numero);
            }
        }
    }
}
