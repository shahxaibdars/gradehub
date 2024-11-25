package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.users.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ViewAttendanceController {

    private String userId;
    Student s;

    public void setUserId(String userId) {
        this.userId = userId;
        s = new Student(userId);
        
        postInitialize();
    }

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private Label attendancePercentageLabel;

    @FXML
    private TextArea attendanceDetailsTextArea;

    @FXML
    public void initialize() {

        // Placeholder for attendance percentage
        attendancePercentageLabel.setText("Select a course to view attendance.");
    }

    public void postInitialize(){
        // Populate courses from the database
        ObservableList<String> courses = s.fetchCoursesFromDatabase();
        if (courses != null && !courses.isEmpty()) {
            courseChoiceBox.setItems(courses);
            courseChoiceBox.setValue("Select Course"); // Placeholder
        } else {
            courseChoiceBox.setItems(FXCollections.observableArrayList("No Courses Available"));
            courseChoiceBox.setValue("No Courses Available"); // Placeholder
        }
    }

    @FXML
    private void handleViewAttendance() {
        String selectedCourse = courseChoiceBox.getValue();
        if (selectedCourse == null) {
            attendancePercentageLabel.setText("Please select a course.");
            return;
        }

        // Simulate fetching attendance percentage and details
        String attendancePercentage = s.fetchAttendancePercentage(selectedCourse);
        String attendanceDetails = s.fetchAttendanceDetails(selectedCourse);
        
        // Update the UI
        if (attendancePercentage == null){
            attendancePercentage = "0";
        }
        attendancePercentageLabel.setText("Attendance: " + attendancePercentage + "%");
        attendanceDetailsTextArea.setText(attendanceDetails);
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
