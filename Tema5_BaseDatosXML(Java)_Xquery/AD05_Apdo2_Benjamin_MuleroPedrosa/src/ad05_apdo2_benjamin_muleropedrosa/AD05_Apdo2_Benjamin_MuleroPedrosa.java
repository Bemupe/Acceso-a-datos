/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad05_apdo2_benjamin_muleropedrosa;

import java.util.InputMismatchException;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import org.exist.xmldb.EXistResource;
import java.util.Scanner;

/**
 *
 * @author benmu
 */
public class AD05_Apdo2_Benjamin_MuleroPedrosa {
    
    private static String URI = "xmldb:exist://localhost:8081/exist/xmlrpc/db/tarea5_ejer2/biblioteca";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int año=0;
        boolean binario;
        Scanner teclado = new Scanner(System.in);               
        
        System.out.println("CONSULTAS Y MODIFICACIONES");
        System.out.println("--------------------------");
        System.out.println();
        //EJERCICIO 1
        System.out.println("1. Nº total de préstamos de cada libro");
        System.out.println("......................................");

        String xquery_1="for $prestamo in /prestamos/prestamo"+"\n"+
                        "for $libro in /libros/libro"+"\n"+
                        "where $libro/@id=$prestamo/codigoLibro"+"\n"+
                        "group by $d:=$prestamo/codigoLibro"+"\n"+ 
                        "return <libro>{$d/text()}, {$libro/nombre/text()}, NºPréstamos:{count($prestamo)}</libro>";        
        
        //Ejecución de conexión
        conexion(xquery_1);
        
        System.out.println();
        System.out.println();
        //EJERCICIO 2
        System.out.println("2. Listado de libros publicados desde el año 2005 en adelante y número de veces que haya sido prestado. Ordenar por nombre del autor.");
        System.out.println(".....................................................................................................................................");
        String xquery_2="for $prestamo in /prestamos/prestamo"+"\n"+
                        "for $libro in /libros/libro"+"\n"+
                        "where $libro/@id=$prestamo/codigoLibro and $libro/fechaPublicacion>2005"+"\n"+
                        "group by $d:=$prestamo/codigoLibro"+"\n"+ 
                        "order by $libro/autor"+"\n"+
                        "return <libro>{$libro/autor/text()}, {$libro/fechaPublicacion/text()}, NºPréstamos:{count($prestamo)}</libro>";       
        
        //Ejecución de conexión
        conexion(xquery_2);
        
        System.out.println();
        System.out.println();
        //EJERCICIO 3
        System.out.println("3. Actualiza el año de edición del libro cuyo id es 2./Lo actualizamos al año 2022");
        System.out.println("..................................................................................");
        //Implementación elección de año
        do{
            try{
                do{
                    binario=false;
                    System.out.println ();
                    System.out.println ("Introduzca un año (Entre 1991 y 2022/Incluidos):");
                    año=teclado.nextInt();
                }while(año<1990||año>2023);
            }catch(InputMismatchException e){
                System.err.println ("Error de lectura: no es un número entero válido."+"\n");
                teclado.nextLine(); 
                binario=true;
            }
        }while(binario);
        
        //xquery
        String xquery_3= "update replace"+"\n"+
                         "/libros/libro[@id=2]/fechaPublicacion with <fechaPublicacion>"+año+"</fechaPublicacion>";
        
        //Consulta de comprobación
        String xqueryConsu_3="for $libro in /libros/libro"+"\n"+
                             "where $libro/@id=2"+"\n"+
                             "return $libro";                                
        
        //Ejecución de conexiones
        conexion(xquery_3);
        conexion(xqueryConsu_3);
        
        System.out.println();
        System.out.println();
        //EJERCICIO 4
        System.out.println("4. Libros están prestados actualmente. Añade el elemento prestado con valor válido para el rango de libros prestados e inválido para el rango de libros devueltos.");
        System.out.println("..................................................................................................................................................................");
        
        //xquery
        String xquery_4= "for $libro in /libros/libro\n" +
                         "where $libro/disponible=\"no\"\n" +
                         "return (update insert <prestado>valido</prestado> into $libro), \n" +
                         "for $libro in /libros/libro\n" +
                         "where $libro/disponible=\"si\"\n" +
                         "return (update insert <prestado>invalido</prestado> into $libro)";
        
        //Consulta de comprobación
        String xqueryConsu_4= "for $libro in /libros/libro"+"\n"+
                              "return $libro";        
        
        //Ejecución de conexiones
        conexion(xquery_4); 
        conexion(xqueryConsu_4);
        
        System.out.println();
        System.out.println();
        //EJERCICIO 5
        System.out.println("5. Actualiza el elemento prestado creado en el paso anterior con los valores \"si\" para los libros que NO están disponibles y \"no\" para los libros que sí lo están .");
        System.out.println(".......................................................................................................................................................................");

        //Xquery y Consulta de comprobación
        String xquery_5_mas_Consu= "for $libro in /libros/libro\n" +
                                   "where $libro/prestado=\"valido\"\n" +
                                   "return (update replace $libro/prestado with <prestado>si</prestado>),\n" +
                                   "for $libro in /libros/libro\n" +
                                   "where $libro/prestado=\"invalido\"\n" +
                                   "return (update replace $libro/prestado with <prestado>no</prestado>),\n" +
                                   "for $libro in /libros/libro\n" +
                                   "return $libro";
        
        //Ejecución de conexiones
        conexion(xquery_5_mas_Consu);
        
        System.out.println();
        System.out.println();
        //EJERCICIO 6
        System.out.println("6. Actualiza el elemento prestado de todos los libros para que pase a llamarse \"enprestamo\".");
        System.out.println("..............................................................................................");

        //Xquery y Consulta de comprobación
        String xquery_6_mas_Consu= "for $libro in /libros/libro\n" +
                                   "where $libro/prestado=\"si\"\n" +
                                   "return (update replace $libro/prestado with <enprestamo>si</enprestamo>),\n" +
                                   "for $libro in /libros/libro\n" +
                                   "where $libro/prestado=\"no\"\n" +
                                   "return (update replace $libro/prestado with <enprestamo>no</enprestamo>),\n" +
                                   "for $libro in /libros/libro\n" +
                                   "return $libro";
        
        //Ejecución de conexiones
        conexion(xquery_6_mas_Consu);
        
        System.out.println();
        System.out.println();
        //EJERCICIO 7
        System.out.println("7. Borra un libro cuya fecha del último préstamo sea anterior al año 2020");
        System.out.println(".........................................................................");

        //Xquery y Consulta de comprobación
        String xquery_7_mas_Consu= "for $libro in /libros/libro\n" +
                                   "for $prestamo in /prestamos/prestamo\n" +
                                   "where $libro/@id=$prestamo/codigoLibro and $libro/enprestamo=\"no\" and $prestamo/fechaSalida<\"2020-01-01\" \n" +
                                   "group by $d:=$prestamo/codigoLibro\n" +
                                   "return  (update delete $prestamo, update delete $libro),\n" +
                                   "for $libro in /libros/libro\n" +
                                   "return  ($libro),\n" +
                                   "for $prestamo in /prestamos/prestamo\n" +
                                   "return  ($prestamo)";

        //Ejecución de conexiones
        conexion(xquery_7_mas_Consu);
    }
    
    
    //Método conexión
    public static void conexion(String xquery) {  
        
        final String driver = "org.exist.xmldb.DatabaseImpl";//Driver
        final String usuario="admin";//Usuario
        final String password="benjamin";//Password
        Collection col = null;
        
        try {
            //Cargar el driver en memoria
            Class cl = Class.forName(driver);
            
            //Creamos una instancia de la base de datos
            Database database = (Database) cl.newInstance();
            
            //Registramos la instancia
            DatabaseManager.registerDatabase(database);
            
            //Establecemos ruta (URI), usuario y password
            col = DatabaseManager.getCollection(URI, usuario, password);
             
            //Preparamos los recursos para utilizar los xquery.
            XPathQueryService xpqs = (XPathQueryService)col.getService("XPathQueryService", "1.0");
            xpqs.setProperty("indent", "yes");
            ResourceSet result = xpqs.query(xquery);
            ResourceIterator i = result.getIterator();
            Resource res = null;
            
            //Obtenemos los resultados
            while(i.hasMoreResources()) {
                try {
                    res = i.nextResource();
                    System.out.println(res.getContent());
                }catch(Exception b){
                    System.out.println("Error:"+b);
                }
                finally {
                    try { 
                        ((EXistResource)res).freeResources();
                    } catch(XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } catch(Exception c){
            System.out.println("Error:"+c);
        
        //Cerramos conexión
        } finally {
            if(col != null) {
                try { 
                    col.close(); 
                } catch(XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    
}
