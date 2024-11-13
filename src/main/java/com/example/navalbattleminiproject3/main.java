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

        playerBoard.spawnBoat(0,0,0,2);
        playerBoard.spawnBoat(1,0,0,2);
        playerBoard.spawnBoat(4,4,0,2);
        playerBoard.spawnBoat(5,4,0,2);

        System.out.println("--------------------------YOOOOOOOOOOOOOOOOOOOOOOPIIISSS-------------------");
        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        System.out.println("yapp-----");

        System.out.println(playerBoard.getAllBoatsUsed());
        playerBoard.shootInOtherBoard(botBoard.getBoard(),botBoard.getBoardWithBoats(),0,0);
        System.out.println("bote 00 " +playerBoard.getBoardWithBoats().get(0).get(0));
        System.out.println("bote 01 " +playerBoard.getBoardWithBoats().get(0).get(1));
        System.out.println("aaaaa Bote en 00 destruído? " +playerBoard.getBoardWithBoats().get(0).get(0).isBoatDestroyed());

        playerBoard.shootInOtherBoard(botBoard.getBoard(),botBoard.getBoardWithBoats(),3,0);
        playerBoard.shootInOtherBoard(botBoard.getBoard(),botBoard.getBoardWithBoats(),0,1);
        System.out.println("aaaaa Bote en 01 destruído? " +playerBoard.getBoardWithBoats().get(0).get(1).isBoatDestroyed());



        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        System.out.println(playerBoard.showBoard(playerBoard.getBoardWithBoats()));

        System.out.println("---------------------------------------------");

        botBoard.randomShootInOtherBoard(playerBoard.getBoard(),playerBoard.getBoardWithBoats());
        botBoard.randomShootInOtherBoard(playerBoard.getBoard(),playerBoard.getBoardWithBoats());
        botBoard.randomShootInOtherBoard(playerBoard.getBoard(),playerBoard.getBoardWithBoats());
        botBoard.randomShootInOtherBoard(playerBoard.getBoard(),playerBoard.getBoardWithBoats());

        System.out.println(playerBoard.showBoard(playerBoard.getBoard()));
        System.out.println(botBoard.showBoard(botBoard.getBoard()));
        
    }
}
