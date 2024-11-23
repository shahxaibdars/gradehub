module com.gradehub {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gradehub to javafx.fxml;
    exports com.gradehub;
}
