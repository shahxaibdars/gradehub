package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class AddTeacherController {

    @FXML
    private TextField teacherIdField;

    @FXML
    private TextField teacherNameField;

    @FXML
    private TextField contactDetailsField;

    @FXML
    private TextField courseField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleAddTeacher() {
        String teacherId = teacherIdField.getText();
        String teacherName = teacherNameField.getText();
        String contactDetails = contactDetailsField.getText();
        String course = courseField.getText();

        if (teacherId.isEmpty() || teacherName.isEmpty() || contactDetails.isEmpty() || course.isEmpty()) {
            statusLabel.setText("All fields are required.");
            return;
        }

        if (saveTeacherToDatabase(teacherId, teacherName, contactDetails, course)) {
            statusLabel.setText("Teacher added successfully!");
        } else {
            statusLabel.setText("Error adding teacher.");
        }
    }

    private boolean saveTeacherToDatabase(String teacherId, String teacherName, String contactDetails, String course) {
        System.out.println("Saving teacher: " + teacherId + ", " + teacherName + ", " + contactDetails + ", " + course);
        return true;
    }

    @FXML
    private void goBack(ActionEvent event) {
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
    }
}
