package com.example.navalbattleminiproject3.view;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoView {
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private StackPane mainPane;

    public VideoView() {
        Media media = new Media(getClass().getResource("/com/example/navalbattleminiproject3/loading-video.mp4").toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1200);
        mediaView.setFitHeight(670);
        mainPane = new StackPane();
        mainPane.getChildren().add(mediaView);
        mediaPlayer.setOnReady(() -> mediaPlayer.play());
    }

    public StackPane getMainPane() {
        return mainPane;
    }
}
