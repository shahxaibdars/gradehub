package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ViewAttendanceController {

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
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

    public void postInitialize(){
        // Populate courses from the database
        ObservableList<String> courses = fetchCoursesFromDatabase();
        if (courses != null && !courses.isEmpty()) {
            courseChoiceBox.setItems(courses);
            courseChoiceBox.setValue("Select Course"); // Placeholder
        } else {
            courseChoiceBox.setItems(FXCollections.observableArrayList("No Courses Available"));
            courseChoiceBox.setValue("No Courses Available"); // Placeholder
        }
    }

    private ObservableList<String> fetchCoursesFromDatabase() {
        ObservableList<String> courseList = FXCollections.observableArrayList();
        String query = "SELECT c.courseName FROM Course c " +
                       "JOIN StudentCourse sc ON c.courseID = sc.courseID " +
                       "WHERE sc.userID = ?";

        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    courseList.add(resultSet.getString("courseName"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseList;
    }

    private String fetchAttendancePercentage(String course) {
        // Simulated data fetching (replace with actual database logic)
        String attendencePercentage = "";
        System.out.println("Fetching attendance percentage for course: " + course);
        String query = "SELECT ((SUM(status = 'LATE') + SUM(status = 'PRESENT')) * 100.0 / COUNT(*)) AS result" +
                       " FROM AttendanceRecord" + 
                       " WHERE courseId = (SELECT CourseId from Course where courseName = ? ) AND userId = ? ;";

        try (Connection connection = DatabaseConnector.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, course);
            preparedStatement.setString(2, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    attendencePercentage =  resultSet.getString("result");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendencePercentage;
    }

    private String fetchAttendanceDetails(String course) {
        // Simulated data fetching (replace with actual database logic)
        System.out.println("Fetching attendance details for course: " + course);
        //return "Date: 2024-01-01 - Present\nDate: 2024-01-02 - Absent\nDate: 2024-01-03 - Present"; // Simulated details
        String attendenceDetails = "";
        System.out.println("Fetching attendance percentage for course: " + course);
        String query = "SELECT date, status " +
                       " FROM AttendanceRecord" + 
                       " WHERE courseId = (SELECT CourseId from Course where courseName = ? ) AND userId = ? ;";

        try (Connection connection = DatabaseConnector.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, course);
            preparedStatement.setString(2, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    attendenceDetails = "Date: " + resultSet.getString("date");
                    attendenceDetails = attendenceDetails + " - " + resultSet.getString("status") + "\n";
                }

                while (resultSet.next()) {
                    attendenceDetails = attendenceDetails + "Date: " + resultSet.getString("date");
                    attendenceDetails = attendenceDetails + " - " + resultSet.getString("status") + "\n";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return attendenceDetails;
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
