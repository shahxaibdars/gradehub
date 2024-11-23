package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
        try{
            String deleteQuery = "DELETE FROM Faculty WHERE userID = ?";
            try(Connection conn = DatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

                pstmt.setString(1, teacherId);

                int rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data deleted successfully from faculty table!");
                }
            }

            String deleteQuery2 = "DELETE FROM User WHERE userID = ?";
            try(Connection conn = DatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery2)) {

                pstmt.setString(1, teacherId);

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
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}

