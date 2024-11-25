package com.gradehub;
import java.util.ArrayList;
import java.util.Arrays;

import com.admin.Admin;
import com.utils.ValidationUtils;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class AddTeacherController {

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
    private ChoiceBox<String> deptField;

    @FXML
    private ChoiceBox<String> desgField;

    @FXML
    private TextField joinField;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        admin = Admin.getInstance();
        // Populate test types
        ArrayList<String> programList = new ArrayList<>(Arrays.asList("CS", "SE", "AI"));
        ObservableList<String> programTypes = FXCollections.observableArrayList(programList);
        deptField.setItems(programTypes);
        deptField.setValue("CS");

        ArrayList<String> professionList = new ArrayList<>(Arrays.asList("Lecturer", "Assistant Professor", "Associate Professor", "Professor"));
        ObservableList<String> professsionTypes = FXCollections.observableArrayList(professionList);
        desgField.setItems(professsionTypes);
        desgField.setValue("Lecturer");
    }

    @FXML
    private void handleAddTeacher() {
        String id = idField.getText();
        String name = nameField.getText();
        String contact = contactField.getText();
        String pass = passwordField.getText();
        String dept = deptField.getValue();
        String desg = desgField.getValue();
        String join = joinField.getText();

        if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || pass.isEmpty() || dept.isEmpty() || desg.isEmpty() || join.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        if (!ValidationUtils.isValidDate(join)) {
            statusLabel.setText("Invalid date format. Use YYYY-MM-DD");
            return;
        }

        String result = admin.saveTeacherToDatabase(id, pass, name, contact, dept, desg, join);
        if (result.equals("success")) {
            idField.setText("");
            nameField.setText("");
            contactField.setText("");
            passwordField.setText("");
            joinField.setText("");
            deptField.setValue("CS");
            desgField.setValue("Lecturer");
            statusLabel.setText("Teacher added successfully!");
        } else {
            statusLabel.setText(result);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}