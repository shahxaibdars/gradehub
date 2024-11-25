package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.admin.Admin;

import javafx.event.ActionEvent;

public class RemoveTeacherController {

    Admin admin;

    @FXML
    private TextField teacherIdField;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize(){
        admin = Admin.getInstance();
    }

    @FXML
    private void handleRemoveTeacher() {
        String teacherId = teacherIdField.getText();

        if (teacherId.isEmpty()) {
            statusLabel.setText("Teacher ID is required.");
            return;
        }

        String result = admin.removeTeacherFromDatabase(teacherId);
        if (result.equals("success")) {
            statusLabel.setText("Teacher removed successfully!");
            teacherIdField.setText("");
        } else {
            statusLabel.setText(result);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}