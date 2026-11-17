package Actividad4;

import javax.swing.plaf.nimbus.State;
import java.math.BigDecimal;
import java.sql.*;


/*
Desarrolla un programa que realice las siguientes operaciones dentro de una única transacción:
1. Desactivar el modo auto - commit.
2. Insertar un cliente y recuperar su identificador generado automáticamente mediante
RETURN_GENERATED_KEYS.
3. Insertar dos pedidos asociados al cliente utilizando el identificador obtenido.
4. Confirmar la transacción si todas las operaciones se realizan correctamente.
5. Si se produce algún error, deshacer todas las operaciones mediante rollback().
Comprueba el comportamiento del programa provocando un error en el segundo pedido, por
ejemplo, utilizando un identificador de cliente inexistente. Verifica que tampoco se conserva el
cliente insertado.
*/
public class Ejercicio1 {
    static void main() {
        String usuario = "root";
        String contraseña = "abc123.";
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";

        String insertarPedido = "INSERT INTO  pedidos(PRODUCTO_NO, CLIENTE_NO, UNIDADES, FECHAPEDIDO) VALUES(?, ?, ?, ?)";



        String insertarCliente= "INSERT INTO clientes(nombre, localidad, vendedor_no, debe, haber, limite_credito) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña );
           ){
            conn.setAutoCommit(false);
           try(PreparedStatement ps = conn.prepareStatement(insertarCliente, Statement.RETURN_GENERATED_KEYS);
               PreparedStatement ps2 = conn.prepareStatement(insertarPedido)
               ) {



               ps.setString(1, "cliente1");
               ps.setString(2, "su localidad");
               ps.setInt(3, 7499);
               ps.setBigDecimal(4, new BigDecimal(0));
               ps.setBigDecimal(5, new BigDecimal(0));
               ps.setInt(6, 2000);
               ps.executeUpdate();


               int idGenerado = 0;

               try (ResultSet rs = ps.getGeneratedKeys()) {
                   if (rs.next()) {
                       idGenerado = rs.getInt(1);
                       System.out.println("El id generado es " + idGenerado);
                   } else{
                       throw new SQLException("Error al generar la clave");
                   }
               }

               ps2.setInt(1, 106 );
               ps2.setInt(2, idGenerado);
               ps2.setInt(3, 4);
               ps2.setString(4, "1999-10-06- 00:00:00");
               ps2.executeUpdate();
               //"INSERT INTO  pedidos(PRODUCTO_NO, CLIENTE_NO, UNIDADES, FECHAPEDIDO) VALUES(20, 106, 5, 1999-10-06- 00:00:00)"

               ps2.setInt(1, 106);
               ps2.setInt(2, 0);
               ps2.setInt(3, 4);
               ps2.setTimestamp(4, Timestamp.valueOf("1999-10-06 00:00:00"));
               ps2.executeUpdate();

               conn.commit();
               System.out.println("Cliente añadido");
           }catch(SQLException e){
               System.out.println("Error en la transacción " + e.getMessage());
               conn.rollback();
           }



        }catch (SQLException e){

            System.err.println("Error de conexión" + e.getMessage());

        }
    }
}



/*
CREAR BASE DE DATOS Y TABLAS:

CREATE DATABASE IF NOT EXISTS actividad_4;
USE actividad_4;

CREATE TABLE Cliente (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(50),
email VARCHAR(50) UNIQUE
);

CREATE TABLE Pedido (
id INT AUTO_INCREMENT PRIMARY KEY,
cliente_id INT,
descripcion VARCHAR(100),
FOREIGN KEY (cliente_id) REFERENCES Cliente(id)
);
*/


