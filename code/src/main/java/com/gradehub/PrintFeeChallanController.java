package com.gradehub;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.users.Student;

import javafx.event.ActionEvent;

public class PrintFeeChallanController {

    private String userId;
    Student s;

    public void setUserId(String userId) {
        this.userId = userId;
        s = new Student(userId);
        postInitialize();
    }
    @FXML
    private TextArea previewTextArea;

    private void postInitialize() {
        // Simulate fetching fee challan details
        String challanDetails = s.fetchChallanDetails();
        previewTextArea.setText(challanDetails);
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}

