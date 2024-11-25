package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;

import com.admin.Admin;
import com.utils.ValidationUtils;

public class AddStudentController {

    Admin admin;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField contactField;
    
    @FXML
    private TextField passwordField;

    @FXML
    private ChoiceBox<String> programField;
    
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
        String program = programField.getValue();

        if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || program.isEmpty() || enrolYear.isEmpty() || password.isEmpty()){
            statusLabel.setText("All fields are required.");
            return;
        } else if (!(ValidationUtils.isInteger(enrolYear))){
            statusLabel.setText("Batch must be integer Value");
            return;
        }

        String result = admin.saveStudentToDatabase(id, password, name, contact, program, enrolYear);

        if (result.equals("success")) {
            statusLabel.setText("Student added successfully!");
            idField.setText("");
            nameField.setText("");
            contactField.setText("");
            passwordField.setText("");
            enrolYearField.setText("");
            programField.setValue("CS");
        } else {
            statusLabel.setText(result);
        }
    }

    @FXML
    public void initialize() {
        admin = Admin.getInstance();
        // Populate test types
        ArrayList<String> programList = new ArrayList<>(Arrays.asList("CS", "SE", "AI"));
        ObservableList<String> programTypes = FXCollections.observableArrayList(programList);
        programField.setItems(programTypes);
        programField.setValue("CS"); // Placeholder
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the updated loadScreen method
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}