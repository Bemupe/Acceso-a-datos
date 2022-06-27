/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Alumno;

import java.beans.*;
import java.io.Serializable;
import java.sql.*;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class NotasBean implements Serializable {
    private PropertyChangeSupport propertySupport;

    /*****************************************************
     * Propiedades del Bean.
     * Crearemos una propiedad por cada campo de la tabla de
     * la base de datos del siguiente modo:
     *
     * DNI: String
     * Nombre modulo: String
     * Curso: String
     * Nota: Double
     */
    protected String dni;
    protected String nombreModulo;
    protected String curso;
    protected Double nota;
    
    public NotasBean() {
        propertySupport = new PropertyChangeSupport(this);
        try {
            recargarFilas();
        } catch (ClassNotFoundException ex) {
            this.dni = "";
            this.nombreModulo = "";
            this.curso = "";
            this.nota = 0.0;
            Logger.getLogger(NotasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the value of DNI
     *
     * @return the value of DNI
     */
    public String getDni() {
        return dni;
    }

    /**
     * Set the value of DNI
     *
     * @param dni new value of DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Get the value of Nombre Modulo
     *
     * @return the value of Nombre Modulo
     */
    public String getNombreModulo() {
        return nombreModulo;
    }

    /**
     * Set the value of Nombre Modulo
     *
     * @param nombreModulo new value of Nombre Modulo
     */
    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

 
    /**
     * Get the value of Curso
     *
     * @return the value of Curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Set the value of Curso
     *
     * @param curso new value of Curso
     */
    public void setCurso(String curso) {
        this.curso = curso;
    }


    /**
     * Get the value of Nota
     *
     * @return the value of Nota
     */
    public Double getNota() {
        return nota;
    }

    /**
     * Set the value of Nota
     *
     * @param nota new value of Nota
     */
    public void setNota(Double nota) {
        this.nota = nota;
    }


    /*******************************************************
     * Definimos los métodos y atributos privados del
     * componente que usaremos para darle funcionalidad.
     *
     */


    /*****************************************************
     * Clase auxiliar que usaremos para crear un vector privado
     * de alumnos.
     */
    private class Nota
    {
       String dni;
       String nombreModulo;
       String curso;
       Double nota;

       public Nota()
        {}

        private Nota(String nDni, String nNombreModulo, String nCurso, Double nota)
        {
          this.dni=nDni;
          this.nombreModulo=nNombreModulo;
          this.curso=nCurso;
          this.nota=nota;
        }

    }


    /******************************************************
     * Usaremos un vector auxiliar para cargar la información de la
     * tabla de forma que tengamos acceso a los datos sin necesidad
     * de estar conectados constantemente
     */
    private Vector Notas=new Vector();

    /*******************************************************
     * Actualiza el contenido de la tabla en el vector de notas
     * Las propiedades contienen el valor del primer elementos de la tabla
     */
    private void recargarFilas() throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnosbean?characterEncoding=utf8", "root", "Benjamin");
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery ("select * from notasfinales");
            while (rs.next())
            {
                Nota a = new Nota(rs.getString("dni"),
                                      rs.getString("nombremodulo"),
                                      rs.getString("curso"),
                                      rs.getDouble("nota"));

                Notas.add(a);
            }
            Nota a = new Nota();
            a = (Nota) Notas.elementAt(1);
            this.dni = a.dni;
            this.nombreModulo = a.nombreModulo;
            this.curso = a.curso;
            this.nota = a.nota;
            rs.close();
            con.close();
        } catch (SQLException ex) {
            this.dni = "";
            this.nombreModulo = "";
            this.curso = "";
            this.nota = 0.0;
            Logger.getLogger(NotasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /********************************************************
     *
     * @param i numero de la fila a cargar en las propiedades del componente
     */
    public void seleccionarFila(int i)
    {
        if(i<=Notas.size())
        {
            Nota a = new Nota();
            a = (Nota) Notas.elementAt(i);
            this.dni = a.dni;
            this.nombreModulo = a.nombreModulo;
            this.curso = a.curso;
            this.nota = a.nota;
        }else{
            this.dni = "";
            this.nombreModulo = "";
            this.curso = "";
            this.nota = 0.0;
        }
    }
    
     /********************************************************
     *
     * @return El número de registros
     */
    
        public int contarFilas()
    {
        return Notas.size();

    }

    /********************************************************
     *
     * @param nDni DNI A buscar, se carga en las propiedades del componente
     */
    public void seleccionarDNI(String nDni)
    {
        Nota a = new Nota();
        int i=0;

        this.dni = a.dni;
        this.nombreModulo = a.nombreModulo;
        this.curso = a.curso;
        this.nota = a.nota;
        while(this.dni.equals("") && i<=Notas.size())
        {
            a = (Nota)Notas.elementAt(i);
            if ( a.dni.equals(nDni) )
            {
                this.dni = a.dni;
                this.nombreModulo = a.nombreModulo;
                this.curso = a.curso;
                this.nota = a.nota;
            }
        }
    }


    /*********************************************************************
     * Código para añadir un nuevo alumno a la base de datos.
     * cada vez que se modifca el estado de la BD se genera un evento para
     * que se recargue el componente.
     */

    private BDModificadaListener receptor;

    public class BDModificadaEvent extends java.util.EventObject
    {
        // constructor
        public BDModificadaEvent(Object source)
        {
            super(source);
        }
    }
    

    //Define la interfaz para el nuevo tipo de evento
    public interface BDModificadaListener extends EventListener
    {
        public void capturarBDModificada(BDModificadaEvent ev);
    }

    public void addBDModificadaListener(BDModificadaListener receptor)
    {
        this.receptor = receptor;
    }
    public void removeBDModificadaListener(BDModificadaListener receptor)
    {
        this.receptor=null;
    }


    /*******************************************************
     * Método que añade un alumno a la base de datos
     * añade un registro a la base de datos formado a partir
     * de los valores de las propiedades del componente.
     *
     * Se presupone que se han usado los métodos set para configurar
     * adecuadamente las propiedades con los datos del nuevo registro.
     */
    public void agregarNotaModulo() throws ClassNotFoundException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnosbean?characterEncoding=utf8", "root", "Benjamin");
            PreparedStatement s = con.prepareStatement("insert into notasfinales values (?,?,?,?)");

            s.setString(1, dni);
            s.setString(2, nombreModulo);
            s.setString(3, curso);
            s.setDouble(4, nota);
            

            s.executeUpdate ();
            recargarFilas();
            receptor.capturarBDModificada( new BDModificadaEvent(this));
        }
        catch(SQLException ex)
        {
            Logger.getLogger(NotasBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }




    /*******************************************************
     *
     * @param listener
     */

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

}
