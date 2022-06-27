/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ad04_ejercicio1_benjamin_muleropedrosa;

/**
 *
 * @author benmu
 */
public class Pintor {
    
    //Atributos
    private String nombre;
    private String estilo;
    private String nacionalidad;
    
    //Constructores
    public Pintor() {
    }
 
    public Pintor(String nombre, String estilo, String nacionalidad) {
        this.nombre = nombre;
        this.estilo = estilo;
        this.nacionalidad = nacionalidad;
    }

    //Gettter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    //Método String
  @Override
  public String toString() {
    return "Pintor:" + nombre + ", Estilo:" + estilo + ", Nacionalidad:" + nacionalidad ;
  }
   
}
