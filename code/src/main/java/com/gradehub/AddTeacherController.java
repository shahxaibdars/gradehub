package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.event.ActionEvent;

public class AddTeacherController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField deptField;

    @FXML
    private TextField desgField;

    @FXML
    private TextField joinField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleAddTeacher() {
        String id = idField.getText();
        String name = nameField.getText();
        String contact = contactField.getText();
        String pass = passwordField.getText();
        String dept = deptField.getText();
        String desg = desgField.getText();
        String join = joinField.getText();

        if (id.isEmpty() 
            || name.isEmpty() 
            || contact.isEmpty()
            || pass.isEmpty()
            || dept.isEmpty()
            || desg.isEmpty()
            || join.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        if (saveTeacherToDatabase(id, name, contact, pass, dept, desg, join)) {
            statusLabel.setText("Teacher added successfully!");
        } else {
            statusLabel.setText("Error adding teacher.");
        }
    }

    private boolean saveTeacherToDatabase(String id, String name, String contact, String password, String dept, String desg, String join) {
        //System.out.println("Saving teacher: " + id + ", " + name + ", " + contact);
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
                pstmt.setString(6, "Faculty");

                int rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully in user table!");
                }
            }

            String insertSQL2 = "INSERT INTO Faculty (userID, department, designation, joinDate) VALUES (?, ?, ?, ?)";
            try(Connection conn = DatabaseConnector.connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL2)) {

                pstmt.setString(1, id);
                pstmt.setString(2, dept);
                pstmt.setString(3, desg);
                pstmt.setString(4, join);

                int rowsAffected = pstmt.executeUpdate();
    
                if (rowsAffected > 0) {
                    System.out.println("Data inserted successfully in faculty table!");
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
