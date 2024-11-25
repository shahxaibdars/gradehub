package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.users.Student;

import javafx.event.ActionEvent;

public class PrintAdmitCardController {

    private String userId;
    Student student;

    public void setUserId(String userId) {
        this.userId = userId;
        student = new Student(userId);
        postInitialize();
    }

    @FXML
    private TextArea eligibleCoursesTextArea;

    private void postInitialize(){
        String eligibleCourses = student.fetchEligibleCourses();
        if (eligibleCourses == null || eligibleCourses.isEmpty()){
            eligibleCoursesTextArea.setText("Your attendence percentage is below 70 in all courses");
        } else{
            StringBuilder preview = new StringBuilder();
            preview.append("Admit Card - Final Exams\n");
            preview.append("------------------------\n");
            preview.append("Eligible Courses:\n");
            preview.append(eligibleCourses);
            preview.append("\nPlease bring this admit card to your exams.");
            eligibleCoursesTextArea.setText(preview.toString());
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}
