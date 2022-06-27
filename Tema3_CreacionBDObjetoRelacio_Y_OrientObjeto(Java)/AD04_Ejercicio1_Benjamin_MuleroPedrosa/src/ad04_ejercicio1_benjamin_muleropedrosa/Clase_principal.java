/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad04_ejercicio1_benjamin_muleropedrosa;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

/**
 *
 * @author benmu
 */
public class Clase_principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Hacemos la conexión a la base de datos y creamos el archivo
        ObjectContainer db = Db4oEmbedded.openFile("pintoresCuadros.db4o");
        
        //Llamamos a métodos para operar con la base de objetos
        try {
            System.out.println("2. CREAR CUADROS Y PINTORES");
            System.out.println("---------------------------");
            guardarPintoresCuadros(db);
            System.out.println();
      
      
            System.out.println("3. CONSULTA Nº DE CUADROS TÍTULOS/PINTOR");
            System.out.println("----------------------------------------");
            consulNumCuadPintTitu(db);
      
            System.out.println("\n");
            System.out.println("4. CONSULTA DE LOS CUADROS, SEGÚN EL NOMBRE DEL PINTOR");
            System.out.println("------------------------------------------------------");
            consulCuadroPintor (db, "Picasso");

            System.out.println("\n");
            System.out.println("5. CONSULTA CUADROS MEDIDAS ENTRE DOS ALTURAS");
            System.out.println("---------------------------------------------");
            consulAlturaIntervalo (db, 92, 146);

            System.out.println("\n");
            System.out.println("6. BORRADO DE UN CUADRO EN PARTICULAR");
            System.out.println("-------------------------------------");
            eliminarCuadro (db, "Las meninas");

            System.out.println("\n");
            System.out.println("7. MODIFICAR LARGO DEL CUADRO SEGÚN TÍTULO");
            System.out.println("------------------------------------------");
            modificarLargCuadroPorTit (db,"Guernica", 30);

            System.out.println("\n");
            System.out.println("8. ELIMINACIÓN EN CASCADA");
            System.out.println("-------------------------");
            eliminarTodosCuadrosAutor(db, "Van Gogh");

            System.out.println("\n");
            System.out.println("9. MODIFICACIÓN EN CASCADA");
            System.out.println("--------------------------");
            modificarNombreTodosCuadrosAutor(db, "Picasso", "Esto es una prueba");
        
        } catch (Exception a) {
            System.err.println("Error: "+a);
        } finally {
            // Cerramos la base de datos antes de salir
            db.close(); 
    }

    }
    
    //Almacenamiento de datos
    public static void guardarPintoresCuadros(ObjectContainer db) {
        //Creamos los objetos cuadros
        Cuadro cd1=new Cuadro("Guernica", "Óleo sobre lienzo", 776, 349);
        Cuadro cd2=new Cuadro("Las señoritas de Avignon", "Óleo sobre lienzo", 233, 243);
        Cuadro cd3=new Cuadro("Las meninas", "Óleo sobre lienzo", 276, 318);
        Cuadro cd4=new Cuadro("El aguador de Sevilla", "Óleo sobre lienzo",81,106);
        Cuadro cd5=new Cuadro("La noche estrellada", "Óleo sobre lienzo", 73,92);
        Cuadro cd6 =new Cuadro("Los girasoles", "Óleo sobre lienzo", 93,72);
        
        //Creamos los objetos pintores
        Pintor pint1=new Pintor("Picasso", "Cubismo","Español");
        Pintor pint2=new Pintor("Diego Velázquez", "Barroco", "Español");
        Pintor pint3=new Pintor("Van Gogh", " Posimpresionismo", "Países Bajos");
        
        //Introducimos los pintores según le corresponda el cuadro con el método de la clase "Cuadro" setPint(), que introduce el objeto "Pintor"
        cd1.setPint(pint1);  
        cd2.setPint(pint1);
        cd3.setPint(pint2);
        cd4.setPint(pint2);
        cd5.setPint(pint3);
        cd6.setPint(pint3);

        //Hacemos persistir los objetos y los almacenamos en el archivo/base de datos con el método store()
        db.store(cd1);
        System.out.println("Añadido a base de datos: "+cd1);
        db.store(cd2);
        System.out.println("Añadido a base de datos: "+cd2);
        db.store(cd3);
        System.out.println("Añadido a base de datos: "+cd3);
        db.store(cd4);
        System.out.println("Añadido a base de datos: "+cd4);
        db.store(cd5);
        System.out.println("Añadido a base de datos: "+cd5);
        db.store(cd6);
        System.out.println("Añadido a base de datos: "+cd6);
        
         
    }
    
                    
    //Consulta de todos los cuadros en el que aparece el pintor y el título
    public static void consulNumCuadPintTitu (ObjectContainer db){
        //Creamos un objeto "cuadro" con los atributos nulos para que entren todos los cuadros en la consulta. 
        Cuadro cuad = new Cuadro(null, null, 0,0);
        
        //Creamos un "ObjectSet" de la clase cuadro en el cual utilizamos el query "ByExample" 
        //utilizando como referencia el objeto creado anteriormente.
        ObjectSet<Cuadro> resultadoCuadro = db.queryByExample(cuad);
        
        //Obtenemos los resultados
        System.out.println("Nº de Cuadros de la base de datos:"+resultadoCuadro.size());
        System.out.println("_____________________________________________________________________________________________________");
        while (resultadoCuadro.hasNext()) {
            Cuadro marc = resultadoCuadro.next();
            System.out.println("Cuadro: "+marc.getTitulo()+", Autor:"+marc.getPint().getNombre());
        }
        System.out.println("_____________________________________________________________________________________________________");


    }
    //Consulta de cuadro según el nombre del pintor
    public static void consulCuadroPintor (ObjectContainer db, String namePintor){
        //Creamos un objeto cuadro, con todos los atributos en nulo menos el nombre que será un atributo "String" con el nombre del pintor.
        Cuadro cuad = new Cuadro(null, null, 0,0);
        
        //Creamos un "ObjectSet" de la clase cuadro en el cual utilizamos el query "ByExample" 
        //utilizando como referencia el objeto creado anteriormente.
        ObjectSet<Cuadro> resultadoCuadro = db.queryByExample(cuad);
        
        System.out.println("Cuadros de: "+namePintor);
        System.out.println("_____________________________________________________________________________________________________");
        
        //Obtenemos los resultados
        if(resultadoCuadro.toString().contains("Pintor:"+namePintor)){
            while (resultadoCuadro.hasNext()) {
                Cuadro marc = resultadoCuadro.next();
                if (marc.getPint().getNombre().equals(namePintor)){
                    System.out.println(marc.toString());
                }
            }
        }else {
            System.out.println("Pintor "+"'"+namePintor+"'"+" no se encuentra.");
        }; 
        
        System.out.println("_____________________________________________________________________________________________________");   
    
    }
    //Consulta de cuadros según intervalo de altura
    public static void consulAlturaIntervalo (ObjectContainer db, int alturaMin, int alturaMax){
        //Creamos un objeto cuadro, con todos los atributos en nulo para que se seleccionen todos los registros
        Cuadro cuad = new Cuadro(null, null, 0,0);
        
        //Creamos un "ObjectSet" de la clase cuadro en el cual utilizamos el query "ByExample" 
        //utilizando como referencia el objeto creado anteriormente.
        ObjectSet<Cuadro> resultadoCuadro = db.queryByExample(cuad);
        System.out.println("Altura entre "+alturaMin+" y "+alturaMax+":");
        
        //Obtenemos los resultados
        System.out.println("_____________________________________________________________________________________________________");
        while (resultadoCuadro.hasNext()) {
            Cuadro marc = resultadoCuadro.next();
            if (marc.getAlto()>=alturaMin&&marc.getAlto()<=alturaMax){
                System.out.println("Cuadro: "+marc.getTitulo()+", Altura:"+marc.getAlto());
            }
        }
        System.out.println("_____________________________________________________________________________________________________");
   
    }
    
    //Eliminar cuadro por nombre de sus título
    public static void eliminarCuadro (ObjectContainer db, String titCuadro){
        //Creamos un objeto cuadro, con todos los atributos en nulo para que se seleccionen todos los registros
        Cuadro cuad = new Cuadro(titCuadro, null, 0,0);
        
        //Creamos un "ObjectSet" de la clase cuadro en el cual utilizamos el query "ByExample" 
        //utilizando como referencia el objeto creado anteriormente.
        ObjectSet<Cuadro> resultadoCuadro = db.queryByExample(cuad);
        System.out.println("Borrado del cuadro: "+titCuadro);
        
        //Obtenemos los resultados
        System.out.println("_____________________________________________________________________________________________________");
        if (resultadoCuadro.hasNext()) {
            Cuadro cDel = resultadoCuadro.next();
            db.delete(cDel);
            System.out.println("CUADRO "+"'"+titCuadro+"'"+" BORRADO");
        }else{
            System.out.println("CUADRO NO BORRADO (No está en la base de datos)");
        }
        
        //Utilizamos el método para consultar cuadros creado anteriormente, de esta forma vemos si se ha borrado o no el cuadro. 
        consulNumCuadPintTitu (db);
        consultarPintores (db);
        
    }
    
    //Método para ver los pintores de la base de datos
     public static void consultarPintores (ObjectContainer db){
        Pintor cuad = new Pintor(null, null, null);
        ObjectSet<Pintor> resultadoCuadro = db.queryByExample(cuad);
        System.out.println("COMPROBACIÓN PINTORES");
         System.out.println("_____________________________________________________________________________________________________");
        while (resultadoCuadro.hasNext()) {
            Pintor pComp = resultadoCuadro.next();
            System.out.println(pComp);
        }     
    }
    
    
    
    //Modificar largo del cualdro según título
    public static void modificarLargCuadroPorTit (ObjectContainer db, String titCuadro, int larg){
        //Creamos un objeto cuadro, con todos los atributos en nulo para que se seleccionen todos los registros
        Cuadro cuad = new Cuadro(titCuadro, null, 0,0);
        
        //Creamos un "ObjectSet" de la clase cuadro en el cual utilizamos el query "ByExample" 
        //utilizando como referencia el objeto creado anteriormente.
        ObjectSet<Cuadro> resultadoCuadro = db.queryByExample(cuad);
        System.out.println("Modificar altura: "+titCuadro);
        
        //Obtenemos los resultados
        System.out.println("_____________________________________________________________________________________________________");
        if (resultadoCuadro.hasNext()) {
            //Obtengo el objeto cuadro
            Cuadro cAct = resultadoCuadro.next();
            System.out.println("Antes: "+cAct);
            //Actualizao el objeto con el nuevo largo a través de el método set de la clase "Cuadro"
            cAct.setLargo(larg);
            
            //Guardamos el objeto y actualizamos con el cambio.
            db.store(cAct);
            
            //Resultado
            System.out.println("Resultado: "+cAct);
        }else {
            System.out.println("CUADRO NO MODIFICADO (No está en la base de datos)");
        }             
        
    }
    

  //Eliminación de cuadro por autor en cascada.
  public static void eliminarTodosCuadrosAutor(ObjectContainer db, String autor) {
      //Creamos un objeto cuadro, con todos los atributos salvo el del nombre del pintor, en el cual utilizamos un atributo "String"  
      Cuadro cuad = new Cuadro(null, null, 0,0);
      
      ///Ejecutamos la consulta con restricción búsqueda según el objeto anterior. (Nombre del autor establecido por el "String autor")
      ObjectSet<Cuadro> resultadoCuadro = db.queryByExample(cuad);
      
      System.out.println("Eliminando todos los cuadros de: "+autor);
      
      //Establecemos un condicional para el caso del autor establecido no tenga cuadros en la base de datos. 
      //Si llegan los registros pasa al bucle y se van borrando.
      if(resultadoCuadro.toString().contains("Pintor:"+autor)){
            while (resultadoCuadro.hasNext()) {
                Cuadro cCas = resultadoCuadro.next();
                if (cCas.getPint().getNombre().equals(autor)){
                  //Eliminamos el cuadro
                  db.delete(cCas);
                  System.out.println(cCas+"/ELIMINADO");
                }
            }
        }else {
            System.out.println("Pintor "+autor+" no tiene cuadros en la base de datos");
        }; 
      
      //Comprobamos la eliminación
      System.out.println();
      System.out.println("COMPROBACIÓN:");
      consulNumCuadPintTitu (db);
  }

//Modificación de cuadros por autor en cascada.
  public static void modificarNombreTodosCuadrosAutor(ObjectContainer db, String autor, String modificacion) {
      //Creamos un objeto cuadro, con todos los atributos salvo el del nombre del pintor, en el cual utilizamos un atributo "String"  
      Cuadro cuad = new Cuadro(null, null, 0,0);
      
      ///Ejecutamos la consulta con restricción búsqueda según el objeto anterior. (Nombre del autor establecido por el "String autor")
      ObjectSet<Cuadro> resultadoCuadro = db.queryByExample(cuad);
      System.out.println("Modificar todos los cuadros de: "+autor+" con el título "+"'"+modificacion+"'");
      
      //Establecemos un condicional para el caso del autor establecido no tenga cuadros en la base de datos. 
      //Si llegan los registros pasa al bucle y se van modificando.
      if(resultadoCuadro.toString().contains("Pintor:"+autor)) {
          while (resultadoCuadro.hasNext()) {
              
              //Introduzco el resultado en un objeto cuadro
              Cuadro cCas = (Cuadro) resultadoCuadro.next();
              
              if(cCas.getPint().getNombre().equals(autor)) {
                  
                  //Actualizo el objeto con el nuevo nombre del cuadro a través de el método set de la clase "Cuadro" 
                  System.out.println(cCas.getTitulo()+", Autor:"+cCas.getPint().getNombre()+" /MODIFICANDO...");
                  cCas.setTitulo(modificacion);
                  
                  //Guardamos el objeto y actualizamos con el cambio.
                  db.store(cCas);
              }
          } 
      } else {
          System.out.println("Pintor "+autor+" no tiene cuadros en la base de datos");
      }
      
      //Comprobamos la modificación
      System.out.println();
      System.out.println("COMPROBACIÓN:");
      consulNumCuadPintTitu (db);
  }


}


    

