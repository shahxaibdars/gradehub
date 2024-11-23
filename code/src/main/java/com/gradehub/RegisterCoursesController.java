package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.List;

public class RegisterCoursesController {

    @FXML
    private VBox coursesContainer;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        // Simulating available courses fetched from a database
        List<String> courses = List.of(
                "CS101 - Data Structures",
                "CS102 - Algorithms",
                "CS103 - Operating Systems",
                "CS104 - Database Systems"
        );

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
        boolean success = registerCourses(selectedCourses);
    
        if (success) {
            statusLabel.setText("Courses registered successfully!");
        } else {
            statusLabel.setText("Error: Unable to register for the selected courses.");
        }
    }
    

    private boolean registerCourses(List<String> selectedCourses) {
        // Simulated backend logic (replace with actual database operations)
        System.out.println("Registering courses: " + selectedCourses);
        return true; // Simulated success
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event);
    }
}
