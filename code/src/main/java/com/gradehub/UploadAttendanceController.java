package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import com.utils.ValidationUtils;

import com.users.Teacher;

public class UploadAttendanceController {

    String userId;
    Teacher teacher;

    public void setUserId(String userId) {
        this.userId = userId;
        teacher = new Teacher(userId);
        postInitialize();
    }
    
    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private TextField dateField;

    @FXML
    private TextField studentIdField;

    @FXML
    private ChoiceBox<String> attendanceChoiceBox;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        // Populate attendance options
        ObservableList<String> attendanceOptions = FXCollections.observableArrayList("PRESENT", "ABSENT", "LATE", "LEAVE");
        attendanceChoiceBox.setItems(attendanceOptions);
        attendanceChoiceBox.setValue("PRESENT");
    }

    public void postInitialize(){
        // Populate courses from the database
        ObservableList<String> courses = teacher.fetchCoursesFromDatabase();
        if (courses != null && !courses.isEmpty()) {
            courseChoiceBox.setItems(courses);
            courseChoiceBox.setValue("Select Course"); // Placeholder
        } else {
            courseChoiceBox.setItems(FXCollections.observableArrayList("No Courses Available"));
            courseChoiceBox.setValue("No Courses Available"); // Placeholder
        }
    }

    @FXML
    private void handleUploadAttendance() {
        String course = courseChoiceBox.getValue();
        String date = dateField.getText();
        String studentId = studentIdField.getText();
        String attendanceStatus = attendanceChoiceBox.getValue();

        // Validate input fields
        if (course == null || date.isEmpty() || studentId.isEmpty() || attendanceStatus == null) {
            statusLabel.setText("Please fill all fields.");
            return;
        }

        // Validate date format
        if (!ValidationUtils.isValidDate(date)) {
            statusLabel.setText("Invalid date format. Use YYYY-MM-DD");
            return;
        }

        // Simulate saving attendance to the database
        String result = teacher.saveAttendanceToDatabase(course, date, studentId, attendanceStatus);
        if (result.equals("success")) {
            statusLabel.setText("Attendance uploaded successfully!");
        } else {
            statusLabel.setText(result);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        FacultyDashboardController.loadScreen("/com/gradehub/facultyDashboard.fxml", event, userId);
    }
}
