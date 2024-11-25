package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;

import com.users.Teacher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class UploadMarksController {

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
    private ChoiceBox<String> assessmentTypeChoiceBox;

    @FXML
    private TextField studentIdField;

    @FXML
    private TextField marksField;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        // Populate test types
        ArrayList<String> testTypesList = new ArrayList<>(Arrays.asList("QUIZ", "ASSIGNMENT", "MIDTERM", "FINAL"));
        ObservableList<String> testTypes = FXCollections.observableArrayList(testTypesList);
        assessmentTypeChoiceBox.setItems(testTypes);
        assessmentTypeChoiceBox.setValue("Select Test Type"); // Placeholder
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
    private void handleUploadMarks() {
        String course = courseChoiceBox.getValue();
        String assessmentType = assessmentTypeChoiceBox.getValue();
        String studentId = studentIdField.getText();
        String marks = marksField.getText();

        if (course == null || assessmentType == null || studentId.isEmpty() || marks.isEmpty()) {
            statusLabel.setText("Please fill all fields.");
            return;
        }

        String result = teacher.saveMarksToDatabase(course, assessmentType, studentId, marks);
        if (result.equals("success")) {
            studentIdField.setText("");
            marksField.setText("");
            statusLabel.setText("Marks uploaded successfully!");
        } else {
            statusLabel.setText(result);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        FacultyDashboardController.loadScreen("/com/gradehub/facultyDashboard.fxml", event, userId);
    }
}
