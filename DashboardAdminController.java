/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DashboardAdminController implements Initializable {

    
        @FXML
    private Button btnLogout;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Event handler untuk tombol btnLogout
btnLogout.setOnAction(event -> {
    try {
        // Load file fxml untuk halaman Dashboard
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = loader.load();


        // Inisialisasi scene
        Scene scene = new Scene(root);

        // Ambil stage dari button
        Stage stage = (Stage) btnLogout.getScene().getWindow();

        // Set scene baru pada stage
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
});
        // TODO
    }    
    
    
    @FXML
    private void home(MouseEvent event){
         bp.setCenter(ap);
    }
    
    @FXML
    private void baptis(MouseEvent event) {
       loadPage("ShowBaptis");
    }

    @FXML
    private void pernikahan(MouseEvent event) {
        loadPage("ShowPernikahan");
    }
    
      @FXML
    private void naiksidi(MouseEvent event) {
        loadPage("ShowNaiksidi");
    }
    
    
    @FXML
    private void sejarahgereja(MouseEvent event) {
        loadPage("ShowSejarahGereja");
    }
   
    
    
    
    private void loadPage(String page){
     Parent root = null;
     
     try{
     root = FXMLLoader.load(getClass().getResource(page+".fxml"));
     }catch(IOException ex){
     Logger.getLogger(DashboardAdminController.class.getName()).log(Level.SEVERE,null,ex);
     }
     bp.setCenter(root);
    }


  
    
}
