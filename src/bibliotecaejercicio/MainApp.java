/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bibliotecaejercicio;

import bibliotecaejercicio.controller.BibliotecaViewController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Erick Omar Letona Figueroa
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
   //private ObservableList<Books> bookList = FXCollections.observableArrayList();
    
    
    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.primaryStage.setTitle("Biblioteca");
        
       try{
          FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PrimaryStageFXML.fxml"));
          rootLayout =(BorderPane)loader.load();
          
          
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
                          
       }catch(IOException e){
          e.printStackTrace();
       }
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public void showBooksList(){
            try{
                FXMLLoader booksLoader = new FXMLLoader(MainApp.class.getResource("view/Books.fxml"));
                AnchorPane bookList = (AnchorPane)booksLoader.load();
                BibliotecaViewController controller = booksLoader.getController();
                rootLayout.setCenter(bookList);   
            }catch(IOException e){
            e.printStackTrace();
            }
    }
    
    public void loadBooks(){
       /* bookList.add(new Books("1","Harry Potter, Las Reliquias De La Muerte", "1995"));
        bookList.add(new Books("2","Aprendiendo UML en 24 horas", "1995"));
        bookList.add(new Books("3","Monos y Micos", "1995")); */
    }       

    public ObservableList<Books> getBookList() {
        return bookList;
    }
    
    
    
}
