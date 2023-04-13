module com.example.snakeandladderssp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeandladderssp to javafx.fxml;
    exports com.example.snakeandladderssp;
}