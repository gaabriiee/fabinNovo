module com.example.fabinnovo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens javafxcrud to javafx.graphics, javafx.fxml;
    exports javafxcrud;
}
