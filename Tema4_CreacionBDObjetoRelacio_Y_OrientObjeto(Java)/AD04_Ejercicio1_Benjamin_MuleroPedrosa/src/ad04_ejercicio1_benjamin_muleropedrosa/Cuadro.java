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
public class Cuadro {
    
    //Atributos
    private String titulo;
    private Pintor pint1;
    private String tecnica;
    private int largo;
    private int alto;
    
    //Constructores
    public Cuadro() {
    }
 
    public Cuadro(String tit, String tec, int lar, int al) {
        this.titulo = tit;
        this.pint1 = null;
        this.tecnica = tec;
        this.largo = lar;
        this.alto = al;
    }
    //Getter y Setter
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pintor getPint() {
        return pint1;
    }

    public void setPint(Pintor pint) {
        this.pint1 = pint;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

   //Método String
  @Override
  public String toString() {
    return "Cuadro:" + titulo + ", Pintor:" + pint1.getNombre() + ", Técnica:" + tecnica + ", Alto:" + alto + ", Largo:"+largo ;
  }
    
    
    }
    
