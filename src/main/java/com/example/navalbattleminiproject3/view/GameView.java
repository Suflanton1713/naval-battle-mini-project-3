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

/**
 * The GameView class represents the graphical user interface (GUI) for the game window.
 * It extends Stage and sets up the main game window, including loading the FXML view and
 * handling the transition to the main game scene after a video intro.
 *
 * @version 1.0
 * @since 2024
 */
public class GameView extends Stage {
    private Parent root;
    private GameController gameController;
    private StackPane mainPane;

    /**
     * Constructor for the GameView class.
     * It loads the FXML layout, initializes the GameController, and sets up the scene.
     * A video intro is played initially, and after it ends, the main game view is shown.
     */
    public GameView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattleminiproject3/fxml/game-view.fxml"));
        try {
            root = loader.load();
            gameController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize the video intro view
        VideoView videoView = new VideoView();
        mainPane = new StackPane(videoView.getMainPane());
        StopVideo();

        // Set up the scene and window properties
        Scene scene = new Scene(mainPane, 1200, 670);
        scene.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styles/styleGame.css").toExternalForm());
        setScene(scene);
        setTitle("Sudoku");
        getIcons().add(new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/favicon.png")));
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        show();
    }

    /**
     * Gets the GameController instance associated with this view.
     *
     * @return The GameController instance.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Singleton pattern: Provides access to a single instance of GameView.
     *
     * @return The single instance of GameView.
     */
    private static class GameViewHolder {
        private static GameView INSTANCE;
    }

    /**
     * Static method to get the single instance of GameView.
     *
     * @return The single instance of GameView.
     */
    public static GameView getInstance() {
        GameViewHolder.INSTANCE = (GameViewHolder.INSTANCE != null ? GameViewHolder.INSTANCE : new GameView());
        return GameViewHolder.INSTANCE;
    }

    /**
     * Deletes the current instance of GameView.
     * Used to clean up the instance when the game ends or is closed.
     */
    public static void deleteInstance() {
        GameViewHolder.INSTANCE.close();
        GameViewHolder.INSTANCE = null;
    }

    /**
     * Starts a Timeline that plays the intro video for 16 seconds,
     * after which it clears the video and shows the main game view.
     */
    private void StopVideo() {
        // Configuring the Timeline to last 16 seconds
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(16000), event -> {
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(root); // Switches to the main game view after 16 seconds
                })
        );
        timeline.setCycleCount(1); // Only 1 cycle (plays once)
        timeline.play(); // Starts the timeline
    }

    /**
     * Sets the player character image in the game.
     *
     * @param character The Image representing the player character.
     */
    public void setPlayerCharacter(Image character) {
        gameController.setPlayerCharacter(character);
    }

    /**
     * Sets the bot character image in the game, selecting randomly from a list of available images.
     *
     * @param botImages An array of Image objects representing possible bot characters.
     */
    public void setBotCharacter(Image[] botImages) {
        int randomIndex = (int) (Math.random() * botImages.length);
        gameController.setBotCharacter(botImages[randomIndex]);
    }
}
