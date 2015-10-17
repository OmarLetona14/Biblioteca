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
import bibliotecaejercicio.model.Libro;
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
 * @author Omar Letona
 */
public class BibliotecaViewController implements Initializable {
    
    @FXML
    private TableView<Libro> tbvBiblioteca;
    
    @FXML
    private TableColumn<Libro, String> tbcTitulo;
    
    @FXML
    private TableColumn<Autor, String> tbcAutor;
    
    @FXML   
    private TableColumn<Libro, String> tbcEditorial;
    
    @FXML
    private Label lblISBN;
    
    @FXML
    private Label lblPaginas;
    
    @FXML
    private Label lblLocalizacion;
    
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        tbvBiblioteca.setItems(mainApp.getLibrosList());
    }
    
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcTitulo.setCellValueFactory(new PropertyValueFactory<Libro, String>("titulo"));
        tbcAutor.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombre"));
        tbcEditorial.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        tbvBiblioteca.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);     
        
        mostrarDetalleLibros(null);
        
        tbvBiblioteca.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Libro>() {        
            @Override
            public void changed(ObservableValue <? extends Libro> observable,
                Libro oldValue, Libro newValue) {
                mostrarDetalleLibros(newValue);
            }
        });
    }  
    
     private void mostrarDetalleLibros(Libro libro){
        if(libro == null){
            lblISBN.setText("");
            lblPaginas.setText("");
            lblLocalizacion.setText("");

        }else{
            lblISBN.setText(libro.getISBN());
            lblPaginas.setText(libro.getPaginas());
            lblLocalizacion.setText(libro.getEjemplar().getLocalizacion());
        }
    }
     
     @FXML
    private void eliminarLibro() {
        int selectedIndex = tbvBiblioteca.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Libro libroAEliminar = tbvBiblioteca.getSelectionModel().getSelectedItem();
            Alert pregunta = Dialogs.getDialog(Alert.AlertType.CONFIRMATION, "Biblioteca System", null, "¿Está seguro que desea eliminar este libro?");
            Optional<ButtonType> result = pregunta.showAndWait();
            if (result.get() == ButtonType.OK){
                if(Libro.eliminarLibro(libroAEliminar)){
                    tbvBiblioteca.getItems().remove(selectedIndex);
                }
            }
        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "biblioteca System", null, "No ha seleccionado ningún elemento, por favor vuelva a intentarlo");
            error.showAndWait();
        }
    }
    
    
    @FXML
    private void ingresarLibro(){
        Libro libroTemporal = new Libro();
        boolean okPresionado = mainApp.ingresarEditarLibro(libroTemporal, CRUDOperation.Create);
        if (okPresionado){
            mainApp.getLibrosList().add(libroTemporal);
        }
    }
    
    @FXML
    private void editarLibro() {
        Libro libroSeleccionado = tbvBiblioteca.getSelectionModel().getSelectedItem();
        if (libroSeleccionado != null) {
            boolean okClicked = mainApp.ingresarEditarLibro(libroSeleccionado, CRUDOperation.Update);
            if (okClicked) {
                refresh();
                mostrarDetalleLibros(libroSeleccionado);
            }

        } else {
            Alert error = Dialogs.getDialog(Alert.AlertType.ERROR, "Biblioteca System", null, "No ha seleccionado ningún libro");
            error.showAndWait();
        }  
    }
     private void refresh(){
        int selectedIndex = tbvBiblioteca.getSelectionModel().getSelectedIndex();
        tbvBiblioteca.setItems(null);
        tbvBiblioteca.layout();
        tbvBiblioteca.setItems(mainApp.getLibrosList());
        tbvBiblioteca.getSelectionModel().select(selectedIndex);
    } 
    @FXML
    public void Exit(){
      System.exit(0);
    }
   
}
