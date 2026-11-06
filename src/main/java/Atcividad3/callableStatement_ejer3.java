package Atcividad3;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            cs.setBigDecimal(salario);


        }catch (SQLException e){
            System.err.println("Error de conexion: " + e.getMessage());
        }






    }
}
