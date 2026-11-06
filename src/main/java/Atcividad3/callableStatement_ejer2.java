package Atcividad3;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class callableStatement_ejer2 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        double salarioMedioDouble;
        int codigoDepartamento;
        BigDecimal salarioMedio;

        System.out.println("Escribe el código de un depa: ");
        codigoDepartamento = sc.nextInt();
        sc.nextLine();

        String consulta = "{CALL salarioMedioDepartamento(?, ?)}";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             CallableStatement cs = conn.prepareCall(consulta)){

            cs.setInt(1, codigoDepartamento);
            cs.registerOutParameter(2, Types.DECIMAL);
            cs.execute();
             //NO TENGO Q USAR UN RS PQ YA TENGO UN PARAMETRO DE SALIDA
            salarioMedio = cs.getBigDecimal(2);

            if (salarioMedio == null){
                System.out.println("No se ha devuelto ningun salario");
            } else{
                salarioMedioDouble = salarioMedio.doubleValue();
                System.out.println("Salario medio = " + salarioMedioDouble);
            }





        }catch (SQLException e){
            System.err.println("Error de conexión" + e.getMessage());
        }
    }
}



/*
USE empresa_ventas;
DELIMITER //
CREATE PROCEDURE salarioMedioDepartamento(IN numeroDepa INT, OUT salarioMedio DECIMAL (10,2))
BEGIN
	SELECT AVG(SALARIO) INTO salarioMedio
    FROM empleados
    WHERE DEP_NO = numeroDepa;
END //
DELIMITER ;
 */