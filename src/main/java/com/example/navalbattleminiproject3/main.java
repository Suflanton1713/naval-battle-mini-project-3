package com.example.navalbattleminiproject3;
import com.example.navalbattleminiproject3.model.board.Board.BotBoard;
import com.example.navalbattleminiproject3.model.board.Board.PlayerBoard;
import com.example.navalbattleminiproject3.model.board.GameData.PlainTextGameData;
import com.example.navalbattleminiproject3.model.board.GameData.PlayerDataHandler;
import com.example.navalbattleminiproject3.model.board.GameData.SerializableGameData;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Arrays;

public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        PlayerDataHandler playerDataHandler = new PlayerDataHandler();

        BotBoard bot1 = new BotBoard();
        BotBoard bot2 = new BotBoard();
        BotBoard serializablebot1 = new BotBoard();
        BotBoard serializablebot2 = new BotBoard();

        playerDataHandler.createProfile("Bot1");
        playerDataHandler.createProfile("Bot2");
        playerDataHandler.updateProfile("Bot1",20);
        playerDataHandler.updateProfile("Bot2",30);

        playerDataHandler.updateProfile("Bot3",40);
        //Crea un nuevo perfil sin puntos, a pesar de que hayas escrito lo contrario.

        playerDataHandler.saveBoardProfile("Bot1", bot1);
        playerDataHandler.saveBoardProfile("Bot2", bot2);

        serializablebot1 = (BotBoard) playerDataHandler.loadBoardProfile("Bot1");

        System.out.println(bot1.showBoard(bot1.getBoard()));

        System.out.println(bot1.showBoard(serializablebot1.getBoard()));

        playerDataHandler.saveBoardProfile("Bot4", bot2);

        BotBoard bot4 = (BotBoard) playerDataHandler.loadBoardProfile("Bot4");

        bot2.randomShootInOtherBoard(bot4);
        bot2.randomShootInOtherBoard(bot4);
        bot2.randomShootInOtherBoard(bot4);
        bot2.randomShootInOtherBoard(bot4);
        bot2.randomShootInOtherBoard(bot4);
        bot2.randomShootInOtherBoard(bot4);
        bot2.randomShootInOtherBoard(bot4);

        System.out.println(bot1.showBoard(bot2.getBoard()));

        System.out.println(bot1.showBoard(bot4.getBoard()));




    }
}
