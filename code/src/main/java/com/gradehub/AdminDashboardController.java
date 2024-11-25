package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AdminDashboardController {

    @FXML
    private void openAddStudentScreen(ActionEvent event) {
        loadScreen("/com/gradehub/addStudent.fxml", event);
    }

    @FXML
    private void openRemoveStudentScreen(ActionEvent event) {
        loadScreen("/com/gradehub/removeStudent.fxml", event);
    }

    @FXML
    private void openAddTeacherScreen(ActionEvent event) {
        loadScreen("/com/gradehub/addTeacher.fxml", event);
    }

    @FXML
    private void openRemoveTeacherScreen(ActionEvent event) {
        loadScreen("/com/gradehub/removeTeacher.fxml", event);
    }

    @FXML
    private void openAllocateCourseScreen(ActionEvent event) {
        loadScreen("/com/gradehub/allocateCourse.fxml", event);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        loadScreen("/com/gradehub/login.fxml", event);
    }

    public static void loadScreen(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(AdminDashboardController.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 800, 600);
            scene.getStylesheets().add(AdminDashboardController.class.getResource("/com/gradehub/css/styles.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
