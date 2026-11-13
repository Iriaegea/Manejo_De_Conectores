package Atcividad3;

import java.math.BigDecimal;
import java.sql.*;

public class resultSet_ejer6 {
    /*
    6. Recuperar claves generadas .
        Crea una tabla categorias con un identificador autoincremental y un nombre. Inserta una
        categoría utilizando PreparedStatement y recupera el identificador generado mediante
        getGeneratedKeys() .
     */

    static void main() {

        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";

        String crearTabla = "CREATE TABLE Categorias(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nombre VARCHAR(100)" +
                ");";


        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement ps = conn.prepareStatement(consulta,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery(consulta)){

        }catch(SQLException e){
            System.err.println("Error de conexión " + e.getMessage());

        };


    }
}
