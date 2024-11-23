package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ViewAttendanceController {

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private Label attendancePercentageLabel;

    @FXML
    private TextArea attendanceDetailsTextArea;

    @FXML
    public void initialize() {
        // Populate courses (simulating fetching from a database)
        ObservableList<String> courses = FXCollections.observableArrayList("CS101", "CS102", "CS103");
        courseChoiceBox.setItems(courses);

        // Placeholder for attendance percentage
        attendancePercentageLabel.setText("Select a course to view attendance.");
    }

    @FXML
    private void handleViewAttendance() {
        String selectedCourse = courseChoiceBox.getValue();

        if (selectedCourse == null) {
            attendancePercentageLabel.setText("Please select a course.");
            return;
        }

        // Simulate fetching attendance percentage and details
        String attendancePercentage = fetchAttendancePercentage(selectedCourse);
        String attendanceDetails = fetchAttendanceDetails(selectedCourse);

        // Update the UI
        attendancePercentageLabel.setText("Attendance: " + attendancePercentage + "%");
        attendanceDetailsTextArea.setText(attendanceDetails);
    }

    private String fetchAttendancePercentage(String course) {
        // Simulated data fetching (replace with actual database logic)
        System.out.println("Fetching attendance percentage for course: " + course);
        return "85"; // Simulated percentage
    }

    private String fetchAttendanceDetails(String course) {
        // Simulated data fetching (replace with actual database logic)
        System.out.println("Fetching attendance details for course: " + course);
        return "Date: 2024-01-01 - Present\nDate: 2024-01-02 - Absent\nDate: 2024-01-03 - Present"; // Simulated details
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event);
    }
}
