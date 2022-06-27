/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrearTablaListadoNotas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author benmu
 */
public class CrearTablaListadoNotas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            // Establecemos las variables necesarias
            String usuario="root";
            String clave="Benjamin";
            String url="jdbc:mysql://localhost:3306/alumnosbean";
            Connection con = null;
            Statement stmt=null;
            
            try 
            {//Cargamos el driver
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (Exception a)
                {
                    System.out.println(a.getMessage());
                }
                
                //Conectamos con la base de datos con el usuario "root" y la contraseña "Benjamin"    
                con = DriverManager.getConnection(url,usuario,clave);
                stmt = con.createStatement();
                 
                //Creamos la tabla
                System.out.println();
                System.out.println("CREAMOS LA TABLA NOTASFINALES");
                System.out.println("-----------------------------");
                stmt.executeUpdate("CREATE TABLE notasfinales (dni VARCHAR (9) NOT NULL, nombremodulo VARCHAR(60), curso VARCHAR(5), nota DOUBLE)ENGINE=InnoDB DEFAULT CHARSET=utf8;");
               
                //Insertamos los datos
                System.out.println();
                System.out.println("INSERTAMOS LOS DATOS");
                System.out.println("--------------------");
                
                String sisInfor = "INSERT INTO NOTASFINALES (dni, nombremodulo, curso, nota) VALUES('12345678A', 'Sistemas Informáticos', '21-22', 3.25);";
                String programacion = "INSERT INTO NOTASFINALES (dni, nombremodulo, curso, nota) VALUES('23456789B', 'Programación', '21-22', 4.75);";
                String desaInterfaces = "INSERT INTO NOTASFINALES (dni, nombremodulo, curso, nota) VALUES('14785236d', 'Desarrollo de interfaces', '21-22', 5.25);";
                String accesoDatos = "INSERT INTO NOTASFINALES (dni, nombremodulo, curso, nota) VALUES('96385274f', 'Acceso a datos', '21-22', 7.25);";
                
                stmt.executeUpdate(sisInfor);
                stmt.executeUpdate(programacion);
                stmt.executeUpdate(desaInterfaces);
                stmt.executeUpdate(accesoDatos);
                
                System.out.println("DNI: 12345678A, NOMBREMODULO:Sistemas Informáticos, CURSO:21-22, NOTA:3.25");
                System.out.println("DNI:23456789B, NOMBREMODULO:Programación, CUROS:21-22, NOTA:4.75");
                System.out.println("DNI:14785236d, NOMBREMODULO:Desarrollo de interfaces, CURSO:21-22, NOTA:5.25");
                System.out.println("DNI:96385274f, NOMBREMODULO:Acceso a Datos, CURSO:21-22, NOTA:7.25");                         
            
            } catch (SQLException ex)
            {
            System.out.println("Error: " + ex.getMessage());
            }
            finally
            {
                try 
                {
                con.close();//Cerramos la conexión
                stmt.close();//Cerramos statement.
                } catch (SQLException ex)
                {
                System.out.println("Error: " + ex.getMessage());
                }
            }
    }
    
}
