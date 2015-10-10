/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bibliotecaejercicio.model;

import bibliotecaejercicio.helpers.DBHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author informatica
 */
public class Libro {
    
    private String titulo;
    private String ISBN;
    private String editorial;
    private String paginas;
    private Autor autor;
    private Ejemplar ejemplar;

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

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Libro(String titulo, String ISBN, String editorial, String paginas, Autor autor, Ejemplar ejemplar) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.editorial = editorial;
        this.paginas = paginas;
        this.autor = autor;
        this.ejemplar = ejemplar;
    }

    public Libro() {
    }

     
     public static ObservableList<Libro> getLibrosList(){
        ObservableList<Libro> libros = FXCollections.observableArrayList();
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT l.titulo, l.editorial, l.isbn, l.paginas, a.nombre , e.localizacion "
                    + "FROM Libro AS l, LibroEscrito As le, Ejemplar AS e, Autor AS a "
                    + "WHERE l.idLibro = le.idLibro AND e.idLibro = l.idLibro AND le.idAutor = a.idAutor  ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Libro libro = new Libro();
                
                libro.setTitulo(rs.getString("titulo"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setISBN(rs.getString("isbn"));
                libro.setPaginas(rs.getString("paginas"));
                libro.autor.setNombre(rs.getString("nombre"));
                libro.ejemplar.setLocalizacion(rs.getString("localizacion"));
                libros.add(libro);
            }
        }catch(Exception e){
        }
        return libros;
    }
    
    
    
}
