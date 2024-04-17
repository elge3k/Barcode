
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class IntroducirNombreImagen {
    private String nombreImagen;
    private BufferedReader reader;

    public void introducirNombreImagen() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Introduce el nombre de la imagen:");
            this.nombreImagen = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void cerrarBufferedReader() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}