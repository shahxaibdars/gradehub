package com.gradehub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class PrintFeeChallanController {

    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @FXML
    private ChoiceBox<String> challanChoiceBox;

    @FXML
    private TextArea previewTextArea;

    @FXML
    public void initialize() {
        // Populate the ChoiceBox with sample fee challans (replace with database queries)
        ObservableList<String> challans = FXCollections.observableArrayList(
                "Fall 2024 - Tuition Fee",
                "Spring 2024 - Hostel Fee",
                "Summer 2024 - Miscellaneous Charges"
        );
        challanChoiceBox.setItems(challans);
    }

    @FXML
    private void generatePreview() {
        String selectedChallan = challanChoiceBox.getValue();

        if (selectedChallan == null) {
            previewTextArea.setText("Please select a fee challan to preview.");
            return;
        }

        // Simulate fetching fee challan details
        String challanDetails = fetchChallanDetails(selectedChallan);
        previewTextArea.setText(challanDetails);
    }

    private String fetchChallanDetails(String challan) {
        // Simulated data fetching (replace with actual database logic)
        System.out.println("Fetching details for: " + challan);
        return "Fee Challan Details for: " + challan + "\n\nAmount: $500\nDue Date: 30-Nov-2024\nPayable at: ABC Bank";
    }

    @FXML
    private void handlePrint() {
        String previewContent = previewTextArea.getText();

        if (previewContent.isEmpty() || previewContent.startsWith("Please select")) {
            previewTextArea.setText("Generate a preview first before printing.");
            return;
        }

        // Simulate printing functionality
        System.out.println("Printing challan...");
        previewTextArea.setText(previewContent + "\n\n[Printed Successfully]");
    }

    @FXML
    private void handleDownload() {
        String previewContent = previewTextArea.getText();

        if (previewContent.isEmpty() || previewContent.startsWith("Please select")) {
            previewTextArea.setText("Generate a preview first before downloading.");
            return;
        }

        // Simulate downloading functionality
        System.out.println("Downloading challan...");
        previewTextArea.setText(previewContent + "\n\n[Downloaded Successfully]");
    }

    @FXML
    private void goBack(ActionEvent event) {
        // Use the static loadScreen method from StudentDashboardController
        StudentDashboardController.loadScreen("/com/gradehub/studentDashboard.fxml", event, userId);
    }
}

