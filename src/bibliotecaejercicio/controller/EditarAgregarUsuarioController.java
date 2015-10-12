/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecaejercicio.controller;

import bibliotecaejercicio.MainApp;
import bibliotecaejercicio.helpers.Dialogs;
import bibliotecaejercicio.model.Libro;
import bibliotecaejercicio.model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class EditarAgregarUsuarioController implements Initializable {

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtDireccion;
    
    @FXML
    private TextField txtTelefono;
    
     private Stage dialogStage;
    private Usuario usuario;
    private boolean presionadoOk;
    private MainApp.CRUDOperation operacion;
    
      public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setOperacion(MainApp.CRUDOperation operacion) {
        this.operacion = operacion;
    }

      
      public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        
        txtNombre.setText(usuario.getNombre());
        txtDireccion.setText(usuario.getDireccion());
        txtTelefono.setText(usuario.getTelefono());

    } 
       public boolean fuePresionadoOk(){
        return this.presionadoOk;
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void acceptedChanges(){
        if(usuarioValido()){
            usuario.setNombre(txtNombre.getText());
            usuario.setDireccion(txtDireccion.getText());
            usuario.setTelefono(txtTelefono.getText());
            if (operacion.equals(MainApp.CRUDOperation.Create)){
                presionadoOk = usuario.insertarUsuario(usuario);
            }
            if (operacion.equals(MainApp.CRUDOperation.Update)){
                presionadoOk = usuario.editarUsuario(usuario);
            }
            dialogStage.close();
        }
    }
    
    @FXML
    private void canceledChanges(){
        dialogStage.close();
    }
    
    private boolean usuarioValido(){
        if(txtNombre.getText() == null || txtNombre.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Nombre no valido, "
                    + "¡por favor ingrese un valor!");
            error.showAndWait();
            txtNombre.requestFocus();
            return false;
        }
        if(txtDireccion.getText() == null || txtDireccion.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Direccion no valida, "
                    + "¡por favor ingrese un valor!");
            error.showAndWait();
            txtDireccion.requestFocus();
            return false;
        }
        if(txtTelefono.getText() == null || txtTelefono.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Telefono no valido, "
                    + "¡por favor ingrese un valor!");
            error.showAndWait();
            txtTelefono.requestFocus();
            return false;
        }

        return true;
    }
}
