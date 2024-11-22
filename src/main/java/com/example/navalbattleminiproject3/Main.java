package com.example.navalbattleminiproject3;

import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for the `Naval Battle` application.
 * This class is responsible for launching the application and displaying the welcome view.
 *
 * @version 1.0
 * @since 2024
 */
public class Main extends Application {

    /**
     * The main method of the application. It calls the `launch` method to start the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The `start` method is the entry point for the JavaFX application.
     * It initializes and displays the welcome view.
     *
     * @param primaryStage The primary stage for the JavaFX application.
     */
    @Override
    public void start(Stage primaryStage) {
        WelcomeView.getInstance();
    }
}
