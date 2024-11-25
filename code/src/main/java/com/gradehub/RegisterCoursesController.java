package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

import com.users.Student;

public class RegisterCoursesController {

    private String userId;
    Student student;

    public void setUserId(String userId) {
        this.userId = userId;
        student = new Student(userId);
        postInitialize();
    }

    @FXML
    private VBox coursesContainer;

    @FXML
    private Label statusLabel;

    private void postInitialize() {
        // Simulating available courses fetched from a database

        ArrayList<String> courses = student.availableForRegistration(false);
        coursesContainer.getChildren().clear();
        // Create CheckBox elements for each course
        for (String course : courses) {
            CheckBox checkBox = new CheckBox(course);
            checkBox.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;"); // Optional styling
            coursesContainer.getChildren().add(checkBox);
        }
    }

    @FXML
    private void handleRegisterCourses() {
        List<String> selectedCourses = new ArrayList<>();
    
        // Iterate over nodes and manually check for CheckBox instances
        for (var node : coursesContainer.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node; // Explicit cast
                if (checkBox.isSelected()) {
                    selectedCourses.add(checkBox.getText());
                }
            }
        }
    
        if (selectedCourses.isEmpty()) {
            statusLabel.setText("Please select at least one course to register.");
            return;
        }
    
        // Simulate backend validation and registration
        boolean success = student.registerSelectedCourses(selectedCourses);
    
        if (success) {
            statusLabel.setText("Courses registered successfully!");
            postInitialize();
        } else {
            statusLabel.setText("Error: Unable to register for the selected courses.");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
