package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class FacultyDashboardController {
    
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @FXML
    private void openUploadMarks(ActionEvent event) {
        loadScreen("/com/gradehub/uploadMarks.fxml", event, userId);
    }

    @FXML
    private void openUploadAttendance(ActionEvent event) {
        loadScreen("/com/gradehub/uploadAttendance.fxml", event, userId);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        loadScreen("/com/gradehub/login.fxml", event, userId);
    }

    public static void loadScreen(String fxmlPath, ActionEvent event, String userId) {
        try {
            FXMLLoader loader = new FXMLLoader(FacultyDashboardController.class.getResource(fxmlPath));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 800, 600);

            // Get the controller of the next screen
            Object controller = loader.getController();
    
            // Pass the userId to the appropriate dashboard controller
            if (controller instanceof UploadMarksController) {
                ((UploadMarksController) controller).setUserId(userId);
            } else if (controller instanceof UploadAttendanceController) {
                ((UploadAttendanceController) controller).setUserId(userId);
            } else if (controller instanceof FacultyDashboardController) {
                ((FacultyDashboardController) controller).setUserId(userId);
            }

            // Apply stylesheet programmatically
            scene.getStylesheets().add(FacultyDashboardController.class.getResource("/com/gradehub/css/styles.css").toExternalForm());
            //scene.getStylesheets().add(getClass().getResource("/com/gradehub/css/styles.css").toExternalForm());

            // Get the current stage using the event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
