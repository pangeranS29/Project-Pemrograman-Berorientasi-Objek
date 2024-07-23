/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package project3;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Hyperlink login_acc;

    @FXML
    private TextField su_email;

    @FXML
    private Label logo1;

    @FXML
    private PasswordField su_password;

    @FXML
    private Button signup_btn;

    @FXML
    private TextField su_username;

    @FXML
    private Button login_btn;

    @FXML
    private FontAwesomeIcon exit;

    @FXML
    private PasswordField password;

    @FXML
    private Label logo11;

    @FXML
    private Label logo;

    @FXML
    private TextField username;

    @FXML
    private Hyperlink create_acc;

    @FXML
    private AnchorPane signup_form;
    
    
    @FXML
    private AnchorPane login_form;
    
    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
      
    
     private int userId; // Tambahkan properti userId

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    public Connection connectDb(){
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost/projectpbo1","root","");
            return connect;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null; 
    }
      
    public void login(ActionEvent event) {
    connect = connectDb();
    try {
        // Coba login sebagai admin
        String sqlAdmin = "SELECT * FROM admin WHERE username = ? AND password = ?";
        statement = connect.prepareStatement(sqlAdmin);
        statement.setString(1, username.getText());
        statement.setString(2, password.getText());
        result = statement.executeQuery();

        if (result.next()) {
            // Tampilkan notifikasi login berhasil
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Login Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Anda berhasil login sebagai admin.");
            alert.showAndWait(); // Tunggu sampai pengguna menekan tombol OK

            // Redirect to admin page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardAdmin.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) username.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Admin Dashboard");
            stage.show();
        } else {
            // Coba login sebagai user
            String sqlUser = "SELECT * FROM user WHERE username = ? AND password = ?";
            statement = connect.prepareStatement(sqlUser);
            statement.setString(1, username.getText());
            statement.setString(2, password.getText());
            result = statement.executeQuery();

            if (result.next()) {
                // Tampilkan notifikasi login berhasil
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Login Berhasil");
                alert.setHeaderText(null);
                alert.setContentText("Anda berhasil login !!!.");
                alert.showAndWait(); // Tunggu sampai pengguna menekan tombol OK

                // Redirect to user page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardUser.fxml"));
                Parent root = loader.load();

                // Get the DashboardUserController
                DashboardUserController dashboardUserController = loader.getController();

                // Kirim user_id ke DashboardUserController
                dashboardUserController.setCrudFormbaptisController(result.getInt("id_user"));

                Scene scene = new Scene(root);
                Stage stage = (Stage) username.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("User Dashboard");
                stage.show();
            } else {
                // Tampilkan pesan error jika username atau password salah
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password!");
                alert.showAndWait();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}




    public void signup(ActionEvent event) {
        connect = connectDb();
        try {
            String sql = "INSERT INTO USER (username, PASSWORD, email) VALUES (?, ?, ?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, su_username.getText());
            statement.setString(2, su_password.getText());
            statement.setString(3, su_email.getText());
            statement.executeUpdate(); // Menggunakan executeUpdate untuk eksekusi INSERT

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Register");
            alert.setHeaderText(null);
            alert.setContentText("Akun Berhasil Dibuat");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Gagal membuat akun. Periksa kembali input Anda.");
            alert.showAndWait();
        }
    }

    public void changeForm(ActionEvent event){
        if(event.getSource()== login_acc){
            signup_form.setVisible(false);
            login_form.setVisible(true);
        }else if(event.getSource()== create_acc){
            signup_form.setVisible(true);
            login_form.setVisible(false);
        }
    }
      
    public void exit(){
        System.exit(0);
    }
      
    public void textfield(MouseEvent event){
        if(event.getSource()== username){
            username.setStyle("-fx-background-color:#fff;"+ "-fx-border-width:3px;");
            password.setStyle("-fx-background-color:#eef3ff;"+"-fx-border-width:1px");
        }else if(event.getSource()== password){
            username.setStyle("-fx-background-color:#eef3ff;" +"-fx-border-width:1px");
            password.setStyle("-fx-background-color:#fff;" +"-fx-border-width:3px");
        }
    }
        public void su_textfield(MouseEvent event) {
        if (event.getSource() == su_username) {
            su_username.setStyle("-fx-background-color:#fff;" + "-fx-border-width:3px;");
            su_password.setStyle("-fx-background-color:#eef3ff;" + "-fx-border-width:1px");
            su_email.setStyle("-fx-background-color:#eef3ff;" + "-fx-border-width:1px");
        } else if (event.getSource() == su_password) {
            su_username.setStyle("-fx-background-color:#eef3ff;" + "-fx-border-width:1px;");
            su_password.setStyle("-fx-background-color:#fff;" + "-fx-border-width:3px");
            su_email.setStyle("-fx-background-color:#eef3ff;" + "-fx-border-width:1px");
        } else if (event.getSource() == su_email) {
            su_username.setStyle("-fx-background-color:#eef3ff;" + "-fx-border-width:1px;");
            su_password.setStyle("-fx-background-color:#eef3ff;" + "-fx-border-width:1px");
            su_email.setStyle("-fx-background-color:#fff;" + "-fx-border-width:3px");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        su_email.setStyle("-fx-background-color:#fff;" + "-fx-border-width:3px");
        username.setStyle("-fx-background-color:#fff;" + "-fx-border-width:3px;");
    }
}

