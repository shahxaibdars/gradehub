package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class WithdrawCoursesController {

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        // Simulating available courses fetched from a database
        ObservableList<String> courses = FXCollections.observableArrayList(
                "CS101 - Data Structures",
                "CS102 - Algorithms",
                "CS103 - Operating Systems"
        );
        courseChoiceBox.setItems(courses);
    }

    @FXML
    private void handleWithdrawCourse() {
        String selectedCourse = courseChoiceBox.getValue();

        if (selectedCourse == null) {
            statusLabel.setText("Please select a course to withdraw.");
            return;
        }

        // Simulate backend validation and withdrawal process
        boolean success = withdrawCourse(selectedCourse);

        if (success) {
            statusLabel.setText("Course withdrawal successful!");
        } else {
            statusLabel.setText("Error: Unable to process withdrawal request.");
        }
    }

    private boolean withdrawCourse(String course) {
        // Simulated backend logic (replace with actual database operations)
        System.out.println("Withdrawing course: " + course);
        return true; // Simulated success
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event);
    }
}
