/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bibliotecaejercicio.controller;

import bibliotecaejercicio.MainApp;
import com.sun.javaws.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class RootLayoutController implements Initializable {

 private MainApp main;

    public void setMainApp(MainApp main) {
        this.main = main;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private void exitApp(){
        System.exit(0);
    }
}
