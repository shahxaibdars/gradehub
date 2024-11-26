package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.users.Admin;

import javafx.event.ActionEvent;

public class AllocateCourseController {

     Admin admin;

     @FXML
     private TextField teacherIdField;

     @FXML
     private TextField courseIdField;

     @FXML
     private Label statusLabel;

     @FXML
     public void initialize() {
          admin = Admin.getInstance();
     }

     @FXML
     private void handleAllocateCourse() {
          String teacherId = teacherIdField.getText();
          String courseId = courseIdField.getText();

          if (teacherId.isEmpty() || courseId.isEmpty()) {
               statusLabel.setText("All fields are required.");
               return;
          }

          String result = admin.allocateCourse(courseId, teacherId);
          if (result.equals("success")) {
               courseIdField.setText("");
               teacherIdField.setText("");
               statusLabel.setText("Course Allocated!");
          } else {
               statusLabel.setText(result);
          }
     }

     @FXML
     private void goBack(ActionEvent event) {
        AdminDashboardController.loadScreen("/com/gradehub/adminDashboard.fxml", event);
     }
}