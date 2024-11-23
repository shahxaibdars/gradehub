package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class AddStudentController {

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField contactDetailsField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleAddStudent() {
        String studentId = studentIdField.getText();
        String studentName = studentNameField.getText();
        String contactDetails = contactDetailsField.getText();

        if (studentId.isEmpty() || studentName.isEmpty() || contactDetails.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        if (saveStudentToDatabase(studentId, studentName, contactDetails)) {
            statusLabel.setText("Student added successfully!");
        } else {
            statusLabel.setText("Error adding student.");
        }
    }

    private boolean saveStudentToDatabase(String studentId, String studentName, String contactDetails) {
        System.out.println("Saving student: " + studentId + ", " + studentName + ", " + contactDetails);
        return true;
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the updated loadScreen method
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}
