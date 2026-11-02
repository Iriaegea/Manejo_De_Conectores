package Actividad2;

import java.sql.*;

public class statement {
    static void main() {
        String url = "jdbc:mysql://localhost:3306/empresa_ventas";
        String usuario = "root";
        String constraseña = "abc123.";

        int num_dep = 56;
        String nombre_dep = "INFORMATICA";
        String localidad_dep = "OURENSE";


        String consulta = "SELECT EMP_NO, APELLIDO, OFICIO, SALARIO FROM empleados";

        String consulta2= "INSERT INTO departamentos(DEP_NO, DNOMBRE, LOCALIDAD) VALUES("+num_dep +", '"+ nombre_dep +"', '"+ localidad_dep+"')";
        //Como es un statement normal y no un preparedStatement tengo q ponerle comillas simples rodeando los strings de la consulta

        String consulta3 = "UPDATE empleados SET SALARIO = SALARIO + SALARIO * 0.05";

        String consulta4 = "DELETE FROM empleados WHERE DEP_NO IS NULL";

        try(Connection conn = DriverManager.getConnection(url, usuario, constraseña);
            Statement stmt = conn.createStatement()){
            //LISTAR TODOS LOS EMPLEADOS:
            System.out.println("LISTAR TODOS LOS EMPLEADOS: \n");
            ResultSet rs = stmt.executeQuery(consulta);
            while (rs.next()){
                int numero = rs.getInt("EMP_NO");
                String apellido = rs.getString("APELLIDO");
                String oficio = rs.getString("OFICIO");
                double salario = rs.getDouble("SALARIO");
                System.out.println("Numero: " + numero + " Apellido: " + apellido + " Oficio: " + oficio + " Salario: " + salario);
            }


            //INSERTAR UN DEPARTAMENTO CON VALORES DIRECTOS:
            System.out.println("\nINSERTAR:\n");
            int insertados = stmt.executeUpdate(consulta2);
            System.out.println("Total registros insertados: " + insertados);


            //INCREMENTAR EL SALARIO DE TODOS LOS EMPLEADOS:
            int modificados = stmt.executeUpdate(consulta3);
            System.out.println("Total registros modificados " + modificados);

            //ELIMINAR EMPLEADOS SIN DEP_NO:
            int eliminados = stmt.executeUpdate(consulta4);
            System.out.println("Total eliminados: " + eliminados);

        }catch (SQLException e){
            System.err.println("Error de conexion " + e.getMessage());
        }
    }
}
