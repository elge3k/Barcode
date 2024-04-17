package barcode.db;

import barcode.db.Database1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class database {

    public static void main(String[] args) {
        Database1 db = new Database1();
        Connection con = db.getConexion();

        if (con != null) {
            try {
                // Aquí puedes realizar operaciones con la base de datos
                String query = "SELECT * FROM tabla_prueba";
                PreparedStatement statement = con.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    // Accede a los datos de cada fila
                    int id = resultSet.getInt("id");
                    long codigoBarras = resultSet.getLong("codigo_barras");
                    // Hacer algo con los datos...
                    System.out.println("ID: " + id + ", Código de Barras: " + codigoBarras);
                }

                // No olvides cerrar los recursos
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("La conexión a la base de datos falló");
        }
    }
}

