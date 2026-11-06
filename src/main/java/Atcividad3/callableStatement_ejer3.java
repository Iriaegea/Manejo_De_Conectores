package Atcividad3;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class callableStatement_ejer3 {
    static void main() {
        Scanner sc = new Scanner(System.in);
        /*
        Procedimiento con parámetro INOUT .
        Crea un procedimiento que reciba un salario mediante un parámetro INOUT, lo incremente
        un 5 % y devuelva el nuevo importe. Invócalo desde Java y muestra el resultado .
         */
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        BigDecimal salario;
        String consulta = "{CALL incrementarSalario(?)}";


        System.out.println("Escribe un salario: ");
        salario = sc.nextBigDecimal();

        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            CallableStatement cs = conn.prepareCall(consulta)){
            cs.setBigDecimal(1, salario);
            cs.registerOutParameter(1, Types.DECIMAL);
            cs.execute();
            salario = cs.getBigDecimal(1);

            if(salario == null){
                System.out.println("No se ha devuelto ningun salario");
            } else {
                System.out.println("Salario incrementado: " + salario);
            }


        }catch (SQLException e){
            System.err.println("Error de conexion: " + e.getMessage());
        }






    }
}


/*
* DELIMITER //
CREATE PROCEDURE incrementarSalario(INOUT salario Decimal(10,2))
BEGIN
	SET salario = salario + (salario * 0.05);
END //
DELIMITER ;
* */

