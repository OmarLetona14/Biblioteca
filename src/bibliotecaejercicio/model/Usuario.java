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
public class Usuario {
    
    private String idUsuario;
    private String nombre;
    private String telefono;
    private String direccion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String idUsuario, String nombre, String telefono, String direccion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Usuario() {
    }
   
     public static ObservableList<Usuario> getUsuariosList(){
        ObservableList<Usuario> usuarios = FXCollections.observableArrayList();
        try{
            Connection con = DBHelper.getConnection();
            String sql = "SELECT * "
                    + "FROM Usuario ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
                Usuario usuario = new Usuario();
                
                usuario.setIdUsuario(rs.getString("idUsuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono(rs.getString("telefono"));

            }
        }catch(Exception e){
        }
        return usuarios;
    }
     
     public static boolean insertarUsuario(Usuario nuevoUsuario){
        
        String insertSQL =  "INSERT INTO Usuario (nombre, telefono, direccion)"
                + "VALUES (?, ?, ?)";
        try{
            PreparedStatement insertStatement = DBHelper.getConnection().prepareStatement(insertSQL);
            
            insertStatement.setString(1, nuevoUsuario.getNombre());
            insertStatement.setString(2, nuevoUsuario.getTelefono());
            insertStatement.setString(3, nuevoUsuario.getDireccion());

            
            insertStatement.executeUpdate();
            
                  
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Error al insertar un usuario", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
     
       public static boolean editarUsuario(Usuario nuevoUsuario){
        String updateSQL = "UPDATE Usuario "
                + "SET nombre = ?, telefono = ?, direccion = ?"
                + "WHERE idUsuario = ?";
        
        try{
            PreparedStatement updateStatement = DBHelper.getConnection().prepareStatement(updateSQL);
            
            updateStatement.setString(1, nuevoUsuario.getNombre());
            updateStatement.setString(2, nuevoUsuario.getTelefono());
            updateStatement.setString(3, nuevoUsuario.getDireccion());
            updateStatement.setString(4, nuevoUsuario.getIdUsuario());

            updateStatement.executeUpdate();
            
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Error al editar un usuario", ex);
            error.showAndWait();
            return false;
        }
        
        return true;
    }
    
        public static boolean eliminarUsuario(Usuario usuario){
        String deleteSQL = "DELETE FROM Usuario "
                + "WHERE idUsuario = ?";

        try{
            PreparedStatement deleteStatement = DBHelper.getConnection().prepareStatement(deleteSQL);
            deleteStatement.setString(1, usuario.getIdUsuario());
            
            deleteStatement.executeUpdate();
            
        }catch( SQLException | ClassNotFoundException ex){
            Alert error = Dialogs.getErrorDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Error al eliminar un usuario", ex);
            error.showAndWait();
            return false;
        }
        return true;
    }
       
       
}
