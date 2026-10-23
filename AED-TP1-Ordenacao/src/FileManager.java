
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileManager {

    // ESCREVER FICHEIRO DE TEXTO
    public void escreverTexto(String caminho, int[] numeros)
            throws IOException {

        // Cria o ficheiro de saída
        try (PrintWriter escritor = new PrintWriter(caminho)) {

            // Percorre todos os elementos do array
            for (int i = 0; i < numeros.length; i++) {
                escritor.println(numeros[i]);
            }
        }
    }

    // ESCREVER FICHEIRO BINÁRIO
    public void escreverBinario(String caminho, int[] numeros)
            throws IOException {

        // Abre um ficheiro binário para escrita
        try (DataOutputStream escritor =
                new DataOutputStream(new FileOutputStream(caminho))) {

            // Percorre os números do array
            for (int i = 0; i < numeros.length; i++) {

                // Cada inteiro ocupa quatro bytes
                escritor.writeInt(numeros[i]);
            }
        }
    }

    // LER FICHEIRO DE TEXTO CONFIRMANDO SE EXISTE E SE TEM A QUANTIDADE DE INTEIROS SOLICITADA
    
public int[] lerTexto(String caminho, int quantidade)
        throws FileNotFoundException {

    int[] numeros = new int[quantidade];

    try (Scanner scanner = new Scanner(new File(caminho))) {

        for (int i = 0; i < quantidade; i++) {

            // Verifica se existe outro inteiro para ler
            if (!scanner.hasNextInt()) {
                throw new IllegalArgumentException(
                    "O ficheiro tem menos inteiros do que o esperado "
                    + "ou contem dados invalidos."
                );
            }

            numeros[i] = scanner.nextInt();
        }

        // Verifica se existem dados adicionais
        if (scanner.hasNext()) {
            throw new IllegalArgumentException(
                "O ficheiro contem mais dados do que o esperado."
            );
        }
    }

    return numeros;
}


    // LER FICHEIRO BINÁRIO
    
public int[] lerBinario(String caminho, int quantidade)
        throws IOException {

    File ficheiro = new File(caminho);

    // Cada inteiro ocupa 4 bytes
    long tamanhoEsperado = (long) quantidade * Integer.BYTES;

    if (ficheiro.length() != tamanhoEsperado) {
        throw new IOException(
            "Tamanho incorreto: esperado "
            + tamanhoEsperado + " bytes, encontrado "
            + ficheiro.length() + " bytes."
        );
    }

    int[] numeros = new int[quantidade];

    try (DataInputStream leitor =
            new DataInputStream(new FileInputStream(ficheiro))) {

        for (int i = 0; i < quantidade; i++) {
            numeros[i] = leitor.readInt();
        }
    }

    return numeros;
}

}
