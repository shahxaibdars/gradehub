package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class FacultyDashboardController {

    @FXML
    private void openUploadMarks(ActionEvent event) {
        loadScreen("/com/gradehub/uploadMarks.fxml", event);
    }

    @FXML
    private void openUploadAttendance(ActionEvent event) {
        loadScreen("/com/gradehub/uploadAttendance.fxml", event);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        loadScreen("/com/gradehub/login.fxml", event);
    }

    private void loadScreen(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            // Apply stylesheet programmatically
            scene.getStylesheets().add(getClass().getResource("/com/gradehub/css/styles.css").toExternalForm());

            // Get the current stage using the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
