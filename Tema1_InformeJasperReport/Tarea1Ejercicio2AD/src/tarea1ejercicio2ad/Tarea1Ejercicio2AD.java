/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1ejercicio2ad;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.* ;
import net.sf.jasperreports.engine.* ;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author benmu
 */
public class Tarea1Ejercicio2AD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        String reportSource="./reports/templates/Reporte.jrxml";
        String reportDest="./reports/results/Reporte.html";
        
        Map<String, Object> params=new HashMap<String, Object>();
        
        params.put("reportTitle", "LISTADO DE CLIENTES");
        params.put("author", "Benjamín Mulero Pedrosa");
        params.put("starDate", (new java.util.Date()).toString());
        
        try
        {
        //Compilar la plantilla
            JasperReport jasperReport=
                JasperCompileManager.compileReport (reportSource);
        
        //Habilitar el driver necesario.
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        
        //Crear una conexión para pasar el informe
        java.sql.Connection conn=DriverManager.getConnection(
                "jdbc:derby://localhost:1527/sample","app","app");
        
        //Sustituir el parámetro datasource JR vacío por 
        //el parámentro de conexión conn.
        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, params, conn);
        
        //Exportar HTML
        JasperExportManager.exportReportToHtmlFile(jasperPrint, reportDest);
        
        //Ver el informe
        JasperViewer.viewReport (jasperPrint);
        }
        catch (JRException ex)
        {
            System.out.println(ex.getMessage());
                
                }
    }
}

