package Atcividad3;
/*
5. Modificar datos mediante ResultSet .
Utiliza un ResultSet actualizable (CONCUR_UPDATABLE) para incrementar un 10 % el
salario del primer empleado recuperado. Aplica los cambios directamente desde el
ResultSet .
*/

import java.math.BigDecimal;
import java.sql.*;

public class resultSet_ejer5 {
    static void main() {

        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        BigDecimal salario;



        String consulta = "SELECT EMP_NO, SALARIO FROM empleados";


        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement ps = conn.prepareStatement(consulta,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = ps.executeQuery(consulta)
        ) {
            if(rs.first()){
                salario = rs.getBigDecimal(1);
                System.out.println("Salario del primero: " + salario);

                salario = ( salario.multiply(new BigDecimal(1.10)));

                rs.updateBigDecimal(1, salario);
                rs.updateRow();


                System.out.println("Nuevo salario : " + salario);

            }
        }catch (SQLException e){
            System.err.println("Error de conexión " + e.getMessage());
        }
    }
}