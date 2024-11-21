package com.example.navalbattleminiproject3.view;

import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * La clase `VideoView` se encarga de cargar y reproducir un video en la interfaz gráfica de la aplicación.
 * Utiliza los componentes `Media`, `MediaPlayer` y `MediaView` de JavaFX para manejar y mostrar el video en una vista.
 *
 * @version 1.0
 * @since 2024
 */
public class VideoView {
    private MediaView mediaView;
    private MediaPlayer mediaPlayer;
    private StackPane mainPane;

    /**
     * Constructor de la clase `VideoView`. Este constructor inicializa el reproductor de video,
     * lo carga y lo ajusta para que se ajuste al tamaño adecuado en la interfaz.
     */
    public VideoView() {
        // Cargar el archivo de video desde la ruta especificada
        Media media = new Media(getClass().getResource("/com/example/navalbattleminiproject3/loading-video.mp4").toExternalForm());

        // Crear el MediaPlayer con el archivo de video
        mediaPlayer = new MediaPlayer(media);

        // Crear la vista del video
        mediaView = new MediaView(mediaPlayer);

        // Ajustar el tamaño de la vista del video
        mediaView.setFitWidth(1200);
        mediaView.setFitHeight(670);

        // Crear un StackPane para contener el video
        mainPane = new StackPane();
        mainPane.getChildren().add(mediaView);

        // Reproducir el video cuando el MediaPlayer esté listo
        mediaPlayer.setOnReady(() -> mediaPlayer.play());
    }

    /**
     * Obtiene el `StackPane` que contiene la vista del video.
     *
     * @return El StackPane con la vista del video.
     */
    public StackPane getMainPane() {
        return mainPane;
    }
}
