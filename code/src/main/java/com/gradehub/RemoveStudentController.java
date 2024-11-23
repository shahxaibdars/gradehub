package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
        //System.out.println("Removing student with ID: " + studentId);
        try{
            String deleteQuery = "DELETE FROM Student WHERE userID = ?";
            try(Connection conn = DatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

                pstmt.setString(1, studentId);

                int rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data deleted successfully from student table!");
                }
            }

            String deleteQuery2 = "DELETE FROM User WHERE userID = ?";
            try(Connection conn = DatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery2)) {

                pstmt.setString(1, studentId);

                int rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data deleted successfully from user table!");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the updated loadScreen method from AdminDashboardController
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}

