module com.example.navalbattleminiproject3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.navalbattleminiproject3 to javafx.fxml;
    exports com.example.navalbattleminiproject3;
}