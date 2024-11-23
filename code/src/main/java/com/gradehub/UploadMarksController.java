package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class UploadMarksController {

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
        ObservableList<String> courses = FXCollections.observableArrayList("CS101", "CS102", "CS103");
        courseChoiceBox.setItems(courses);

        ObservableList<String> assessmentTypes = FXCollections.observableArrayList("Quiz", "Assignment", "Exam");
        assessmentTypeChoiceBox.setItems(assessmentTypes);
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

        if (saveMarksToDatabase(course, assessmentType, studentId, marks)) {
            statusLabel.setText("Marks uploaded successfully!");
        } else {
            statusLabel.setText("Error uploading marks.");
        }
    }

    private boolean saveMarksToDatabase(String course, String assessmentType, String studentId, String marks) {
        System.out.println("Course: " + course + ", Assessment: " + assessmentType + ", Student: " + studentId + " - Marks: " + marks);
        return true;
    }

    @FXML
    private void goBack(ActionEvent event) {
        loadScreen("/com/gradehub/facultyDashboard.fxml", event);
    }

    private void loadScreen(String fxmlPath, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene scene = new Scene(loader.load());

            // Apply stylesheet programmatically
            scene.getStylesheets().add(getClass().getResource("/com/gradehub/css/styles.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
