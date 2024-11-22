package com.example.navalbattleminiproject3;
import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        WelcomeView.getInstance();

        BotBoard botBoard = new BotBoard();
        PlayerBoard playerBoard = new PlayerBoard();

    }
}
