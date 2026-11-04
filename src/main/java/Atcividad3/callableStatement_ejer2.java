package Atcividad3;

import java.sql.*;
import java.util.Scanner;

public class callableStatement_ejer2 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";

        int codigoDepartamento;
        double salarioMedio;

        System.out.println("Escribe el código de un depa: ");
        codigoDepartamento = sc.nextInt();
        sc.nextLine();

        String consulta = "{CALL salarioMedioDepartamento(?, ?)}";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             CallableStatement cs = conn.prepareCall(consulta)){

            cs.setInt(1, codigoDepartamento);
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.execute();
            try(ResultSet rs = cs.getResultSet()){
                if(rs.next()){
                    salarioMedio = rs.getDouble(1);
                    System.out.println("Salario medio = " + salarioMedio);
                }
            }



        }catch (SQLException e){
            System.err.println("Error de conexión" + e.getMessage());
        }
    }
}



/*
USE empresa_ventas;
DELIMITER //
CREATE PROCEDURE salarioMedioDepartamento(IN numeroDepa INT, OUT salarioMedio DECIMAL)
BEGIN
	SELECT AVG(SALARIO) INTO salarioMedio
    FROM empleados
    WHERE DEP_NO = numeroDepa;
END //
DELIMITER ;
 */