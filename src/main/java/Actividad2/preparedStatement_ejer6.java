package Actividad2;

import java.sql.*;
import java.util.Scanner;

public class preparedStatement_ejer6 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        String nombreDepartamento ;

        System.out.println("Escribe el nombre de un departamento: ");
        nombreDepartamento= sc.nextLine().trim();

        String consulta = "SELECT DEP_NO FROM departamentos WHERE DNOMBRE = ?";
        String consulta2 = "SELECT * FROM empleados WHERE DEP_NO = ?";

        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement ps = conn.prepareStatement(consulta)){
            ps.setString(1, nombreDepartamento);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int codigo = rs.getInt("DEP_NO");
                try( PreparedStatement ps2 = conn.prepareStatement(consulta2)){
                    ps2.setInt(1, codigo);

                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        System.out.println(rs2.getString("APELLIDO"));
                    }
                }

            } else {
                System.out.println("No se encontró el departamento");
            }





        }catch(SQLException e){
            System.err.println("Error de conexión " + e.getMessage());
        }
    }
}
