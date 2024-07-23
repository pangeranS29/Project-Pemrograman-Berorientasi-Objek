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
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CrudSejarahGerejaController implements Initializable {
    @FXML
    private TextArea textAreaDeskripsi;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDataToTextArea();
    }

    private void loadDataToTextArea() {
        try {
            // Koneksi ke database
            connect = database.connect();

            // Query untuk mengambil data dari tabel SejarahGereja
            String query = "SELECT * FROM sejarahgereja";
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            // Mengisi TextArea dengan data dari database
            StringBuilder deskripsi = new StringBuilder();
            while (result.next()) {
                String deskripsiData = result.getString("deskripsi");
                deskripsi.append(deskripsiData).append("\n\n");
            }
            textAreaDeskripsi.setText(deskripsi.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

