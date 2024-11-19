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
        Scene scene = new Scene(mainPane, 1300, 670);
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
        // Configura un Timeline para detener el video después de 1.6 segundos
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1600), event -> {
                    // Aquí se puede realizar cualquier acción, si es necesario.
                })
        );
        timeline.setCycleCount(1); // Solo un ciclo
        timeline.setOnFinished(event -> {
            // Detener el video y mostrar la vista principal
            mainPane.getChildren().clear();
            mainPane.getChildren().add(root);
        });
        timeline.play(); // Asegúrate de llamar al play para que inicie la animación
    }

}
