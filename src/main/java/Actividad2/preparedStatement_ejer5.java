package Actividad2;

import java.sql.*;
import java.util.Scanner;

public class preparedStatement_ejer5 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        int  numeroEmpleado, numeroDepa;
        String apellido, oficio;
        double salario;


        System.out.println("Numero del empleado: ");// usar uno q no este usado
        numeroEmpleado = sc.nextInt();
        sc.nextLine();
        System.out.println("Apellido: ");
        apellido = sc.nextLine();
        System.out.println("Oficio: ");
        oficio = sc.nextLine();
        System.out.println("Salario: ");
        salario = sc.nextDouble();
        sc.nextLine();
        System.out.println("Numero de departamento: ");
        numeroDepa = sc.nextInt(); // usar 10 q ya existe
        sc.nextLine();







        String consulta ="INSERT INTO empleados(EMP_NO, APELLIDO, OFICIO, SALARIO, DEP_NO) VALUES(?, ?, ?, ?, ?)";





        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
        PreparedStatement ps = conn.prepareStatement(consulta)){
            ps.setInt(1, numeroEmpleado);
            ps.setString(2, apellido);
            ps.setString(3, oficio);
            ps.setDouble(4, salario);
            ps.setInt(5, numeroDepa);
            int numeroEngadidas  = ps.executeUpdate();// esta vez no se le pasa el String en los paréntesis
            System.out.println("Engadidas: " + numeroEngadidas);






        }catch (SQLException e){
            System.err.println("Error de conexion "+ e.getMessage());
        }
    }
}
