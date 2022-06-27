/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import static ad03_ej2_muleropedrosabenjamin.NewHibernateUtil.cerrarSessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//Insertamos el empleado señalado a través de una sentencia SQL con conexión JDBC.
public class Insertar {
 
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        
        //Cierro la sesión que se haya podido iniciar con Hibernate, 
        //ya que para insertar utilizaremos la misma clave y el mismo usuario.
        cerrarSessionFactory ();
        
        //Establezco los parámetros necesario para la conexión
        String sDriver = "com.mysql.jdbc.Driver";
        String sURL = "jdbc:mysql://localhost:3306/proyectosx?zeroDateTimeBehavior=convertToNull";
        Connection con = null;
        
        //Introduzco todo el proceso en un try-catch para coger las posibles excepciones.
        try {

            Class.forName(sDriver).newInstance();
            con = DriverManager.getConnection(sURL,"root","Benjamin");
            PreparedStatement stmt;
            //INSERT INTO empleado VALUES (cod_emp,nom_emp,fecha_ingreso,salario,cod_jefe,cod_depto)
            stmt = con.prepareStatement("INSERT INTO empleado VALUES (?,?,?,?,?,?)");
            
            //Establezco los parámetros para introducir los datos en la base de datos
            String cod_emp="A22";
            String nom_emp="Pepe García";
            Date fecha_ingreso=new Date();
            long timeInMilliSeconds = fecha_ingreso.getTime();
            java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
            float salario=2000;
            String cod_jefe="A11";
            String cod_depto="01";
            
            //Añado cada parámetro en su lugar corespondiente.
            stmt.setString(1,cod_emp);
            stmt.setString(2,nom_emp);
            stmt.setDate(3,date1);
            stmt.setFloat(4, salario);
            stmt.setString(5, cod_jefe);
            stmt.setString(6, cod_depto);
            stmt.executeUpdate();
        } catch (InstantiationException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
 
}
