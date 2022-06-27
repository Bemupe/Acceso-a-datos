/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package prueba_listado_notas;
import Alumno.NotasBean;
import Alumno.NotasBean.BDModificadaEvent;
import Alumno.NotasBean.BDModificadaListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class AccedeBD implements BDModificadaListener{

    NotasBean notas;
    AccedeBD()
    {
         notas = new NotasBean();
         notas.addBDModificadaListener( (BDModificadaListener)this );
    }

    public void listado()
    {
        for(int i=0; i<notas.contarFilas(); i++)
        {
            notas.seleccionarFila(i);
            System.out.println("DNI " + i + "\n\tDNI:" + notas.getDni());
            System.out.println("\tNombre del módulo: " + notas.getNombreModulo());
            System.out.println("\tCurso: " + notas.getCurso());
            System.out.println("\tNota: " + notas.getNota());
        }
    }

    void anade()
    {
        notas.setDni("23456789B");
        notas.setNombreModulo("FOL");
        notas.setCurso("21-22");
        notas.setNota(6.5);
        try {
            notas.agregarNotaModulo();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccedeBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void capturarBDModificada(BDModificadaEvent ev)
    {
        System.out.println("Se ha añadido una nueva nota a la base de datos");
    }
}
