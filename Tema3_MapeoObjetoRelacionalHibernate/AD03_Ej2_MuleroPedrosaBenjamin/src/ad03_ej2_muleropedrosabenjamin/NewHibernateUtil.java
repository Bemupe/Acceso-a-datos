/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad03_ej2_muleropedrosabenjamin;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class NewHibernateUtil {
    private static SessionFactory instancia;
    private static Session session;
    
    //Construye la sesión de Hiberanate
    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    //Abre la sesión de hibernate
    public static void openSession () {
      instancia = NewHibernateUtil.buildSessionFactory();
        session = instancia.openSession();
    
    
    }
    
    //Se obtiene la sesión de hibernate
    public static Session getSession () {

    if ((session==null)||(!session.isOpen()))
        openSession();
    return session;
    }
    
    //Se cierra la sessión de hibernate
    public static void cerrarSessionFactory () {

    if (session!=null)
        session.close();
    if (instancia!=null)
        instancia.close();
   
    }
}