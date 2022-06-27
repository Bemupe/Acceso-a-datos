/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad02_mulero_pedrosa_benjamin;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author benmu
 */
public class AD02_Mulero_Pedrosa_Benjamin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
       
            // Establecemos las variables necesarias
            String usuario="root";
            String clave="Benjamin";
            String url="jdbc:mysql://localhost:3306/consultorait";
            Connection con = null;
            Statement stmt=null;
            ResultSet rs_consulta1, rs_consulta2, rs_consulta3;
            boolean boole=false;
            int departamento=0;
            Scanner teclado= new Scanner (System.in);
            
            try 
            {//Cargamos los drivers
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex)
                {
                    Logger.getLogger(AD02_Mulero_Pedrosa_Benjamin.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            //Conectamos con la base de datos con el usuario "root" y la contraseña "Benjamin"    
                con = DriverManager.getConnection(url,usuario,clave);
                stmt = con.createStatement();
                
                
                //CONSULTA_1
                rs_consulta1=stmt.executeQuery("SELECT DE.DNOMBRE, EM.APELLIDO FROM DEPARTAMENTOS DE, EMPLEADOS EM WHERE DE.DEPT_NO=EM.DEPT_NO");
                rs_consulta1.next();
                System.out.println();
                System.out.println("CONSULTA 1");
                System.out.println("----------");
                try
                {
                    do 
                    {
                        System.out.println("Apellido trabajador: "+rs_consulta1.getString("apellido")+"\n"
                                            +"Departamento: "+rs_consulta1.getString("dnombre"));
                        System.out.println("------------------------------------");
                    } while(rs_consulta1.next());
                }catch(SQLException ex)
                {
                    System.out.println("Error consulta1: " + ex.getMessage());
                }
                finally
                {
                    rs_consulta1.close();//Cerramos consulta1 
                }   
                
                
                //CONSULTA_2
                rs_consulta2=stmt.executeQuery("SELECT de.dnombre, count(em.dept_no) AS nºtrabajadores, sum(em.salario) AS presupuesto FROM departamentos de, empleados em WHERE de.dept_no=em.dept_no group by de.dnombre");
                rs_consulta2.next();
                System.out.println("\n");
                System.out.println("CONSULTA 2");
                System.out.println("----------");
                try
                {
                    do
                    {
                        System.out.println("Departamento: "+rs_consulta2.getString("dnombre")+"\n"
                            +"NºTrabajadores: "+rs_consulta2.getString("nºtrabajadores")+"\n"
                            +"Presupuesto: "+rs_consulta2.getString("presupuesto")+" Euros");
                        System.out.println("-------------------------");
                    }while(rs_consulta2.next());
                
                }catch(SQLException ex)
                {
                    System.out.println("Error: consulta2 " + ex.getMessage());
                }
                finally
                {
                    rs_consulta2.close();//Cerramos consulta2 
                }   

                //CONSULTA_3
                System.out.println("\n");
                System.out.println("CONSULTA 3");
                System.out.println("----------");
                rs_consulta3=null;
                try
                {
                    do  //Hasta no introducir los datos correctos el programa no continúa. 
                        //Para ello, hemos creado búcles y la captura de excepción en el caso de introducir otros caracteres. 
                        //Utilizamos búcles y variable booleana.
                    {
                        try
                        {
                            do
                            {
                                boole=false;
                                System.out.println("Selecciona (escribe el valor correspondiente) el departamento, al cual incrementar a sus trabajadores un 10% sus salarios"+"\n"+
                                                    "10= Sistemas y seguridad"+"\n"+
                                                    "20= Desarrollo"+"\n"+
                                                    "30= Pruebas"+"\n"+
                                                    "40= Dirección"+"\n"+
                                                    "50= Ventas");
                                System.out.print(": ");
                                departamento = teclado.nextInt();
                            } while(departamento!=10&&departamento!=20&&departamento!=30&&departamento!=40&&departamento!=50);
                        } catch(InputMismatchException e)
                        {
                            System.err.printf ("Error de lectura: no es un número entero válido."+"\n");
                            teclado.nextLine();
                            boole=true;
                        }
                    } while(boole);
                    rs_consulta3=stmt.executeQuery("SELECT de.dnombre, em.apellido, em.salario*0.1 AS incre_sala, em.salario*1.1 AS sala_mas_10por FROM empleados em, departamentos de WHERE de.dept_no=em.dept_no AND em.dept_no= "+departamento);
                    rs_consulta3.next();
                    do
                    {
                //Sacamos por consola los resultados de la consulta3    
                        System.out.println("DEPARTAMENTO: "+rs_consulta3.getString("dnombre"));
                        System.out.println("Apellido: "+rs_consulta3.getString("apellido")+"\n"
                                       +"Incremento 10%: "+rs_consulta3.getString("incre_sala")+"\n"
                                       +"Salario+10%: "+rs_consulta3.getString("sala_mas_10por")+" Euros");
                        System.out.println("-------------------------------");
                    }while(rs_consulta3.next());
                }catch(SQLException ex)
                {
                    System.out.println("Error consulta3: " + ex.getMessage());
                }
                finally
                {
                    rs_consulta3.close();//Cerramos consulta3 
                }
            
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
