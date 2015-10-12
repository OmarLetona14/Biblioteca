/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bibliotecaejercicio.model;

import bibliotecaejercicio.helpers.DBHelper;
import bibliotecaejercicio.helpers.Dialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author informatica
 */
public class Libro {
    
    private String idLibro;
    private String titulo;
    private String ISBN;
    private String editorial;
    private String paginas;
    private Autor autor = new Autor();
    private Ejemplar ejemplar = new Ejemplar();

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

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getIdLibro() {
        return idLibro;
    }
    

    public Libro(String idLibro, String titulo, String ISBN, String editorial, String paginas, Autor autor, Ejemplar ejemplar) {
        this.idLibro = idLibro;
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
    
     
        public static boolean insertarLibro(Libro nuevoLibro){
        
        String insertSQL =  "INSERT INTO Libro (titulo, isbn, editorial, paginas)"
                + "VALUES (?, ?, ?, ?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
            insertStatement.setString(1, nuevoLibro.getTitulo());
            insertStatement.setString(2, nuevoLibro.getISBN());
            insertStatement.setString(3, nuevoLibro.getEditorial());
            insertStatement.setString(4, nuevoLibro.getPaginas());
            
            insertStatement.executeUpdate();
            
             String insertSQLAutor =  "INSERT INTO Autor(nombre) "
                + "VALUES (?)";
             
            PreparedStatement insertStatementAutor = DBHelper.getConnection().prepareStatement(insertSQLAutor);
            
            insertStatementAutor.setString(1, nuevoLibro.getAutor().getNombre());
            insertStatementAutor.executeUpdate();
            
             String insertSQLEjemplar =  "INSERT INTO Ejemplar(localizacion, idLibro) "
                + "VALUES (?, ?)";
             
            PreparedStatement insertStatementEjemplar = DBHelper.getConnection().prepareStatement(insertSQLEjemplar);
            
            insertStatementEjemplar.setString(1, nuevoLibro.getEjemplar().getLocalizacion());
            insertStatementEjemplar.setString(2, nuevoLibro.getIdLibro());
            insertStatementEjemplar.executeUpdate();
            
             String insertSQLRelacion =  "INSERT INTO LibroEscrito(idAutor, idLibro) "
                + "VALUES (?, ?)";
             
            PreparedStatement insertStatementRelacion = DBHelper.getConnection().prepareStatement(insertSQLRelacion);
            
            insertStatementRelacion.setString(1, nuevoLibro.getAutor().getIdAutor());
            insertStatementRelacion.setString(2, nuevoLibro.getIdLibro());
            insertStatementRelacion.executeUpdate();
                  
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Error al insertar un libro", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
    
    public static boolean editarLibro(Libro nuevoLibro){
        String updateSQL = "UPDATE Libro "
                + " SET titulo = ?, editorial = ?, paginas = ?, isbn = ?"
                + " WHERE idLibro = ?";
        
         String updateSQLAutor = "UPDATE Autor "
                + " SET nombre = ?"
                + " WHERE idAutor = ?";
         
          String updateSQLEjemplar = "UPDATE Ejemplar "
                + " SET localizacion = ?"
                + " WHERE idLibro = ?";
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(1, nuevoLibro.getTitulo());
            updateStatement.setString(2, nuevoLibro.getEditorial());
            updateStatement.setString(3, nuevoLibro.getPaginas());
            updateStatement.setString(4, nuevoLibro.getISBN());

            updateStatement.executeUpdate();
            
            PreparedStatement updateStatementAutor = DBHelper.getConnection().prepareStatement(updateSQLAutor);
            
            updateStatementAutor.setString(1, nuevoLibro.getAutor().getNombre());

            updateStatementAutor.executeUpdate();
            
             PreparedStatement updateStatementEjemplar = DBHelper.getConnection().prepareStatement(updateSQLEjemplar);
            
            updateStatementEjemplar.setString(1, nuevoLibro.getEjemplar().getLocalizacion());

            updateStatementEjemplar.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Error al editar un libro", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
    public static boolean eliminarLibro(Libro libro){
        String deleteSQL = "DELETE FROM Libro "
                + "WHERE idLibro = ?";
        
        String deleteSQLRelacion = "DELETE FROM LibroEscrito "
                + "WHERE idLibro = ?";
        
        String deleteSQLejemplar = "DELETE FROM Ejemplar "
                + "WHERE idLibro = ?";
        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setString(1, libro.getIdLibro());
            
            deleteStatement.executeUpdate();
            
            PreparedStatement deleteStatementLibroEscrito = DBHelper.getConnection().prepareStatement(deleteSQLRelacion);
            deleteStatementLibroEscrito.setString(1, libro.getIdLibro());
            
            deleteStatement.executeUpdate();
            
             PreparedStatement deleteStatementEjemplar = DBHelper.getConnection().prepareStatement(deleteSQLejemplar);
            deleteStatementEjemplar.setString(1, libro.getIdLibro());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Error al eliminar un libro", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }      
}
