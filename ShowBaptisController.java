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

public class ShowBaptisController implements Initializable {
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private TableColumn<ShowDataBaptis, String> col_tanggal;
    @FXML
    private TableColumn<ShowDataBaptis, String> col_ayah;
    @FXML
    private TableColumn<ShowDataBaptis, Integer> col_baptis;
    @FXML
    private TableColumn<ShowDataBaptis, String> col_nama;
    @FXML
    private TableView<ShowDataBaptis> tblViewbaptis;
    @FXML
    private TableColumn<ShowDataBaptis, String> col_ibu;
    @FXML
    private TableColumn<ShowDataBaptis, String> col_jk;
    @FXML
    private TableColumn<ShowDataBaptis, String> col_gereja;
    @FXML
    private TableColumn<ShowDataBaptis, String> col_status;
    @FXML
    private TableColumn<ShowDataBaptis, Integer> col_user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = database.connect();
        loadBaptisData();

        col_status.setCellFactory(new Callback<TableColumn<ShowDataBaptis, String>, TableCell<ShowDataBaptis, String>>() {
            @Override
            public TableCell<ShowDataBaptis, String> call(TableColumn<ShowDataBaptis, String> param) {
                return new StatusCell();
            }
        });
    }

    private void loadBaptisData() {
        try {
            String query = "SELECT * FROM baptis";
            prepare = connect.prepareStatement(query);
            result = prepare.executeQuery();

            ObservableList<ShowDataBaptis> baptisData = FXCollections.observableArrayList();

            while (result.next()) {
                ShowDataBaptis baptis = new ShowDataBaptis(
                        result.getInt("id_baptis"),
                        result.getInt("user_id"),
                        result.getString("nama_calon"),
                        result.getString("jenis_kelamin"),
                        result.getString("tanggal_lahir"),
                        result.getString("nama_ayah"),
                        result.getString("nama_ibu"),
                        result.getString("gereja"),
                        result.getString("status")
                );
                baptisData.add(baptis);
            }

            tblViewbaptis.setItems(baptisData);

            col_baptis.setCellValueFactory(new PropertyValueFactory<>("id_baptis"));
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
            ShowDataBaptis selectedData = tblViewbaptis.getSelectionModel().getSelectedItem();
            if (selectedData != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationDialog.fxml"));
                    Parent root = loader.load();
                    ConfirmationDialogController controller = loader.getController();
                    controller.setSelectedId(selectedData.getId_baptis());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Konfirmasi Data Baptis");
                    stage.showAndWait();

                    // Memuat ulang data baptis setelah update
                    loadBaptisData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class StatusCell extends TableCell<ShowDataBaptis, String> {
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
