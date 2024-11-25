package com.gradehub;

import com.admin.Admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void handleLogin() {
        String userId = userIdField.getText();
        String password = passwordField.getText();

        if (userId.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter both User ID and Password.");
            return;
        }
            // Check credentials
            String userType = Admin.validateCredentials(userId, password);

            if (userType == null) {
                errorLabel.setText("Invalid credentials. Please try again.");
            } else {
                switch (userType) {
                    case "Student":
                        //stage.setTitle("Grade Hub - Login");
                        loadDashboard("/com/gradehub/studentDashboard.fxml", userId);
                        break;
                    case "Faculty":
                        loadDashboard("/com/gradehub/facultyDashboard.fxml", userId);
                        break;
                    case "Admin":
                        loadDashboard("/com/gradehub/adminDashboard.fxml", userId);
                        break;
                    default:
                        errorLabel.setText("Unknown user type.");
                }
            }
    }

    private void loadDashboard(String fxmlPath, String userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), 800, 600);
    
            // Get the controller of the next screen
            Object controller = loader.getController();
    
            // Pass the userId to the appropriate dashboard controller
            if (controller instanceof StudentDashboardController) {
                ((StudentDashboardController) controller).setUserId(userId);
            } 
            else if (controller instanceof FacultyDashboardController) {
                ((FacultyDashboardController) controller).setUserId(userId);
            }
    
            // Apply stylesheet programmatically
            scene.getStylesheets().add(getClass().getResource("/com/gradehub/css/styles.css").toExternalForm());
    
            Stage stage = (Stage) userIdField.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Failed to load the dashboard.");
        }
    }
}
