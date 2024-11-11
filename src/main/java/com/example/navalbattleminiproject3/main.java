package com.example.navalbattleminiproject3;
import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BotBoard botBoard = new BotBoard();

        System.out.println(botBoard.showBoard(botBoard.getBoard()));
        
    }
}
