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

public class FormNaiksidiController implements Initializable {
    
     @FXML
    private TableColumn<FormDataNaikSidi, String> tbl_status;


    @FXML
    private Button crud_updateBtn;

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtTanggalLahir;

    @FXML
    private TextField txtNamaAyah;

    @FXML
    private TableColumn<FormDataNaikSidi, String> tbl_jk;

    @FXML
    private Button crud_readBtn;

    @FXML
    private TableView<FormDataNaikSidi> crud_tableView;

    @FXML
    private TableColumn<FormDataNaikSidi, String> tbl_tanggal;

    @FXML
    private Button crud_deleteBtn;

    @FXML
    private TableColumn<FormDataNaikSidi, String> tbl_ibu;

    @FXML
    private TableColumn<FormDataNaikSidi, String> tbl_gereja;

    @FXML
    private TextField txtNamaIbu;

    @FXML
    private Button crud_addBtn;

    @FXML
    private TableColumn<FormDataNaikSidi, String> tbl_Nama;

    @FXML
    private TableColumn<FormDataNaikSidi, String> tbl_ayah;

    @FXML
    private ComboBox<String> txtJenisKelamin;

    @FXML
    private ComboBox<String> txtGereja;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    public void naiksidiAddBtn() {
        connect = database.connect();

        try {
            if (txtNama.getText().isEmpty()
                    || txtJenisKelamin.getSelectionModel().getSelectedItem() == null
                    || txtTanggalLahir.getText().isEmpty()
                    || txtNamaAyah.getText().isEmpty()
                    || txtNamaIbu.getText().isEmpty()
                    || txtGereja.getSelectionModel().getSelectedItem() == null) {
                showAlert(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
            } else {
                String checkData = "SELECT nama_calon FROM naiksidi WHERE nama_calon = ? AND user_id = ?";
                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, txtNama.getText());
                prepare.setInt(2, userId);
                result = prepare.executeQuery();

                if (result.next()) {
                    showAlert(Alert.AlertType.ERROR, "Error Message", null, "Nama Calon " + txtNama.getText() + " is already taken");
                } else {
                    String insertData = "INSERT INTO naiksidi(nama_calon,jenis_kelamin,tanggal_lahir,nama_ayah,nama_ibu,gereja,user_id,status)"
                            + "VALUES(?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, txtNama.getText());
                    prepare.setString(2, txtJenisKelamin.getSelectionModel().getSelectedItem());
                    prepare.setString(3, txtTanggalLahir.getText());
                    prepare.setString(4, txtNamaAyah.getText());
                    prepare.setString(5, txtNamaIbu.getText());
                    prepare.setString(6, txtGereja.getSelectionModel().getSelectedItem());
                    prepare.setInt(7, userId);
                    prepare.setString(8, "Belum Dikonfirmasi"); // Menetapkan status "Belum Dikonfirmasi"
                    prepare.executeUpdate();

                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Added!");

                    naiksidiShowData();
                   
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void naiksidiUpdateBtn() {
        connect = database.connect();

        try {
            if (txtNama.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE nama_calon:" + txtNama.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    String updateData = "UPDATE naiksidi SET jenis_kelamin = ?, tanggal_lahir = ?, nama_ayah = ?,"
                            + " nama_ibu = ?, gereja = ? WHERE nama_calon = ? AND user_id = ?";
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, txtJenisKelamin.getSelectionModel().getSelectedItem());
                    prepare.setString(2, txtTanggalLahir.getText());
                    prepare.setString(3, txtNamaAyah.getText());
                    prepare.setString(4, txtNamaIbu.getText());
                    prepare.setString(5, txtGereja.getSelectionModel().getSelectedItem());
                    prepare.setString(6, txtNama.getText());
                    prepare.setInt(7, userId);
                    prepare.executeUpdate();

                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Updated!");

                    naiksidiShowData();
                    
                } else {
                    showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Cancelled.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
@FXML
public void naiksidiDeleteBtn() {
    connect = database.connect();

    try {
        if (txtNama.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE nama_calon: " + txtNama.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                String deleteData = "DELETE FROM naiksidi WHERE nama_calon = ? AND user_id = ?";
                prepare = connect.prepareStatement(deleteData);
                prepare.setString(1, txtNama.getText());
                prepare.setInt(2, userId);
                prepare.executeUpdate();

                showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Successfully Deleted!");

                naiksidiShowData();
              
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "Cancelled.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   

    @FXML
    public void baptisJKList() {
        ObservableList<String> jList = FXCollections.observableArrayList("Laki Laki", "Perempuan");
        txtJenisKelamin.setItems(jList);
    }

    @FXML
    public void baptisGerejaList() {
        ObservableList<String> gList = FXCollections.observableArrayList("HKBP PALMARUM TARUTUNG", "GEREJA B", "GEREJA C");
        txtGereja.setItems(gList);
    }

    public ObservableList<FormDataNaikSidi> CrudListDatanaiksidi() {
    ObservableList<FormDataNaikSidi> listData = FXCollections.observableArrayList();
    String selectData = "SELECT * FROM naiksidi WHERE user_id = ?";

    connect = database.connect();

    try {
        prepare = connect.prepareStatement(selectData);
        prepare.setInt(1, userId);
        result = prepare.executeQuery();

        while (result.next()) {
            FormDataNaikSidi cData = new FormDataNaikSidi(
                result.getInt("user_id"),
                result.getString("nama_calon"),
                result.getString("jenis_kelamin"),
                result.getString("tanggal_lahir"),
                result.getString("nama_ayah"),
                result.getString("nama_ibu"),
                result.getString("gereja"),
                    result.getString("status")
                    
            );
            listData.add(cData);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listData;
}


    @FXML
    public void naiksidiShowData() {
        ObservableList<FormDataNaikSidi> FormDataNaikSidi = CrudListDatanaiksidi();

        tbl_Nama.setCellValueFactory(new PropertyValueFactory<>("nama_calon"));
        tbl_jk.setCellValueFactory(new PropertyValueFactory<>("jenis_kelamin"));
        tbl_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_lahir"));
        tbl_ayah.setCellValueFactory(new PropertyValueFactory<>("nama_ayah"));
        tbl_ibu.setCellValueFactory(new PropertyValueFactory<>("nama_ibu"));
        tbl_gereja.setCellValueFactory(new PropertyValueFactory<>("gereja"));
        tbl_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        crud_tableView.setItems(FormDataNaikSidi);
    }

    @FXML
    public void naiksidiSelectData() {
        FormDataNaikSidi cData = crud_tableView.getSelectionModel().getSelectedItem();

        if (cData != null) {
            txtNama.setText(cData.getNama_calon());
            txtTanggalLahir.setText(cData.getTanggal_lahir());
            txtNamaAyah.setText(cData.getNama_ayah());
            txtNamaIbu.setText(cData.getNama_ibu());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        baptisJKList();
        baptisGerejaList();
        naiksidiShowData();
    }

    private void showAlert(Alert.AlertType type, String title, String headerText, String contentText) {
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    @FXML
public void naiksidiReadBtn() {
    ObservableList<FormDataNaikSidi> FormDataNaikSidi = CrudListDatanaiksidi();

    if (FormDataNaikSidi.isEmpty()) {
        showAlert(Alert.AlertType.INFORMATION, "Information Message", null, "No data found for the current user.");
    } else {
        tbl_Nama.setCellValueFactory(new PropertyValueFactory<>("nama_calon"));
        tbl_jk.setCellValueFactory(new PropertyValueFactory<>("jenis_kelamin"));
        tbl_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_lahir"));
        tbl_ayah.setCellValueFactory(new PropertyValueFactory<>("nama_ayah"));
        tbl_ibu.setCellValueFactory(new PropertyValueFactory<>("nama_ibu"));
        tbl_gereja.setCellValueFactory(new PropertyValueFactory<>("gereja"));
        tbl_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        crud_tableView.setItems(FormDataNaikSidi);
    }
}

}
