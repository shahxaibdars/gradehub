package com.gradehub;
import java.util.ArrayList;
import java.util.List;

import com.users.Student;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

public class WithdrawCoursesController {

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

        ArrayList<String> courses = student.availableForRegistration(true);
        coursesContainer.getChildren().clear();
        // Create CheckBox elements for each course
        for (String course : courses) {
            CheckBox checkBox = new CheckBox(course);
            checkBox.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;"); // Optional styling
            coursesContainer.getChildren().add(checkBox);
        }
    }

    @FXML
    private void handleWithdrawCourse() {
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
            statusLabel.setText("Please select at least one course to withdraw.");
            return;
        }
    
        // Simulate backend validation and registration
        boolean success = student.withdrawSelectedCourses(selectedCourses);

        if (success) {
            statusLabel.setText("Course withdrawal successful!");
            postInitialize();
        } else {
            statusLabel.setText("Error: Unable to process withdrawal request.");
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
