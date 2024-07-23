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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ShowSejarahGerejaController implements Initializable {
    @FXML
    private TextField textFieldId;
    @FXML
    private TextArea textAreaDeskripsi;
    @FXML
    private Button buttonCreate;
    @FXML
    private Button buttonRead;
    @FXML
    private Button buttonUpdate;
    @FXML
    private Button buttonDelete;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inisialisasi events untuk tombol
        buttonCreate.setOnAction(event -> createData());
        buttonRead.setOnAction(event -> readData());
        buttonUpdate.setOnAction(event -> updateData());
        buttonDelete.setOnAction(event -> deleteData());
    }

    private void createData() {
        try {
            // Koneksi ke database
            connect = database.connect();

            // Query untuk menyimpan data baru
            String query = "INSERT INTO sejarahgereja (deskripsi) VALUES (?)";
            prepare = connect.prepareStatement(query);
            prepare.setString(1, textAreaDeskripsi.getText());
            prepare.executeUpdate();

            // Reset input fields
            textFieldId.clear();
            textAreaDeskripsi.clear();

            showNotification("Data berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
            showNotification("Terjadi kesalahan saat menambahkan data.");
        }
    }

    private void readData() {
        try {
            // Koneksi ke database
            connect = database.connect();

            // Query untuk mengambil data dari tabel SejarahGereja
            String query = "SELECT * FROM sejarahgereja WHERE id = ?";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, Integer.parseInt(textFieldId.getText()));
            result = prepare.executeQuery();

            if (result.next()) {
                int id = result.getInt("id");
                String deskripsi = result.getString("deskripsi");
                textFieldId.setText(String.valueOf(id));
                textAreaDeskripsi.setText(deskripsi);
                showNotification("Data berhasil dibaca!");
            } else {
                showNotification("Data tidak ditemukan!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showNotification("Terjadi kesalahan saat membaca data.");
        }
    }

    private void updateData() {
        try {
            // Koneksi ke database
            connect = database.connect();

            // Query untuk memperbarui data
            String query = "UPDATE sejarahgereja SET deskripsi = ? WHERE id = ?";
            prepare = connect.prepareStatement(query);
            prepare.setString(1, textAreaDeskripsi.getText());
            prepare.setInt(2, Integer.parseInt(textFieldId.getText()));
            prepare.executeUpdate();

            // Reset input fields
            textFieldId.clear();
            textAreaDeskripsi.clear();

            showNotification("Data berhasil diperbarui!");
        } catch (SQLException e) {
            e.printStackTrace();
            showNotification("Terjadi kesalahan saat memperbarui data.");
        }
    }

    private void deleteData() {
        try {
            // Koneksi ke database
            connect = database.connect();

            // Query untuk menghapus data
            String query = "DELETE FROM sejarahgereja WHERE id = ?";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, Integer.parseInt(textFieldId.getText()));
            prepare.executeUpdate();

            // Reset input fields
            textFieldId.clear();
            textAreaDeskripsi.clear();

            showNotification("Data berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
            showNotification("Terjadi kesalahan saat menghapus data.");
        }
    }

    private void showNotification(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Notifikasi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
