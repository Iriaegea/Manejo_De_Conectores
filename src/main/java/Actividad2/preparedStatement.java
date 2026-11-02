package Actividad2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class preparedStatement {
    static void main() {
        String url = "";
        String usuario = "root";
        String contraseña = "abc123.";


        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
        preparedStatement ps = conn.prepareStatement(consulta)){

        }catch (SQLException e){
            System.err.println("Error de conexion "+ e.getMessage());
        }
    }
}
