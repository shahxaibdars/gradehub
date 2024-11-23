package com.gradehub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Debug: Print resource URLs
            System.out.println("FXML URL: " + getClass().getResource("/com/gradehub/login.fxml"));
            System.out.println("CSS URL: " + getClass().getResource("/com/gradehub/css/styles.css"));
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/gradehub/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            
            // Add CSS file programmatically
            String css = getClass().getResource("/com/gradehub/css/styles.css").toExternalForm();
            scene.getStylesheets().add(css);
            
            stage.setTitle("Grade Hub - Login");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}