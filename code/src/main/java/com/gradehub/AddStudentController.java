package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddStudentController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField contactField;
    
    @FXML
    private TextField passwordField;


    @FXML
    private TextField programField;

    
    @FXML
    private TextField enrolYearField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleAddStudent() {
        String id = idField.getText();
        String name = nameField.getText();
        String contact = contactField.getText();
        String password = passwordField.getText();
        String enrolYear = enrolYearField.getText();
        String program = programField.getText();


        if (id.isEmpty()
            || name.isEmpty()
            || contact.isEmpty()
            || program.isEmpty()
            || enrolYear.isEmpty()
            || password.isEmpty())
            {
            statusLabel.setText("All fields are required.");
            return;
        }

        if (saveStudentToDatabase(id, name, contact, program, enrolYear, password)) {
            statusLabel.setText("Student added successfully!");
        } else {
            statusLabel.setText("Error adding student.");
        }
    }

    private boolean saveStudentToDatabase(String id, String name, String contact, String program, String enrolYear, String password) {
        //System.out.println("Saving student: " + studentId + ", " + studentName + ", " + contact);
        String email = id + "@example.com";
        try{
            String insertSQL = "INSERT INTO User (userID, password, name, email, phoneNumber, userType) VALUES (?, ?, ?, ?, ?, ?)";
            try(Connection conn = DatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                pstmt.setString(1, id);
                pstmt.setString(2, password);
                pstmt.setString(3, name);
                pstmt.setString(4, email);
                pstmt.setString(5, contact);
                pstmt.setString(6, "Student");

                int rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully in user table!");
                }
            }

            String insertSQL2 = "INSERT INTO Student (userID, enrollmentYear, program, semester) VALUES (?, ?, ?, ?)";
            try(Connection conn = DatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL2)) {

                pstmt.setString(1, id);
                pstmt.setString(2, enrolYear);
                pstmt.setString(3, program);
                pstmt.setString(4, "1");

                int rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully in student table!");
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
        // Use the updated loadScreen method
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}