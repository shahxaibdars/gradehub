package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class ViewMarksController {

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
        postInitialize();
    }

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private ChoiceBox<String> testTypeChoiceBox;

    @FXML
    private TextArea marksTextArea;

    @FXML
    public void initialize() {
        // Populate test types
        ArrayList<String> testTypesList = new ArrayList<>(Arrays.asList("QUIZ", "ASSIGNMENT", "MIDTERM", "FINAL"));
        ObservableList<String> testTypes = FXCollections.observableArrayList(testTypesList);
        testTypeChoiceBox.setItems(testTypes);
        testTypeChoiceBox.setValue("Select Test Type"); // Placeholder
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

    @FXML
    private void handleViewMarks() {
        String selectedCourse = courseChoiceBox.getValue();
        String selectedTestType = testTypeChoiceBox.getValue();

        if (selectedCourse == null || selectedTestType == null || 
            selectedCourse.equals("Select Course") || selectedTestType.equals("Select Test Type")) {
            marksTextArea.setText("Please select both a course and test type.");
            return;
        }

        String marks = fetchMarks(selectedCourse, selectedTestType);
        if (marks == null) {
            marksTextArea.setText("No marks found for " + selectedTestType + " in " + selectedCourse + ".");
        } else {
            marksTextArea.setText("Marks for " + selectedTestType + " in " + selectedCourse + ": " + marks);
        }
    }

    private String fetchMarks(String course, String testType) {
        String query = "SELECT am.obtainedMarks, a.totalMarks " +
                       "FROM Assessment a " +
                       "JOIN Course c ON a.courseID = c.courseID " +
                       "JOIN StudentCourse sc ON c.courseID = sc.courseID " +
                       "JOIN AssessmentMarks am ON a.assessmentID = am.assessmentID " +
                       "WHERE sc.userID = ? AND c.courseName = ? AND a.type = ?";

        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, course);
            preparedStatement.setString(3, testType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String courseList = "";
                if (resultSet.next()) {
                    courseList = resultSet.getString("obtainedMarks");
                    courseList = courseList + "/" + resultSet.getString("totalMarks");
                }
                while (resultSet.next()) {
                    courseList = courseList + ", " + (resultSet.getString("obtainedMarks"));
                    courseList = courseList + "/" + resultSet.getString("totalMarks");
                }
                return courseList;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
