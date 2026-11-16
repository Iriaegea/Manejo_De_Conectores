package Actividad3;

import java.sql.*;
import java.util.Scanner;

public class resultSet_ejer6 {
    /*
    6. Recuperar claves generadas .
        Crea una tabla categorias con un identificador autoincremental y un nombre. Inserta una
        categoría utilizando PreparedStatement y recupera el identificador generado mediante
        getGeneratedKeys() .
     */

    static void main() {


        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        String categoriaNueva = "";


        String crearTabla = "CREATE TABLE IF NOT EXISTS Categorias(" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nombre VARCHAR(100)" +
                ");";

        String insertarCategoria = "INSERT INTO Categorias(nombre) VALUES(?)";
        System.out.println("Qué categoría quieres insertar?: ");
        categoriaNueva = sc.nextLine();


        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(insertarCategoria, Statement.RETURN_GENERATED_KEYS)){

            st.executeUpdate(crearTabla);

            ps.setString(1, categoriaNueva);
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    int idGenerado = rs.getInt(1);
                    System.out.println("El id generado es: " + idGenerado);
                }
            }



        }catch(SQLException e){
            System.err.println("Error de conexión " + e.getMessage());

        };


    }
}
