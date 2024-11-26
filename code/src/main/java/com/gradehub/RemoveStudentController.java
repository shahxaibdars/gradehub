package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.users.Admin;

import javafx.event.ActionEvent;

public class RemoveStudentController {

    Admin admin;

    @FXML
    private TextField studentIdField;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        admin = Admin.getInstance();
    }

    @FXML
    private void handleRemoveStudent() {
        String studentId = studentIdField.getText();

        if (studentId.isEmpty()) {
            statusLabel.setText("Student ID is required.");
            return;
        }

        String result = admin.removeStudentFromDatabase(studentId);
        if (result.equals("success")) {
            statusLabel.setText("Student removed successfully!");
            studentIdField.setText("");
        } else {
            statusLabel.setText(result);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the updated loadScreen method from AdminDashboardController
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}

