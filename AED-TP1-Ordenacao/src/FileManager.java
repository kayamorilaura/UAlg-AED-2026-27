import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileManager{

    public int[] lerTexto(String caminho, int quantidade)
            throws FileNotFoundException {

        int[] numeros = new int[quantidade]; //Cria um array com o tamanho solicitado.

        try (Scanner scanner = new Scanner(new File(caminho))) { 

            for (int i = 0; i < quantidade; i++) {
                numeros[i] = scanner.nextInt(); //Lê os números um a um
            }
        }

        return numeros;
    }
}