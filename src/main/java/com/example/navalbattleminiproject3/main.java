package com.example.navalbattleminiproject3;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        WelcomeView.getInstance();
    }
}
