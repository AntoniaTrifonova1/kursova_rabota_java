module com.example.kr_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kr_java to javafx.fxml;
    exports com.example.kr_java;
}