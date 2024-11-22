/**
 * Module configuration for the Naval Battle project.
 * This file declares the required modules and opens necessary packages for JavaFX and other dependencies.
 *
 * @module com.example.navalbattleminiproject3
 * @version 1.0
 * @since 2024
 */
module com.example.navalbattleminiproject3 {
    // Required modules for JavaFX functionality
    requires javafx.controls;
    requires javafx.fxml;

    // Required module for management operations
    requires java.management;

    // Required module for desktop-related operations
    requires jdk.unsupported.desktop;

    // Required module for JavaFX media features
    requires javafx.media;

    // Opens packages to allow JavaFX reflection access
    opens com.example.navalbattleminiproject3 to javafx.fxml;
    opens com.example.navalbattleminiproject3.controller to javafx.fxml;

    // Exports the base package to make it accessible to other modules
    exports com.example.navalbattleminiproject3;
}
