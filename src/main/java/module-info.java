module com.example.tictac {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictac to javafx.fxml;
    exports com.example.tictac;
}