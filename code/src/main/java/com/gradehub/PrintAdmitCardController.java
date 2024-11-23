package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class PrintAdmitCardController {

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @FXML
    private TextArea eligibleCoursesTextArea;

    @FXML
    private TextArea admitCardPreviewTextArea;

    @FXML
    public void initialize() {
        // Simulate fetching eligible courses (replace with actual logic later)
        String eligibleCourses = fetchEligibleCourses();
        eligibleCoursesTextArea.setText(eligibleCourses);
    }

    private String fetchEligibleCourses() {
        // Simulated logic (replace with database fetching logic)
        StringBuilder courses = new StringBuilder();
        courses.append("CS101 - Data Structures\n");
        courses.append("CS102 - Operating Systems\n");
        courses.append("CS103 - Database Management Systems");
        return courses.toString();
    }

    @FXML
    private void generateAdmitCard() {
        String eligibleCourses = eligibleCoursesTextArea.getText();
        if (eligibleCourses.isBlank()) {
            admitCardPreviewTextArea.setText("No eligible courses found for the admit card.");
        } else {
            // Simulate admit card generation
            String admitCardPreview = generateAdmitCardPreview(eligibleCourses);
            admitCardPreviewTextArea.setText(admitCardPreview);
        }
    }

    private String generateAdmitCardPreview(String eligibleCourses) {
        StringBuilder preview = new StringBuilder();
        preview.append("Admit Card - Final Exams\n");
        preview.append("------------------------\n");
        preview.append("Eligible Courses:\n");
        preview.append(eligibleCourses);
        preview.append("\n\nPlease bring this admit card to your exams.");
        return preview.toString();
    }
    
    @FXML
    private void handlePrint() {
        // Simulated print functionality
        System.out.println("Printing admit card...");
    }

    @FXML
    private void handleDownload() {
        // Simulated download functionality
        System.out.println("Downloading admit card as PDF...");
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
