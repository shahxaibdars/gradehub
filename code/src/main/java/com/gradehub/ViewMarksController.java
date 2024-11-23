package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ViewMarksController {

    @FXML
    private ChoiceBox<String> courseChoiceBox;

    @FXML
    private ChoiceBox<String> testTypeChoiceBox;

    @FXML
    private Label marksLabel;

    @FXML
    public void initialize() {
        // Populate courses (simulating fetching from a database)
        ObservableList<String> courses = FXCollections.observableArrayList("CS101", "CS102", "CS103");
        courseChoiceBox.setItems(courses);
        courseChoiceBox.setValue("Select Course"); // Placeholder
    
        // Populate test types
        ObservableList<String> testTypes = FXCollections.observableArrayList("Quiz", "Assignment", "Exam");
        testTypeChoiceBox.setItems(testTypes);
        testTypeChoiceBox.setValue("Select Test Type"); // Placeholder
    }
    

    @FXML
    private void handleViewMarks() {
        String selectedCourse = courseChoiceBox.getValue();
        String selectedTestType = testTypeChoiceBox.getValue();

        if (selectedCourse == null || selectedTestType == null) {
            marksLabel.setText("Please select both a course and test type.");
            return;
        }

        // Simulate fetching marks for the selected course and test type
        String marks = fetchMarks(selectedCourse, selectedTestType);
        marksLabel.setText("Marks for " + selectedTestType + " in " + selectedCourse + ": " + marks);
    }

    private String fetchMarks(String course, String testType) {
        // Simulated data fetching (replace with actual database logic)
        System.out.println("Fetching marks for course: " + course + ", test type: " + testType);
        return "85"; // Simulated marks
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event);
    }
}



