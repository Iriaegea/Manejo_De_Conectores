package Atcividad3;

import java.sql.*;

public class callableStatement_ejer1 {
    static void main() {
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String contraseña = "abc123.";
        int resultado;


        String consulta = "{CALL contarEmpleados()}";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña);
            CallableStatement cs = conn.prepareCall(consulta)){

            cs.execute(); // si uso executequery me da directamente el rs, pero si uso execute() tengo q usar getResultSet
            try(ResultSet rs = cs.getResultSet()){
                if(rs.next()){
                    resultado = rs.getInt("totalEmpleados"); // puedo poner un 1 para q me de la primera fila(solo hay una en este caso) o puedo ponerle esa palabra pero tengo q usar AS para q la pille.
                    System.out.println("El total de empleados es: " + resultado);
                }

            }



        }catch (SQLException e){
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}

/*
DELIMITER //
CREATE PROCEDURE contarEmpleados()
BEGIN
	SELECT COUNT(*) AS totalEmpleados FROM empleados;
END //

DELIMITER ;
*/
