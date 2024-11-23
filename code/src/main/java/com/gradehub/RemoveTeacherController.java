package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class RemoveTeacherController {

    @FXML
    private TextField teacherIdField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleRemoveTeacher() {
        String teacherId = teacherIdField.getText();

        if (teacherId.isEmpty()) {
            statusLabel.setText("Teacher ID is required.");
            return;
        }

        if (removeTeacherFromDatabase(teacherId)) {
            statusLabel.setText("Teacher removed successfully!");
        } else {
            statusLabel.setText("Error removing teacher. ID not found.");
        }
    }

    private boolean removeTeacherFromDatabase(String teacherId) {
        System.out.println("Removing teacher with ID: " + teacherId);
        return true;
    }

    @FXML
    private void goBack(ActionEvent event) {
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}

