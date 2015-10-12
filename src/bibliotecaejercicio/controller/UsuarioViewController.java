/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecaejercicio.controller;

import bibliotecaejercicio.MainApp;
import bibliotecaejercicio.helpers.Dialogs;
import bibliotecaejercicio.model.Autor;
import bibliotecaejercicio.model.Libro;
import bibliotecaejercicio.model.Usuario;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class UsuarioViewController implements Initializable {

     private MainApp main;

    
      @FXML
    private TableView<Usuario> tbvAutores;
    
    @FXML
    private TableColumn<Usuario, String> tbcNombre;
    
    @FXML
    private TableColumn<Usuario, String> tbcDireccion;
      
    @FXML
    private Label lblTelefono;
    
    
    private MainApp mainApp;
    
     public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tbvAutores.setItems(mainApp.getUsuariosList());
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<Usuario, String>("direccion"));
        tbvAutores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);     
        
        mostrarDetalleUsuarios(null);
        
        tbvAutores.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {        
            @Override
            public void changed(ObservableValue <? extends Usuario> observable,
                Usuario oldValue, Usuario newValue) {
                mostrarDetalleUsuarios(newValue);
            }
        });
    }  
    
     private void mostrarDetalleUsuarios(Usuario usuario){
        if(usuario == null){
            lblTelefono.setText("");
        }else{
            lblTelefono.setText(usuario.getTelefono());

        }
    }
     
     @FXML
    private void eliminarUsuario() {
        int selectedIndex = tbvAutores.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Usuario usuarioAEliminar = tbvAutores.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(Alert.AlertType.CONFIRMATION, "Biblioteca System", null, "¿Está seguro que desea eliminar este usuario?");
            Optional<ButtonType> result = pregunta.showAndWait();
            if (result.get() == ButtonType.OK){
                if(Usuario.eliminarUsuario(usuarioAEliminar)){
                    tbvAutores.getItems().remove(selectedIndex);
                }
            }
        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "No ha seleccionado ningún elemento, por favor vuelva a intentarlo");
            error.showAndWait();
        }
    }
    
    
    @FXML
    private void ingresarUsuario(){
        Usuario usuarioTemporal = new Usuario();
        boolean okPresionado = mainApp.ingresarEditarUsuario(usuarioTemporal, MainApp.CRUDOperation.Create);
        if (okPresionado){
            mainApp.getUsuariosList().add(usuarioTemporal);
        }
    }
    
    @FXML
    private void editarUsuario() {
        Usuario usuarioSeleccionado = tbvAutores.getSelectionModel().getSelectedItem();
        if (usuarioSeleccionado != null) {
            boolean okClicked = mainApp.ingresarEditarUsuario(usuarioSeleccionado, MainApp.CRUDOperation.Update);
            if (okClicked) {
                refresh();
                mostrarDetalleUsuarios(usuarioSeleccionado);
            }

        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "No ha seleccionado ningún usuario");
            error.showAndWait();
        }  
    }
     private void refresh(){
        int selectedIndex = tbvAutores.getSelectionModel().getSelectedIndex();
        tbvAutores.setItems(null);
        tbvAutores.layout();
        tbvAutores.setItems(mainApp.getUsuariosList());
        tbvAutores.getSelectionModel().select(selectedIndex);
    }  
    @FXML
    public void Exit(){
      System.exit(0);
    }    
    
}
