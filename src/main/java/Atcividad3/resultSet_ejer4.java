package Atcividad3;
/*
 4. Recorrido bidireccional .
Consulta los empleados ordenados por EMP_NO utilizando un ResultSet de tipo
TYPE_SCROLL_INSENSITIVE. Desplaza el cursor al primer, último, anterior, siguiente y
tercer registro, mostrando los datos del empleado en cada posición .
* */

import java.sql.*;

public class resultSet_ejer4 {
    static void main() {


        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        String apellido;


        String consulta = "SELECT APELLIDO " +
                "FROM empleados " +
                "ORDER BY EMP_NO ";


        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement ps = conn.prepareStatement(consulta,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery(consulta)
        ){
            if(rs.first()){
                apellido = rs.getString("APELLIDO");
                System.out.println("Primero: " + apellido);
            }
            if(rs.last()){
                apellido = rs.getString("APELLIDO");
                System.out.println("Último: " + apellido);
            }
            if(rs.previous()){
                apellido = rs.getString("APELLIDO");
                System.out.println("Anterior: " + apellido);
            }
            if(rs.next()){
                apellido = rs.getString("APELLIDO");
                System.out.println("Siguiente: " + apellido);
            }
            if(rs.absolute(3)){
                apellido = rs.getString("APELLIDO");
                System.out.println("Tercero: " + apellido);
            }





        }catch(SQLException e){
            System.err.println("Error de conexion " + e.getMessage());
        }


    }
}
