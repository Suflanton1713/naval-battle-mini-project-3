package com.example.navalbattleminiproject3.view;

import com.example.navalbattleminiproject3.controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class GameView extends Stage {
    private Parent root;
    private GameController gameController;
    private StackPane mainPane;

    public GameView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattleminiproject3/fxml/game-view.fxml"));
        try{
            root = loader.load();
            gameController = loader.getController();
        } catch (IOException e){
            e.printStackTrace();
        }

        VideoView videoView = new VideoView();
        mainPane = new StackPane(videoView.getMainPane());
        StopVideo();

        // Configurar la escena y ventana
        Scene scene = new Scene(mainPane, 1200, 670);
        scene.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styles/styleGame.css").toExternalForm());
        setScene(scene);
        setTitle("Sudoku");
        getIcons().add(new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/favicon.png")));
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

    private void StopVideo() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(root);
                })
        );
        timeline.setCycleCount(1); // Solo 1 ciclo
        timeline.play(); // Inicia el timeline
    }

    public void setPlayerCharacter(Image character) {
        gameController.setPlayerCharacter(character);
    }

    public void setBotCharacter(Image[] botImages) {
        int randomIndex = (int) (Math.random() * botImages.length);
        gameController.setBotCharacter(botImages[randomIndex]);
    }
}
