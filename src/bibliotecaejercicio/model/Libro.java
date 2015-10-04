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
public class Libro {
    
    private String titulo;
    private String ISBN;
    private String editorial;
    private int paginas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public Libro(String titulo, String ISBN, String editorial, int paginas) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.paginas = paginas;
    }
    
    
    
}
