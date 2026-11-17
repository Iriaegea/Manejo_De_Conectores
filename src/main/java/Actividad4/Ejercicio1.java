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

        String insertarPedido1 = "INSERT INTO  pedidos(PRODUCTO_NO, ?, UNIDADES, FECHAPEDIDO) VALUES(20, 106, 5, 1999-10-06- 00:00:00)";
        String insertarPedido2 = "INSERT INTO  pedidos(PRODUCTO_NO, ?, UNIDADES, FECHAPEDIDO) VALUES(20, 106, 5, 1999-11-06- 00:00:00";

        String insertarCliente= "INSERT INTO clientes(nombre, localidad, vendedor_no, debe, haber, limite_credito) VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(url, usuario, contraseña );
           ){
            conn.setAutoCommit(false);
           try(PreparedStatement ps = conn.prepareStatement(insertarCliente, Statement.RETURN_GENERATED_KEYS);
               ) {



               ps.setString(1, "cliente1");
               ps.setString(2, "su localidad");
               ps.setInt(3, 7499);
               ps.setBigDecimal(4, new BigDecimal(0));
               ps.setBigDecimal(5, new BigDecimal(0));
               ps.setInt(6, 2000);
               ps.executeUpdate();


               ps.setInt();
               ps.setInt();
               ps.executeUpdate(insertarPedido1);
               ps.executeUpdate(insertarPedido2);


               try (ResultSet rs = ps.getGeneratedKeys()) {
                   while (rs.next()) {
                       int idGenerado = rs.getInt(1);
                       System.out.println("El id generado es " + idGenerado);
                   }
               }

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


