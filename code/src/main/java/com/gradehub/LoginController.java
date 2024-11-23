package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

        try (Connection connection = DatabaseConnector.connect()) {
            if (connection == null) {
                errorLabel.setText("Failed to connect to the database.");
                return;
            }

            // Check credentials
            String userType = validateCredentials(connection, userId, password);

            if (userType == null) {
                errorLabel.setText("Invalid credentials. Please try again.");
            } else {
                switch (userType) {
                    case "Student":
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
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("An error occurred. Please try again.");
        }
    }

    private String validateCredentials(Connection connection, String userId, String password) {
        String userType = null;

        try {
            // Query for User
            String userQuery = "SELECT userType FROM User WHERE userID = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userType = resultSet.getString("userType");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userType;
    }

    private void loadDashboard(String fxmlPath, String userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());
    
            // Get the controller of the next screen
            Object controller = loader.getController();
    
            // Pass the userId to the appropriate dashboard controller
            if (controller instanceof StudentDashboardController) {
                ((StudentDashboardController) controller).setUserId(userId);
            } 
            // else if (controller instanceof FacultyDashboardController) {
            //     ((FacultyDashboardController) controller).setUserId(userId);
            // }
    
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
