/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecaejercicio.controller;

import bibliotecaejercicio.MainApp;
import bibliotecaejercicio.MainApp.CRUDOperation;
import bibliotecaejercicio.helpers.Dialogs;
import bibliotecaejercicio.model.Autor;
import bibliotecaejercicio.model.Ejemplar;
import bibliotecaejercicio.model.Libro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class EditarAgregarLibroController implements Initializable {

    @FXML
    private TextField txtTitulo;
    
    @FXML
    private TextField txtAutor;
    
    @FXML
    private TextField txtEditorial;
    
    @FXML
    private TextField txtISBN;
    
    @FXML
    private TextField txtPaginas;
    
    @FXML
    private TextField txtLocalizacion;
   
   
    private Stage dialogStage;
    private Libro libro;
    private boolean presionadoOk;
    private MainApp.CRUDOperation operacion;
    

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setOperacion(MainApp.CRUDOperation operacion) {
        this.operacion = operacion;
    }

      
      public void setLibro(Libro libro) {
        this.libro = libro;
        
        txtTitulo.setText(libro.getTitulo());
        txtAutor.setText(libro.getAutor().getNombre());
        txtEditorial.setText(libro.getEditorial());
        txtISBN.setText(libro.getISBN());
        txtPaginas.setText(libro.getISBN());     
       txtLocalizacion.setText(libro.getEjemplar().getLocalizacion());
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
        if(libroValido()){
            libro.setTitulo(txtTitulo.getText());
            libro.getAutor().setNombre(txtAutor.getText());
            libro.setEditorial(txtEditorial.getText());
            libro.setISBN(txtISBN.getText());
            libro.setPaginas(txtPaginas.getText());
        libro.getEjemplar().setLocalizacion(txtLocalizacion.getText());
            if (operacion.equals(CRUDOperation.Create)){
                presionadoOk = libro.insertarLibro(libro);
            }
            if (operacion.equals(CRUDOperation.Update)){
                presionadoOk = libro.editarLibro(libro);
            }
            dialogStage.close();
        }
    }
    
    @FXML
    private void canceledChanges(){
        dialogStage.close();
    }
    
    private boolean libroValido(){
        if(txtTitulo.getText() == null || txtTitulo.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Titulo no valido, "
                    + "¡por favor ingrese un valor!");
            error.showAndWait();
            txtTitulo.requestFocus();
            return false;
        }
        if(txtAutor.getText() == null || txtAutor.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Autor no valido, "
                    + "¡por favor ingrese un valor!");
            error.showAndWait();
            txtAutor.requestFocus();
            return false;
        }
        if(txtEditorial.getText() == null || txtEditorial.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Editorial no valido, "
                    + "¡por favor ingrese un valor!");
            error.showAndWait();
            txtEditorial.requestFocus();
            return false;
        }
        if(txtISBN.getText() == null || txtISBN.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "ISNB no valido, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtISBN.requestFocus();
            return false;
        }
         if(txtPaginas.getText() == null || txtPaginas.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Paginas no validas, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtPaginas.requestFocus();
            return false;
        }
         
         if(txtLocalizacion.getText() == null || txtLocalizacion.getText().length() == 0){
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "Localizacion no valida, "
                    + "por favor ingrese un valor!");
            error.showAndWait();
            txtLocalizacion.requestFocus();
            return false;
        }
       
        return true;
    }
    
}
