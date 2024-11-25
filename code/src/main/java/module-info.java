module com.gradehub {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.sql;

    opens com.gradehub to javafx.fxml;
    exports com.gradehub;
}
