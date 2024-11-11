module com.example.navalbattleminiproject3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;
    requires jdk.unsupported.desktop;
    opens com.example.navalbattleminiproject3 to javafx.fxml;
    opens com.example.navalbattleminiproject3.controller to javafx.fxml;
    exports com.example.navalbattleminiproject3;
}