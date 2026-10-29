package org.example.Actvidad1;
//jdbc:h2:mem:mi_base

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Actividad1_ejercicio1 {


    void main() {

        String url = "jdbc:h2:mem:testdb";
        String usuario = "sa";
        String contraseña = "";


        String sql = "CREATE TABLE IF NOT EXISTS Alumnos  (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nombre VARCHAR(100) NOT NULL," +
                "edad INT);";

        String insertar1 = "INSERT INTO Alumnos(nombre, edad) VALUES('Iria', 22)";
        String insertar2 = "INSERT INTO Alumnos(nombre, edad) VALUES('Lorena', 20)";
        String insertar3 = "INSERT INTO Alumnos(nombre, edad) VALUES('Helen', 24)";
        String eliminarRegistros = "DELETE FROM Alumnos";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conexion.createStatement()) {
            int alteradas;

            stmt.executeUpdate(sql);
            System.out.println("Tabla creada correctamente");

            alteradas = stmt.executeUpdate(insertar1);
            if (alteradas > 0) {
                System.out.println("Insertado el primero");
            } else {
                System.out.println("No se ha insertado el primero");
            }

            alteradas = stmt.executeUpdate(insertar2);
            if (alteradas > 0) {
                System.out.println("Insertado el segundo");
            } else {
                System.out.println("No se ha insertado el segundo");
            }

            alteradas = stmt.executeUpdate(insertar3);
            if (alteradas > 0) {
                System.out.println("Insertada tercera");
            } else {
                System.out.println("Insertada tercera");
            }

            int borradas = stmt.executeUpdate(eliminarRegistros);
            System.out.println(" Se han eliminado " + borradas + " registros");


        } catch (SQLException e) {
            System.err.println("Error de conexión " + e.getMessage());
        }
    }
}