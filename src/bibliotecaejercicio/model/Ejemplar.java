/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecaejercicio.model;

/**
 *
 * @author Erick Omar Letona Figueroa
 */
public class Ejemplar {
    
    private String localizacion;

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Ejemplar(String localizacion) {
        this.localizacion = localizacion;
    }
}
