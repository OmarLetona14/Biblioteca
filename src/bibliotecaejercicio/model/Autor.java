/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bibliotecaejercicio.model;

/**
 *
 * @author informatica
 */
public class Autor {
    
    private String idAutor;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Autor(String nombre) {
        this.nombre = nombre;
    }

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public Autor(String idAutor, String nombre) {
        this.idAutor = idAutor;
        this.nombre = nombre;
    }

    public Autor() {
    } 
            
}
