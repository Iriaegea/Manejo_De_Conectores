package Actividad2;

import org.h2.util.json.JsonConstructorUtils;

import java.sql.*;

public class ejercicio1 {
    static void main() {
        String url = "";
        String usuario = "";
        String constraseña = "";

        String consulta = "SELECT ";
        try(Connection conn = DriverManager.getConnection(url, usuario, constraseña);
            Statement stmt = conn.createStatement()){

            ResultSet infoEmpleados = stmt.executeQuery(consulta);
            while (infoEmpleados.next()){

            }

        }catch (SQLException e){
            System.err.println("Error de conexion " + e.getMessage());
        }
    }
}
