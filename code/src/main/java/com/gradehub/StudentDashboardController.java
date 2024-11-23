package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class StudentDashboardController {

    @FXML
    private void openViewMarks(ActionEvent event) {
        loadScreen("/com/gradehub/viewMarks.fxml", event);
    }

    @FXML
    private void openViewAttendance(ActionEvent event) {
        loadScreen("/com/gradehub/viewAttendance.fxml", event);
    }

    @FXML
    private void openRegisterCourses(ActionEvent event) {
        loadScreen("/com/gradehub/registerCourses.fxml", event);
    }

    @FXML
    private void openWithdrawCourse(ActionEvent event) {
        loadScreen("/com/gradehub/withdrawCourses.fxml", event);
    }

    @FXML
    private void openPrintFeeChallan(ActionEvent event) {
        loadScreen("/com/gradehub/printFeeChallan.fxml", event);
    }

    @FXML
    private void openPrintAdmitCard(ActionEvent event) {
        loadScreen("/com/gradehub/printAdmitCard.fxml", event);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        loadScreen("/com/gradehub/login.fxml", event);
    }

    // Static method to load screens
    public static void loadScreen(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(StudentDashboardController.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            // Apply stylesheet programmatically
            scene.getStylesheets().add(StudentDashboardController.class.getResource("/com/gradehub/css/styles.css").toExternalForm());

            // Set the new scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
