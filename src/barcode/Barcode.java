package barcode;

import barcode.db.Database1;
import com.itextpdf.text.pdf.Barcode39;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class Barcode {

    public static void main(String[] args) {
        // Crear objeto para introducir el número de barras
        IntroducirNumeroDeBarras introducir = new IntroducirNumeroDeBarras();
        introducir.introducirNumeroDeBarras();
        String numeroDeBarras = introducir.getNumeroDeBarras();

        // Generar el código de barras
        String codigoBarras = generarCodigoBarras(numeroDeBarras);

        // Guardar el código de barras en la base de datos
        guardarCodigoBarrasEnBaseDeDatos(codigoBarras);

        // Generar la imagen del código de barras
        try {
            generarImagenCodigoBarras(codigoBarras, "codigo_barras.png");
            System.out.println("Imagen del código de barras generada exitosamente.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String generarCodigoBarras(String numeroDeBarras) {
        Barcode39 code = new Barcode39();
        code.setCode(numeroDeBarras);
        return code.getCode();
    }

    private static void guardarCodigoBarrasEnBaseDeDatos(String codigoBarras) {
        Database1 db = new Database1();
        Connection con = db.getConexion();

        if (con != null) {
            try {
                String query = "INSERT INTO tabla_prueba (codigo_barras) VALUES (?)";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, codigoBarras);
                statement.executeUpdate();
                statement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("La conexión a la base de datos falló");
        }
    }

    static void generarImagenCodigoBarras(String codigoBarras, String archivoImagen) throws IOException {
        // Generar la imagen del código de barras
        int width = 400;
        int height = 100;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();

        // Dibujar el código de barras
        Barcode39 code = new Barcode39();
        code.setCode(codigoBarras);
        java.awt.Image imgBarcode = code.createAwtImage(java.awt.Color.BLACK, java.awt.Color.WHITE);
        g2d.drawImage(imgBarcode, 0, 0, width, height, null);
        g2d.dispose();

        // Guardar la imagen en el escritorio
        File file = new File(System.getProperty("user.home"), archivoImagen);
        ImageIO.write(img, "png", file);
    }
}





