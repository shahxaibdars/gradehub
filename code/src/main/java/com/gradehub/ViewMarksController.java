package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;

import com.users.Student;

public class ViewMarksController {

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
    private void handleViewMarks() {
        String selectedCourse = courseChoiceBox.getValue();
        String selectedTestType = testTypeChoiceBox.getValue();

        if (selectedCourse == null || selectedTestType == null || 
            selectedCourse.equals("Select Course") || selectedTestType.equals("Select Test Type")) {
            marksTextArea.setText("Please select both a course and test type.");
            return;
        }

        String marks = s.fetchMarks(selectedCourse, selectedTestType);
        if (marks == null) {
            marksTextArea.setText("No marks found for " + selectedTestType + " in " + selectedCourse + ".");
        } else {
            marksTextArea.setText("Marks for " + selectedTestType + " in " + selectedCourse + ": " + marks);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
