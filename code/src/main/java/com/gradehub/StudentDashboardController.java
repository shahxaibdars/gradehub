package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class StudentDashboardController {

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @FXML
    private void openViewMarks(ActionEvent event) {
        loadScreen("/com/gradehub/viewMarks.fxml", event, userId);
    }

    @FXML
    private void openViewAttendance(ActionEvent event) {
        loadScreen("/com/gradehub/viewAttendance.fxml", event, userId);
    }

    @FXML
    private void openRegisterCourses(ActionEvent event) {
        loadScreen("/com/gradehub/registerCourses.fxml", event, userId);
    }

    @FXML
    private void openWithdrawCourse(ActionEvent event) {
        loadScreen("/com/gradehub/withdrawCourses.fxml", event, userId);
    }

    @FXML
    private void openPrintFeeChallan(ActionEvent event) {
        loadScreen("/com/gradehub/printFeeChallan.fxml", event, userId);
    }

    @FXML
    private void openPrintAdmitCard(ActionEvent event) {
        loadScreen("/com/gradehub/printAdmitCard.fxml", event, userId);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        loadScreen("/com/gradehub/login.fxml", event, userId);
    }

    // Static method to load screens
    public static void loadScreen(String fxmlPath, ActionEvent event, String userId) {
        try {
            FXMLLoader loader = new FXMLLoader(StudentDashboardController.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 800, 600);

            // Get the controller of the next screen
            Object controller = loader.getController();
    
            // Pass the userId to the appropriate dashboard controller
            if (controller instanceof ViewMarksController) {
                ((ViewMarksController) controller).setUserId(userId);
            } else if (controller instanceof ViewAttendanceController) {
                ((ViewAttendanceController) controller).setUserId(userId);
            } else if (controller instanceof StudentDashboardController) {
                ((StudentDashboardController) controller).setUserId(userId);
            } else if (controller instanceof PrintFeeChallanController) {
                ((PrintFeeChallanController) controller).setUserId(userId);
            } else if (controller instanceof PrintAdmitCardController) {
                ((PrintAdmitCardController) controller).setUserId(userId);
            } else if (controller instanceof RegisterCoursesController) {
                ((RegisterCoursesController) controller).setUserId(userId);
            } else if (controller instanceof WithdrawCoursesController) {
                ((WithdrawCoursesController) controller).setUserId(userId);
            }
            
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
