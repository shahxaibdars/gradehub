package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private static final String STUDENT_USER = "student";
    private static final String FACULTY_USER = "faculty";
    private static final String ADMIN_USER = "admin";
    private static final String PASSWORD = "password";

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter both username and password.");
            return;
        }

        try {
            if (username.equals(STUDENT_USER) && password.equals(PASSWORD)) {
                loadDashboard("/com/gradehub/studentDashboard.fxml");
            } else if (username.equals(FACULTY_USER) && password.equals(PASSWORD)) {
                loadDashboard("/com/gradehub/facultyDashboard.fxml");
            } else if (username.equals(ADMIN_USER) && password.equals(PASSWORD)) {
                loadDashboard("/com/gradehub/adminDashboard.fxml");
            } else {
                errorLabel.setText("Invalid credentials. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDashboard(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            // Apply stylesheet programmatically
            scene.getStylesheets().add(getClass().getResource("/com/gradehub/css/styles.css").toExternalForm());

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
