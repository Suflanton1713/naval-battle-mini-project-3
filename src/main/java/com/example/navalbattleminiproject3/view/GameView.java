package com.example.navalbattleminiproject3.view;

import com.example.navalbattleminiproject3.controller.GameController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameView extends Stage {
    private Parent root;
    private GameController gameController;

    public GameView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattleminiproject3/game-view.fxml"));
        try{
            root = loader.load();
            gameController = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(root,1200,670);
        scene.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styleGame.css").toExternalForm());
        setScene(scene);
        setTitle("Sudoku");
        getIcons().add(new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/favicon.png")));
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        show();
    }

    public GameController getGameController() {
        return gameController;
    }

    private static class GameViewHolder {
        private static GameView INSTANCE;
    }

    public static GameView getInstance() {
        GameViewHolder.INSTANCE = (GameViewHolder.INSTANCE != null ? GameViewHolder.INSTANCE : new GameView());
        return GameViewHolder.INSTANCE;
    }

    public static void deleteInstance() {
        GameViewHolder.INSTANCE.close();
        GameViewHolder.INSTANCE = null;
    }


}
