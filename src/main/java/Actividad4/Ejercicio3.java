package Actividad4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 /*
            Ejercicio 3. Recuperación parcial mediante savepoints
            Desarrolla un programa que realice las siguientes operaciones dentro de una única transacción:
            1. Insertar un cliente llamado Ana.
            2. Establecer un savepoint después de insertar a Ana.
            3. Intentar insertar un segundo cliente, Luis, utilizando el mismo email que Ana.
            4. Si se produce un error, mostrar su mensaje y SQLState. Si corresponde a una violación de
            integridad (clase 23), recuperar la transacción hasta el savepoint.
            5. Insertar un tercer cliente, Marta.
            6. Confirmar la transacción.
            Comprueba que los clientes Ana y Marta se han insertado correctamente, mientras que Luis no
            aparece en la base de datos.
            Si se produce un error inesperado que impida continuar de forma segura, realiza un rollback()
            completo.
*/

public class Ejercicio3 {
    static void main() {
        String usuario = "root";
        String contraseña = "abc123.";
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";


        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña)){


        }catch (SQLException e){
            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
        }




    }
}