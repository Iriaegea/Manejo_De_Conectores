package Actividad2;

import java.sql.*;
import java.util.Scanner;

public class preparedStatement_ejer7 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        int numeroEmpleado;
        double nuevaComision;


        System.out.println("Escribe el numero del empleado: ");
        numeroEmpleado = sc.nextInt();
        sc.nextLine();
        System.out.println("Escribe la nueva comisión: ");
        nuevaComision = sc.nextDouble();
        sc.nextLine();

        String consulta1 = "UPDATE  empleados SET COMISION = ? WHERE EMP_NO = ?";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            PreparedStatement ps = conn.prepareStatement(consulta1)){
            ps.setDouble(1, nuevaComision);
            ps.setInt(2, numeroEmpleado);
            int totalCambios = ps.executeUpdate();

            System.out.println("Total filas cambiadas: " + totalCambios);
        }catch (SQLException e){
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}
