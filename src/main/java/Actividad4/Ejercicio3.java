package Actividad4;

import java.math.BigDecimal;
import java.sql.*;

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

        String insertarCliente ="INSERT INTO clientes(nombre, localidad, vendedor_no, debe, haber, limite_credito) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña)){
            conn.setAutoCommit(false);

            try(PreparedStatement ps = conn.prepareStatement(insertarCliente)){
                ps.setString(1, "Ana");
                ps.setString(2, "Localidad de ana");
                ps.setInt(3, 7499);
                ps.setBigDecimal(4, new BigDecimal(0));
                ps.setBigDecimal(5, new BigDecimal(0));
                ps.setBigDecimal(6, new BigDecimal("2000"));
                ps.executeUpdate();

                Savepoint sp = conn.setSavepoint("DespuesDeAna");

                try{

                    ps.setString(1, "Luis");
                    ps.setString(2, "Localidad de ana");
                    ps.setInt(3, 0); // fuerzo el fallo con un codigo q no existe
                    ps.setBigDecimal(4, new BigDecimal(0));
                    ps.setBigDecimal(5, new BigDecimal(0));
                    ps.setBigDecimal(6, new BigDecimal("2000"));
                    ps.executeUpdate();

                }catch (SQLException e){
                    System.err.println(e.getSQLState());
                    System.err.println(e.getMessage());


                    if(e.getSQLState() != null && e.getSQLState().startsWith("23")){
                        conn.rollback(sp);
                    } else {
                        throw e; // lanzar para q el de fuera haga el rollback
                    }

                }


                ps.setString(1, "Marta");
                ps.setString(2, "Localidad de ana");
                ps.setInt(3, 7499);
                ps.setBigDecimal(4, new BigDecimal(0));
                ps.setBigDecimal(5, new BigDecimal(0));
                ps.setBigDecimal(6, new BigDecimal("2000"));

                ps.executeUpdate();
                conn.commit(); // posicion del commit (antes lo tenía justo antes del último catch)
            }catch (SQLException e){
                conn.rollback();

            }



        }catch (SQLException e){

            System.err.println(e.getMessage());
            System.err.println(e.getSQLState());
        }




    }
}