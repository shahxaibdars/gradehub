package com.gradehub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadAttendanceController {

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
        // Populate courses (in a real application, fetch from the database)
        ObservableList<String> courses = FXCollections.observableArrayList("CS101", "CS102", "CS103");
        courseChoiceBox.setItems(courses);

        // Populate attendance options
        ObservableList<String> attendanceOptions = FXCollections.observableArrayList("Present", "Absent");
        attendanceChoiceBox.setItems(attendanceOptions);
        attendanceChoiceBox.setValue("Present");
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
        if (!isValidDate(date)) {
            statusLabel.setText("Invalid date format. Use dd/MM/yyyy.");
            return;
        }

        // Simulate saving attendance to the database
        if (saveAttendanceToDatabase(course, date, studentId, attendanceStatus)) {
            statusLabel.setText("Attendance uploaded successfully!");
        } else {
            statusLabel.setText("Error uploading attendance.");
        }
    }

    private boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            Date parsedDate = dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean saveAttendanceToDatabase(String course, String date, String studentId, String attendanceStatus) {
        // Simulate saving attendance to the database
        System.out.println("Saving attendance for course: " + course + ", date: " + date +
                ", student: " + studentId + ", status: " + attendanceStatus);
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
