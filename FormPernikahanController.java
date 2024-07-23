/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project3;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FormPernikahanController implements Initializable {

    @FXML
    private TableColumn<FormDataPernikahan, String> tbl_CalonSuami;

    @FXML
    private TextField txtCalonSuami;

    @FXML
    private Button crud_updateBtn;

    @FXML
    private TableColumn<FormDataPernikahan, String> tbl_TempatPernikahan;
    
    @FXML
    private TableColumn<FormDataPernikahan, String> tbl_status;
    

    @FXML
    private TextField txtCalonIstri;

    @FXML
    private Button crud_readBtn;

    @FXML
    private TableView<FormDataPernikahan> crud_tableView;

    @FXML
    private ComboBox<String> txtTempatPernikahan;

    @FXML
    private TableColumn<FormDataPernikahan, String> tbl_tanggal;

    @FXML
    private TextField txtTanggal;

    @FXML
    private Button crud_deleteBtn;

    @FXML
    private TableColumn<FormDataPernikahan, String> tbl_CalonIstri;

    @FXML
    private Button crud_addBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    public void pernikahanAddBtn() {
        connect = database.connect();

        try {
            if (txtCalonSuami.getText().isEmpty()
                    || txtCalonIstri.getText().isEmpty()
                    || txtTanggal.getText().isEmpty()
                    || txtTempatPernikahan.getSelectionModel().getSelectedItem() == null) {
                showAlert(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
            } else {
                String checkData = "SELECT nama_Calonsuami FROM pernikahan WHERE nama_Calonsuami = ?  AND user_id = ?";
                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, txtCalonSuami.getText());
                prepare.setInt(2, this.userId); // Use the userId obtained from FXMLDocumentController
                result = prepare.executeQuery();

                if (result.next()) {
                    showAlert(Alert.AlertType.ERROR, "Error Message", null, "Nama Calon Suami " + txtCalonSuami.getText() + " is already taken");
                } else {
                    String insertData = "INSERT INTO pernikahan(nama_Calonsuami,nama_Calonistri,tanggal_pernikahan,tempat_pernikahan,user_id,status)"
                            + " VALUES(?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, txtCalonSuami.getText());
                    prepare.setString(2, txtCalonIstri.getText());
                    prepare.setString(3, txtTanggal.getText());
                    prepare.setString(4, txtTempatPernikahan.getSelectionModel().getSelectedItem());
                    prepare.setInt(5, userId);
                    prepare.setString(6, "Belum Dikonfirmasi"); // Menetapkan status "Belum Dikonfirmasi"
                    prepare.executeUpdate();

                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Added!");

                    pernikahanShowData();
                 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void pernikahanUpdateBtn() {
        connect = database.connect();

        try {
            if (txtCalonSuami.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE nama_Calonsuami: " + txtCalonSuami.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    String updateData = "UPDATE pernikahan SET nama_Calonistri = ?,"
                            + " tanggal_pernikahan = ?, tempat_pernikahan = ? WHERE nama_Calonsuami = ? AND user_id = ?";
                    prepare = connect.prepareStatement(updateData);

                    prepare.setString(1, txtCalonIstri.getText());
                    prepare.setString(2, txtTanggal.getText());
                    prepare.setString(3, txtTempatPernikahan.getSelectionModel().getSelectedItem());
                    prepare.setString(4, txtCalonSuami.getText());
                    prepare.setInt(5, userId);

                    prepare.executeUpdate();

                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Updated!");

                    pernikahanShowData();
                   
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Cancelled.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
@FXML
public void pernikahanDeleteBtn() {
    connect = database.connect();

    try {
        if (txtCalonSuami.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE nama_Calonsuami: " + txtCalonSuami.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                String deleteData = "DELETE FROM pernikahan WHERE nama_Calonsuami = ? AND user_id = ?";
                prepare = connect.prepareStatement(deleteData);
                prepare.setString(1, txtCalonSuami.getText());
                prepare.setInt(2, userId);
                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Deleted!");

                pernikahanShowData();
          
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Cancelled.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    

    @FXML
    public void pernikahanGerejaList() {
        ObservableList<String> gList = FXCollections.observableArrayList("HKBP PALMARUM TARUTUNG", "GEREJA B", "GEREJA C");
        txtTempatPernikahan.setItems(gList);
    }

    public ObservableList<FormDataPernikahan> getFormDataPernikahan() {
    ObservableList<FormDataPernikahan> listData = FXCollections.observableArrayList();
    String selectData = "SELECT * FROM pernikahan WHERE user_id = ?";

    connect = database.connect();

    try {
        prepare = connect.prepareStatement(selectData);
        prepare.setInt(1, userId);
        result = prepare.executeQuery();

        while (result.next()) {
            FormDataPernikahan pData = new FormDataPernikahan(
                    result.getInt("user_id"),
                    result.getString("nama_Calonsuami"),
                    result.getString("nama_Calonistri"),
                    result.getString("tanggal_pernikahan"),
                    result.getString("tempat_pernikahan"),
                    result.getString("status")
            );
            listData.add(pData);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listData;
}


    @FXML
    public void pernikahanShowData() {
        ObservableList<FormDataPernikahan> formDataPernikahan = getFormDataPernikahan();

        tbl_CalonSuami.setCellValueFactory(new PropertyValueFactory<>("nama_Calonsuami"));
        tbl_CalonIstri.setCellValueFactory(new PropertyValueFactory<>("nama_Calonistri"));
        tbl_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_pernikahan"));
        tbl_TempatPernikahan.setCellValueFactory(new PropertyValueFactory<>("tempat_pernikahan"));
        tbl_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        crud_tableView.setItems(formDataPernikahan);
    }

    @FXML
    public void pernikahanSelectData() {
        FormDataPernikahan pData = crud_tableView.getSelectionModel().getSelectedItem();

        if (pData != null) {
            txtCalonSuami.setText(pData.getNama_Calonsuami());
            txtCalonIstri.setText(pData.getNama_Calonistri());
            txtTanggal.setText(pData.getTanggal_pernikahan());
            txtTempatPernikahan.getSelectionModel().select(pData.getTempat_pernikahan());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
public void pernikahanReadBtn() {
    ObservableList<FormDataPernikahan> formDataPernikahan = getFormDataPernikahan();

    if (formDataPernikahan.isEmpty()) {
        showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "No data found for the current user.");
    } else {
        tbl_CalonSuami.setCellValueFactory(new PropertyValueFactory<>("nama_Calonsuami"));
        tbl_CalonIstri.setCellValueFactory(new PropertyValueFactory<>("nama_Calonistri"));
        tbl_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_pernikahan"));
        tbl_TempatPernikahan.setCellValueFactory(new PropertyValueFactory<>("tempat_pernikahan"));
        tbl_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        crud_tableView.setItems(formDataPernikahan);
    }
}


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        pernikahanGerejaList();
        pernikahanShowData();
    }
}

