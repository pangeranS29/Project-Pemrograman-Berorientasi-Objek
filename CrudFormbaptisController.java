package project3;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import java.sql.SQLException;


public class CrudFormbaptisController implements Initializable {
    
    @FXML
    private Button crud_updateBtn;
    
    @FXML
    private TextField txtNama;
    
    @FXML
    private TextField txtTanggalLahir;
    
    @FXML
    private TextField txtNamaAyah;
    
    @FXML
    private TableColumn<CrudDatabaptis, String> tbl_jk;
    
    @FXML
    private Button crud_readBtn;
    
    @FXML
    private TableView<CrudDatabaptis> crud_tableView;
    
    @FXML
    private TableColumn<CrudDatabaptis, String> tbl_tanggal;
    
    @FXML
    private Button crud_deleteBtn;
    
    @FXML
    private TableColumn<CrudDatabaptis, String> tbl_ibu;
    
    @FXML
    private TableColumn<CrudDatabaptis, String> tbl_gereja;
    
      @FXML
    private TableColumn<CrudDatabaptis, String> tbl_status;
    
    @FXML
    private TextField txtNamaIbu;
    
    @FXML
    private Button crud_addBtn;
    
    @FXML
    private TableColumn<CrudDatabaptis, String> tbl_Nama;
    
    @FXML
    private TableColumn<CrudDatabaptis, String> tbl_ayah;
    
    @FXML
    private ComboBox<String> txtJenisKelamin;
    
    @FXML
    private ComboBox<String> txtGereja;
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Alert alert;
    
    private int userId; // Tambahkan properti userId
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getUserId() {
        return this.userId;
    }
    
    @FXML
    public void baptisAddBtn() {
        connect = database.connect();
        
        try {
            if (txtNama.getText().isEmpty()
                    || txtJenisKelamin.getSelectionModel().getSelectedItem() == null
                    || txtTanggalLahir.getText().isEmpty()
                    || txtNamaAyah.getText().isEmpty()
                    || txtNamaIbu.getText().isEmpty()
                    || txtGereja.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String checkData = "SELECT nama_calon FROM baptis WHERE nama_calon = ? AND user_id = ?";
                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, txtNama.getText());
                prepare.setInt(2, this.userId); // Use the userId obtained from FXMLDocumentController
                result = prepare.executeQuery();
                
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Nama Calon " + txtNama.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO baptis(user_id, nama_calon, jenis_kelamin, tanggal_lahir, nama_ayah, nama_ibu, gereja, status)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
prepare = connect.prepareStatement(insertData);
prepare.setInt(1, this.userId);
prepare.setString(2, txtNama.getText());
prepare.setString(3, txtJenisKelamin.getSelectionModel().getSelectedItem());
prepare.setString(4, txtTanggalLahir.getText());
prepare.setString(5, txtNamaAyah.getText());
prepare.setString(6, txtNamaIbu.getText());
prepare.setString(7, txtGereja.getSelectionModel().getSelectedItem());
prepare.setString(8, "Belum Dikonfirmasi"); // Menetapkan status "Belum Dikonfirmasi"
prepare.executeUpdate();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    
                    baptisShowData();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   @FXML
public void baptisUpdateBtn() {
    connect = database.connect();
    
    try {
        if (txtNama.getText().isEmpty()
            || txtJenisKelamin.getSelectionModel().getSelectedItem() == null
            || txtTanggalLahir.getText().isEmpty()
            || txtNamaAyah.getText().isEmpty()
            || txtNamaIbu.getText().isEmpty()
            || txtGereja.getSelectionModel().getSelectedItem() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            CrudDatabaptis selectedData = crud_tableView.getSelectionModel().getSelectedItem();
            if (selectedData != null) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE nama_calon:" + selectedData.getNama_calon() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    String updateData = "UPDATE baptis SET jenis_kelamin = ?, tanggal_lahir = ?, nama_ayah = ?,"
                                       + " nama_ibu = ?, gereja = ? WHERE nama_calon = ? AND user_id = ?";
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, txtJenisKelamin.getSelectionModel().getSelectedItem());
                    prepare.setString(2, txtTanggalLahir.getText());
                    prepare.setString(3, txtNamaAyah.getText());
                    prepare.setString(4, txtNamaIbu.getText());
                    prepare.setString(5, txtGereja.getSelectionModel().getSelectedItem());
                    prepare.setString(6, selectedData.getNama_calon());
                    prepare.setInt(7, this.userId); // Use the userId obtained from FXMLDocumentController
                    prepare.executeUpdate();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    baptisShowData();
                   
                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a row to update.");
                alert.showAndWait();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}



        @FXML
    public void baptisDeleteBtn() {
        connect = database.connect();
        
        try {
            if (txtNama.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE nama_calon: " + txtNama.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    String deleteData = "DELETE FROM baptis WHERE nama_calon = ? AND user_id = ?";
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, txtNama.getText());
                    prepare.setInt(2, this.userId); // Use the userId obtained from FXMLDocumentController
                    prepare.executeUpdate();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    baptisShowData();
                   
                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
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
    
     public ObservableList<CrudDatabaptis> CrudListDatabaptis() {
        ObservableList<CrudDatabaptis> listData = FXCollections.observableArrayList();
        String selectData = "SELECT * FROM baptis WHERE user_id = ?";
        
        connect = database.connect();
        
        try {
            PreparedStatement prepare = connect.prepareStatement(selectData);
            prepare.setInt(1, this.userId); // Use the userId obtained from FXMLDocumentController
            ResultSet result = prepare.executeQuery();
            
            while (result.next()) {
                int userId = result.getInt("user_id");
                String nama_calon = result.getString("nama_calon");
                String jenis_kelamin = result.getString("jenis_kelamin");
                String tanggal_lahir = result.getString("tanggal_lahir");
                String nama_ayah = result.getString("nama_ayah");
                String nama_ibu = result.getString("nama_ibu");
                String gereja = result.getString("gereja");
                String status = result.getString("status"); // Mengambil data status dari database
                
                CrudDatabaptis cData = new CrudDatabaptis(userId, nama_calon, jenis_kelamin, tanggal_lahir, nama_ayah, nama_ibu, gereja, status);
                listData.add(cData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    @FXML
    public void baptisShowData() {
        ObservableList<CrudDatabaptis> CrudDatabaptis = CrudListDatabaptis();
        tbl_Nama.setCellValueFactory(new PropertyValueFactory<>("nama_calon"));
        tbl_jk.setCellValueFactory(new PropertyValueFactory<>("jenis_kelamin"));
        tbl_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_lahir"));
        tbl_ayah.setCellValueFactory(new PropertyValueFactory<>("nama_ayah"));
        tbl_ibu.setCellValueFactory(new PropertyValueFactory<>("nama_ibu"));
        tbl_gereja.setCellValueFactory(new PropertyValueFactory<>("gereja"));
        tbl_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        crud_tableView.setItems(CrudDatabaptis);
    }
    
    
    @FXML
    public void baptisSelectData() {
        CrudDatabaptis cData = crud_tableView.getSelectionModel().getSelectedItem();
        
        if (cData != null) {
            txtNama.setText(cData.getNama_calon());
            txtTanggalLahir.setText(cData.getTanggal_lahir());
            txtNamaAyah.setText(cData.getNama_ayah());
            txtNamaIbu.setText(cData.getNama_ibu());
        }
    }
    
    @FXML
public void baptisReadBtn() {
    ObservableList<CrudDatabaptis> CrudDatabaptis = CrudListDatabaptis();
    
    if (CrudDatabaptis.isEmpty()) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("No data found for the current user.");
        alert.showAndWait();
    } else {
        tbl_Nama.setCellValueFactory(new PropertyValueFactory<>("nama_calon"));
        tbl_jk.setCellValueFactory(new PropertyValueFactory<>("jenis_kelamin"));
        tbl_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_lahir"));
        tbl_ayah.setCellValueFactory(new PropertyValueFactory<>("nama_ayah"));
        tbl_ibu.setCellValueFactory(new PropertyValueFactory<>("nama_ibu"));
        tbl_gereja.setCellValueFactory(new PropertyValueFactory<>("gereja"));
        tbl_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        crud_tableView.setItems(CrudDatabaptis);
    }
}

   

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        baptisJKList();
        baptisGerejaList();
        baptisShowData();
        
        
    }
}
