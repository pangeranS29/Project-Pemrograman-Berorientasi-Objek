package project3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardUserController implements Initializable {

     @FXML
    private Button  btnSejarahGereja;
   
        
        
    @FXML
    private Button btnBaptis;

    @FXML
    private Button btnPernikahan;

    @FXML
    private Button btnNaiksidi;

    @FXML
    private Button btnLogout;

    @FXML
    private Label welcomeLabel;

    private CrudFormbaptisController crudFormbaptisController;
    private FormPernikahanController formPernikahanController;
     private FormNaiksidiController formNaiksidiController;

    public void setCrudFormbaptisController(int userId) {
        this.crudFormbaptisController = new CrudFormbaptisController();
        this.crudFormbaptisController.setUserId(userId);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Event handler untuk tombol btnBaptis
        btnBaptis.setOnAction(event -> {
            try {
                // Load file fxml untuk form request baptis
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudFormbaptis.fxml"));
                Parent root = loader.load();

                // Dapatkan instance CrudFormbaptisController
                CrudFormbaptisController crudFormbaptisController = loader.getController();

                // Atur user ID pada CrudFormbaptisController
                crudFormbaptisController.setUserId(this.crudFormbaptisController.getUserId());

                // Inisialisasi scene
                Scene scene = new Scene(root);

                // Set stage untuk form request baptis
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL); // Membuat stage menjadi modal
                stage.setTitle("Form Request Baptis");
                stage.setScene(scene);
                stage.showAndWait(); // Menampilkan form dan menunggu sampai ditutup

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Event handler untuk tombol btnPernikahan
        btnPernikahan.setOnAction(event -> {
            try {
                // Load file fxml untuk form pernikahan
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FormPernikahan.fxml"));
                Parent root = loader.load();
                
                 // Dapatkan instance FormPernikahanController
            formPernikahanController = loader.getController();

            // Atur user ID pada FormPernikahanController
            formPernikahanController.setUserId(this.crudFormbaptisController.getUserId());
                
                
                // Inisialisasi scene
                Scene scene = new Scene(root);

                // Set stage untuk form pernikahan
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL); // Membuat stage menjadi modal
                stage.setTitle("Form Pernikahan");
                stage.setScene(scene);
                stage.showAndWait(); // Menampilkan form dan menunggu sampai ditutup

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Event handler untuk tombol btnNaiksidi
        btnNaiksidi.setOnAction(event -> {
            try {
                // Load file fxml untuk form naik sidi
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FormNaiksidi.fxml"));
                Parent root = loader.load();
                
                
                // Dapatkan instance FormNaiksidiController
                formNaiksidiController = loader.getController();

                // Atur user ID pada FormNaiksidiController
                formNaiksidiController.setUserId(this.crudFormbaptisController.getUserId());
                
                
                // Inisialisasi scene
                Scene scene = new Scene(root);

                // Set stage untuk form naik sidi
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL); // Membuat stage menjadi modal
                stage.setTitle("Form Naik Sidi");
                stage.setScene(scene);
                stage.showAndWait(); // Menampilkan form dan menunggu sampai ditutup

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        
        // Event handler untuk tombol btnNaiksidi
        btnSejarahGereja.setOnAction(event -> {
            try {
                // Load file fxml untuk form naik sidi
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CrudSejarahGereja.fxml"));
                Parent root = loader.load();
                
                          
                // Inisialisasi scene
                Scene scene = new Scene(root);

                // Set stage untuk form naik sidi
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL); // Membuat stage menjadi modal
                stage.setTitle("Sejarah Gereja");
                stage.setScene(scene);
                stage.showAndWait(); // Menampilkan form dan menunggu sampai ditutup

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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

    }

    public Label getWelcomeLabel() {
        return welcomeLabel;
    }
}
