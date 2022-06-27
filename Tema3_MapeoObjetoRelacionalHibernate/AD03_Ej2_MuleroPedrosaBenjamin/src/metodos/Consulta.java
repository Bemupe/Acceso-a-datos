/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;


import static ad03_ej2_muleropedrosabenjamin.NewHibernateUtil.cerrarSessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author benmu
 */
public class Consulta {

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
        
        Statement stmt = con.createStatement();
        //String query = "select nom_emp, cod_depto, salario from empleado where  salario>1000";
        ResultSet result = stmt.executeQuery("select nom_emp, cod_depto, salario from empleado where  salario>1000");
        
        //Parametros de cada registro   
        String nombre;   
        String cod_depto;    
        String salario;
        
        //Sacamos por pantalla los resultados
        System.out.println ("\u001B[34mEMPLEADOS CON SALARIOS SUPERIOR A 1000€ ( ");
            //Se lee el registro
            while(result.next()) {
                //Introduzco cada resultado en su parametro y los saco por pantalla
                nombre=result.getString("nom_emp");//nom_emp=nom_emp(query)
                cod_depto=result.getString("cod_depto");
                salario=result.getString("salario");
                System.out.println ("  "+nombre+" "+cod_depto+" "+salario+" "); 
                System.out.println ("---------------------------------------");
                }
        System.out.println("\u001B[34m )");    
        
    } catch (InstantiationException ex) {        
        Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);     
    } catch (IllegalAccessException ex) {          
        Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);     
    }
    
}
   

}
