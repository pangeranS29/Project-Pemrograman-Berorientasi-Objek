package project3;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmationDialogController implements Initializable {
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnReject;

    private int selectedId;
    private Connection connect;
    private PreparedStatement prepare;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect = database.connect();

        btnConfirm.setOnAction(event -> {
            confirmBaptis();
            ((Stage) btnConfirm.getScene().getWindow()).close();
        });

        btnReject.setOnAction(event -> {
            rejectBaptis();
            ((Stage) btnReject.getScene().getWindow()).close();
        });
    }

    public void setSelectedId(int id) {
        this.selectedId = id;
    }

    private void confirmBaptis() {
        try {
            String query = "UPDATE baptis SET status = 'Sudah Dikonfirmasi' WHERE id_baptis = ?";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, selectedId);
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rejectBaptis() {
        try {
            String query = "UPDATE baptis SET status = 'Ditolak' WHERE id_baptis = ?";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, selectedId);
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
