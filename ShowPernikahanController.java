/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package project3;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ShowPernikahanController implements Initializable {
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    
    @FXML
    private TableColumn<ShowDataPernikahan, String> col_tempat;

    @FXML
    private TableView<ShowDataPernikahan> tblViewpernikahan;

    @FXML
    private TableColumn<ShowDataPernikahan, String> col_tanggal;

    @FXML
    private TableColumn<ShowDataPernikahan, Integer> col_idpernikahan;

    @FXML
    private TableColumn<ShowDataPernikahan, String> col_istri;

    @FXML
    private TableColumn<ShowDataPernikahan, String> col_suami;

    @FXML
    private TableColumn<ShowDataPernikahan, String> col_status;

    @FXML
    private TableColumn<ShowDataPernikahan, Integer> col_user;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = database.connect();
        loadBaptisData();

        col_status.setCellFactory(new Callback<TableColumn<ShowDataPernikahan, String>, TableCell<ShowDataPernikahan, String>>() {
            @Override
            public TableCell<ShowDataPernikahan, String> call(TableColumn<ShowDataPernikahan, String> param) {
                return new StatusCell();
            }
        });
    }

    private void loadBaptisData() {
        try {
            String query = "SELECT * FROM pernikahan";
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            ObservableList<ShowDataPernikahan> pernikahanData = FXCollections.observableArrayList();

            while (result.next()) {
                ShowDataPernikahan pernikahan = new ShowDataPernikahan(
                        result.getInt("id_pernikahan"),
                        result.getInt("user_id"),
                        result.getString("nama_Calonsuami"),
                        result.getString("nama_Calonistri"),
                        result.getString("tanggal_pernikahan"),
                        result.getString("tempat_pernikahan"),
                        result.getString("status")
                );
                pernikahanData.add(pernikahan);
            }

            tblViewpernikahan.setItems(pernikahanData);

            col_idpernikahan.setCellValueFactory(new PropertyValueFactory<>("id_pernikahan"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            col_suami.setCellValueFactory(new PropertyValueFactory<>("nama_Calonsuami"));
            col_istri.setCellValueFactory(new PropertyValueFactory<>("nama_Calonistri"));
            col_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_pernikahan"));
            col_tempat.setCellValueFactory(new PropertyValueFactory<>("tempat_pernikahan"));
            col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStatusClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Satu kali klik
            ShowDataPernikahan selectedData = tblViewpernikahan.getSelectionModel().getSelectedItem();
            if (selectedData != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationDialogPernikahan.fxml"));
                    Parent root = loader.load();
                    ConfirmationDialogPernikahanController  controller = loader.getController();
                    controller.setSelectedId(selectedData.getId_pernikahan());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Konfirmasi Data Pernikahan");
                    stage.showAndWait();

                    // Memuat ulang data baptis setelah update
                    loadBaptisData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class StatusCell extends TableCell<ShowDataPernikahan, String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(new Label(item));
                setOnMouseClicked(event -> handleStatusClick(event));
            } else {
                setGraphic(null);
            }
        }
    } 
    
}
