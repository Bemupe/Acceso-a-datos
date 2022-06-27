/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba_listado_notas;
import Alumno.NotasBean;

/**
 *
 * @author usuario
 */
public class Prueba_listado_notas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AccedeBD gestion = new AccedeBD();
        
        System.out.println("LISTADO INICIAL");
        System.out.println("---------------");
        gestion.listado();
        gestion.anade();
        
        AccedeBD gestion2 = new AccedeBD();
        System.out.println();
        System.out.println("LISTADO FINAL");
        System.out.println("-------------");
        gestion2.listado();
    }

}
