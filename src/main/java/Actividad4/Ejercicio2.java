package Actividad4;

import java.math.BigDecimal;
import java.sql.*;

public class Ejercicio2 {

    /*
    Ejercicio 2. Inserción de clientes por lotes
    Desarrolla un programa que inserte cinco clientes utilizando PreparedStatement y procesamiento
    por lotes.
    1. Desactivar el modo auto - commit.
    2. Añadir las cinco inserciones al lote mediante addBatch().
    3. Ejecutar el lote utilizando executeBatch().
    4. Mostrar el resultado de la ejecución.
    5. Confirmar la transacción si todas las inserciones se realizan correctamente.
    Modifica uno de los clientes para que tenga el mismo email que otro e intenta ejecutar
    nuevamente el lote.
    Si se produce un error, muestra el mensaje y el SQLState, y realiza un rollback() completo.
    Comprueba que no se ha conservado ninguna inserción del lote fallido.
     */
    static void main() {
        String usuario = "root";
        String contraseña = "abc123.";
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";

        String insertarCliente = "INSERT INTO clientes(nombre, localidad, vendedor_no, debe, haber, limite_credito) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña)){
            conn.setAutoCommit(false);

            try(PreparedStatement ps = conn.prepareStatement(insertarCliente)){

                for (int i = 1; i <= 5; i++) {
                    ps.setString(1, "cliente" + i);
                    ps.setString(2, "su localidad");
                    ps.setInt(3, 7499);
                    ps.setBigDecimal(4, BigDecimal.ZERO);
                    ps.setBigDecimal(5, BigDecimal.ZERO);
                    ps.setBigDecimal(6, new BigDecimal("2000"));

                    ps.addBatch();
                }
                //NTO clientes(nombre, localidad, vendedor_no, debe, haber, limite_credito) VALUES( client5, su localidad, 7499, 0, 0, 2000)");


                int[] resultados = ps.executeBatch();
                for (int filas : resultados){
                    if (filas == Statement.SUCCESS_NO_INFO) {
                        System.out.println("Operación correcta, número de filas desconocido");
                    } else {
                        System.out.println("Filas afectadas: " + filas);
                    }
                }
            }catch (SQLException e){
                conn.rollback();
                throw e;
            }


            conn.commit();
        }catch (SQLException e){
            System.err.println("Error "+ e.getMessage() );
            System.err.println("SQLstate: " + e.getSQLState());

        }
    }
}
