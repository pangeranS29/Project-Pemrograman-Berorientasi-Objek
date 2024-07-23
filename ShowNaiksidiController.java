package project3;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ShowNaiksidiController implements Initializable {
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private TableColumn<ShowDataNaiksidi, String> col_tanggal;
    @FXML
    private TableColumn<ShowDataNaiksidi, String> col_ayah;
    @FXML
    private TableColumn<ShowDataNaiksidi, Integer> col_baptis;
    @FXML
    private TableColumn<ShowDataNaiksidi, String> col_nama;
    @FXML
    private TableView<ShowDataNaiksidi> tblViewbaptis;
    @FXML
    private TableColumn<ShowDataNaiksidi, String> col_ibu;
    @FXML
    private TableColumn<ShowDataNaiksidi, String> col_jk;
    @FXML
    private TableColumn<ShowDataNaiksidi, String> col_gereja;
    @FXML
    private TableColumn<ShowDataNaiksidi, String> col_status;
    @FXML
    private TableColumn<ShowDataNaiksidi, Integer> col_user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = database.connect();
        loadBaptisData();

        col_status.setCellFactory(new Callback<TableColumn<ShowDataNaiksidi, String>, TableCell<ShowDataNaiksidi, String>>() {
            @Override
            public TableCell<ShowDataNaiksidi, String> call(TableColumn<ShowDataNaiksidi, String> param) {
                return new StatusCell();
            }
        });
    }

    private void loadBaptisData() {
        try {
            String query = "SELECT * FROM naiksidi";
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            ObservableList<ShowDataNaiksidi> naiksidiData = FXCollections.observableArrayList();

            while (result.next()) {
                ShowDataNaiksidi baptis = new ShowDataNaiksidi(
                        result.getInt("id_naiksidi"),
                        result.getInt("user_id"),
                        result.getString("nama_calon"),
                        result.getString("jenis_kelamin"),
                        result.getString("tanggal_lahir"),
                        result.getString("nama_ayah"),
                        result.getString("nama_ibu"),
                        result.getString("gereja"),
                        result.getString("status")
                );
                naiksidiData.add(baptis);
            }

            tblViewbaptis.setItems(naiksidiData);

            col_baptis.setCellValueFactory(new PropertyValueFactory<>("id_naiksidi"));
            col_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            col_nama.setCellValueFactory(new PropertyValueFactory<>("nama_calon"));
            col_jk.setCellValueFactory(new PropertyValueFactory<>("jenis_kelamin"));
            col_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal_lahir"));
            col_ayah.setCellValueFactory(new PropertyValueFactory<>("nama_ayah"));
            col_ibu.setCellValueFactory(new PropertyValueFactory<>("nama_ibu"));
            col_gereja.setCellValueFactory(new PropertyValueFactory<>("gereja"));
            col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleStatusClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Satu kali klik
            ShowDataNaiksidi selectedData = tblViewbaptis.getSelectionModel().getSelectedItem();
            if (selectedData != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationDialogNaiksidi.fxml"));
                    Parent root = loader.load();
                    ConfirmationDialogNaiksidiController controller = loader.getController();
                    controller.setSelectedId(selectedData.getId_naiksidi());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Konfirmasi Data Naiksidi");
                    stage.showAndWait();

                    // Memuat ulang data baptis setelah update
                    loadBaptisData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class StatusCell extends TableCell<ShowDataNaiksidi, String> {
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
