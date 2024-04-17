package barcode;

import java.util.Scanner;

public class IntroducirNumeroDeBarras {
    private String numeroDeBarras;

    public void introducirNumeroDeBarras() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el número de barras:");
        this.numeroDeBarras = scanner.nextLine();
        scanner.close();
    }

    public String getNumeroDeBarras() {
        return numeroDeBarras;
    }
}
