package org.example.Actvidad1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad1_ejercicio2 {
    static void main() {
        String url = "jdbc:h2:./data/instituto";
        String usuario = "sa";
        String contraseña ="";

        String crearTabla = "CREATE TABLE IF NOT EXISTS Ciclos(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nombre VARCHAR(100) NOT NULL," +
                "grado VARCHAR(50) CHECK (grado IN('medio', 'superior')))";
        String insertar1 = "INSERT INTO Ciclos(nombre, grado) VALUES('no se ', 'medio') ";
        String insertar2 = "INSERT INTO Ciclos(nombre, grado) VALUES('algo mas', 'superior')";

        String modificar = "UPDATE  Ciclos SET nombre = 'cambiado' WHERE grado = 'superior'";

        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
        Statement stmt = conn.createStatement()){
        stmt.executeUpdate(crearTabla);
        System.out.println("Tabla creada o ya existente");

        int insertado = stmt.executeUpdate(insertar1);
        if (insertado > 0 ){
            System.out.println("Insertado");
        }
        insertado = stmt.executeUpdate(insertar2);
        if (insertado > 0){
            System.out.println("insertado");
        }


        int total = stmt.executeUpdate(modificar);
            System.out.println("Numero de elementos modificados " + total);

        }catch (SQLException e){
            System.err.println(e.getMessage());
        }


    }



}
