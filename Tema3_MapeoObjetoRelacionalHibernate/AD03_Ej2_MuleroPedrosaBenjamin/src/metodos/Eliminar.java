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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benmu
 */
public class Eliminar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

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
            stmt = con.prepareStatement("DELETE FROM empleado WHERE nom_emp='Pepe García'");
           
            stmt.executeUpdate();
        } catch (InstantiationException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
