/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad02_ej03_muleropedrosabenjamin;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author benmu
 */
public class AD02_Ej03_MuleroPedrosaBenjamin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
            // Establecemos las variables necesarias
            String usuario="root";
            String clave="Benjamin";
            String url="jdbc:mysql://localhost:3306/consultorait";
            Connection con = null;
            CallableStatement cst1=null;
            CallableStatement cst2=null;
            CallableStatement cst3=null;
            ResultSet rs_consulta_pro3=null;
            Statement stmt=null;
            boolean boole=false;
            int inferior=0;
            int superior=0;
            int departamento=0;
            int porcentaje=0;            
            Scanner teclado= new Scanner (System.in);
            
            try 
            {//Cargamos los drivers
                try 
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) 
                {
                    Logger.getLogger(AD02_Ej03_MuleroPedrosaBenjamin.class.getName()).log(Level.SEVERE, null, ex);
                }
       
            // Conecta con la base de datos con el usuario "root" y la contraseña "Benjamin"
            con = DriverManager.getConnection(url,usuario,clave);
            
            // Llamada a los procedimientos almacenados
            cst1 = con.prepareCall("{call intervaloComision (?,?,?)}");
            cst2 = con.prepareCall("{call intervaloSalario (?,?,?)}");
            cst3 = con.prepareCall("{call incrementarSalario (?,?)}");
            //PROCEDIMIENTO ALMACENADO_1
            do
            {
                do 
                {
                    System.out.println("\n");
                    System.out.println("PROCEDIMIENTO ALMACENADO 1");
                    System.out.println("--------------------------");
                    System.out.println("Establece el intervalo de comisiones, para conocer el número de trabajadores incluidos en él"+"\n");
                    try
                    {
                        do
                        {
                            boole=false;
                            System.out.println("Valor INFERIOR (Escribe un valor entero 0 y 20): ");       
                            inferior = teclado.nextInt();  
                            
                        } while(inferior<0||inferior>20);
                    } catch(InputMismatchException e)
                    {
                        System.err.printf ("Error de lectura: no es un número entero válido."+"\n");
                        teclado.nextLine(); 
                        boole=true;
                    }
                } while(boole);
                do
                {
                    try
                    {
                        do
                        {
                            boole=false;
                            System.out.println("Valor SUPERIOR (Escribe un valor entero mayor que el anterior y menor o igual a 20): ");       
                            superior = teclado.nextInt();
                        } while(superior<inferior||superior>20);
                    } catch(InputMismatchException e)
                    {
                        System.err.printf ("Error de lectura: no es un número entero válido."+"\n");
                        teclado.nextLine(); 
                        boole=true;
                    }
                } while(boole);     
   
                // Parametro 1 del procedimiento almacenado
                cst1.setInt(1, inferior);
                cst1.setInt(2, superior);
                
                // Definimos los tipos de los parametros de salida del procedimiento almacenado
                cst1.registerOutParameter(3, java.sql.Types.INTEGER);

                // Ejecuta el procedimiento almacenado
                cst1.execute();
                
                // Se obtienen la salida del procedimineto almacenado
                String n_empleados_comisiones_intervalo = cst1.getString(3);
                System.out.println("Número de empleados con comisiones establecidas según intervalo de "+inferior+" a "+superior+"): " + n_empleados_comisiones_intervalo+" Trabajador/es");
            } while (inferior < 0);
            
            
            //PROCEDIMIENTO ALMACENADO_2
            do
            {
                do 
                {
                    System.out.println("\n");
                    System.out.println("PROCEDIMIENTO ALMACENADO 2");
                    System.out.println("--------------------------");
                    System.out.println("Establece el intervalo de salarios, para conocer el número de trabajadores incluidos en la consultora"+"\n");
                    try
                    {
                        do
                        {
                            boole=false;
                            System.out.println("Valor INFERIOR (Escribe un salario (número entero) entre 1450 y 3000): ");       
                            inferior = teclado.nextInt();  
                            
                        } while(inferior<1450||inferior>3000);
                    } catch(InputMismatchException e)
                    {
                        System.err.printf ("Error de lectura: no es un número entero válido."+"\n");
                        teclado.nextLine(); 
                        boole=true;
                    }
                } while(boole);
                do
                {
                    try
                    {
                        do
                        {
                            boole=false;
                            System.out.println("Valor SUPERIOR (Escribe un salario (número entero) mayor que el anterior y menor o igual a 3000): ");       
                            superior = teclado.nextInt();
                        } while(superior<inferior||superior>3000);
                    } catch(InputMismatchException e)
                    {
                        System.err.printf ("Error de lectura: no es un número entero válido."+"\n");
                        teclado.nextLine(); 
                        boole=true;
                    }
                } while(boole);     
   
                // Parametro 1 del procedimiento almacenado
                cst2.setInt(1, inferior);
                cst2.setInt(2, superior);
                
                // Definimos los tipos de los parametros de salida del procedimiento almacenado
                cst2.registerOutParameter(3, java.sql.Types.INTEGER);

                // Ejecuta el procedimiento almacenado
                cst2.execute();
                
                // Se obtienen la salida del procedimineto almacenado
                String n_empleados_salario_intervalo = cst2.getString(3);
                System.out.println("Número de empleados en fecha de alta con intervalo de cuantía de salarios "+inferior+" a "+superior+"): " + n_empleados_salario_intervalo+" Trabajador/es");
            } while (inferior < 1450);
            
            
            //PROCEDIMIENTO ALMACENADO_3
            do
            {
                do 
                {
                    System.out.println("\n");
                    System.out.println("PROCEDIMIENTO ALMACENADO 3");
                    System.out.println("--------------------------");
                    System.out.println("Aumentar el salario de los empleados de un departamento en un porcentaje. "+"\n");
                    try
                    {
                        do
                        {
                            boole=false;
                            System.out.println("Selecciona (escribe el valor correspondiente) el departamento, al cual incrementar a sus trabajadores un porcentaje sus salarios"+"\n"+
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
                do
                {
                    try
                    {
                        do
                        {
                            boole=false;
                            System.out.println("Establece un porcentaje para incrementar a los trabajadores del departamento (De 0 a 50): ");       
                            porcentaje = teclado.nextInt();
                        } while(porcentaje<0||porcentaje>50);
                    } catch(InputMismatchException e)
                    {
                        System.err.printf ("Error de lectura: no es un número entero válido."+"\n");
                        teclado.nextLine(); 
                        boole=true;
                    }
                } while(boole);cst3.setInt(1, departamento);     
   
                // Parametro 1 del procedimiento almacenado
                cst3.setInt(1, departamento);
                cst3.setInt(2, porcentaje);
                
                // Definimos los tipos de los parametros de salida del procedimiento almacenado
                
                // Ejecuta el procedimiento almacenado
                cst3.execute();
                
                // Sacamos por pantalla el resultado de aplicar el procedimiento 3, a través de una consulta
                stmt = con.createStatement();
                rs_consulta_pro3=stmt.executeQuery("SELECT de.dnombre, em.apellido,em.salario FROM empleados em, departamentos de WHERE de.dept_no=em.dept_no AND em.dept_no="+departamento);
                rs_consulta_pro3.next();
                try
                {   System.out.println();
                    System.out.println("MODIFICACIÓN REALIZADA");
                    System.out.println("______________________");
                    do
                    {
                        System.out.println("\n"+"DEPARTAMENTO: "+rs_consulta_pro3.getString("dnombre")+"\n"
                                            +"Apellido: "+rs_consulta_pro3.getString("apellido")+"\n"
                                            +"Salario+"+porcentaje+"%: "+rs_consulta_pro3.getString("salario"));
                        System.out.println("------------------------------------");
                    }while(rs_consulta_pro3.next());
                }catch(SQLException ex)
                {
                    System.out.println("Error consulta del procedimiento3: " + ex.getMessage());
                }
                finally
                {
                    rs_consulta_pro3.close();//Cerramos consulta del procedimiento 3
                    stmt.close();//Cerramos smt
                    
                }
            } while (inferior < 0);
            } catch (SQLException ex) 
            {
                System.out.println("Error : " + ex.getMessage());
            }
            finally
            {
                try 
                {   cst3.close();//Cerramos en orden inverso a la apertura, procedimiento3, procedimiento2, procedimiento1 y conexión con base de datos
                    cst2.close();
                    cst1.close();
                    con.close();
                } catch (SQLException ex) 
                {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
    }
}
