package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class RemoveStudentController {

    @FXML
    private TextField studentIdField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleRemoveStudent() {
        String studentId = studentIdField.getText();

        if (studentId.isEmpty()) {
            statusLabel.setText("Student ID is required.");
            return;
        }

        // Simulate removing the student from the database
        if (removeStudentFromDatabase(studentId)) {
            statusLabel.setText("Student removed successfully!");
        } else {
            statusLabel.setText("Error removing student. ID not found.");
        }
    }

    private boolean removeStudentFromDatabase(String studentId) {
        // Simulated database removal logic
        System.out.println("Removing student with ID: " + studentId);
        return true; // Return true if removal is successful
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the updated loadScreen method from AdminDashboardController
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}

