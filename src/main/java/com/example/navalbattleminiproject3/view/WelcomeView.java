package com.example.navalbattleminiproject3.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class WelcomeView extends Stage {
    private Parent root;
    private ImageView loadingImage;
    private Rectangle progressBar; // Barra de progreso
    private StackPane mainPane;
    private Image characters;


    public WelcomeView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/navalbattleminiproject3/fxml/welcome-view.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // Crear la imagen de carga
        loadingImage = new ImageView(new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/backgrounds/loading-image.jpg")));
        loadingImage.setFitWidth(1200);
        loadingImage.setFitHeight(670);

        // Crear la barra de progreso
        progressBar = new Rectangle(0, 20, Color.DODGERBLUE);
        progressBar.setHeight(20);  // Altura de la barra de progreso
        progressBar.setOpacity(0.8);  // Un poco de transparencia

        // Crear un VBox para colocar la barra de progreso debajo de la imagen de carga
        VBox loadingContainer = new VBox(loadingImage, progressBar);
        loadingContainer.setAlignment(Pos.CENTER); // Centrar los elementos

        // Crear un StackPane para superponer la imagen sobre la escena principal
        mainPane = new StackPane(root, loadingContainer);

        Scene scene = new Scene(mainPane, 1200, 670);
        scene.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styles/styleWelcome.css").toExternalForm());
        setScene(scene);
        setTitle("Naval Battle");
        getIcons().add(new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/favicon.png")));
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);

        // Iniciar la animación de carga
        iniciarPantallaDeCarga();
        show();
    }

    private void iniciarPantallaDeCarga() {
        // Crear una línea de tiempo para aumentar el ancho de la barra de progreso durante 10 segundos
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1), event -> {
                    // Aumentar el ancho de la barra de progreso
                    progressBar.setWidth(progressBar.getWidth() + 12);
                })
        );
        timeline.setCycleCount(1); // Aumenta la barra en 100 pasos (esto hará que dure 10 segundos)

        // Al finalizar, eliminar el StackPane y permitir interacción con los elementos
        timeline.setOnFinished(event -> {
            // Limpiar el StackPane y agregar solo el contenido original
            mainPane.getChildren().clear();
            mainPane.getChildren().add(root); // Añadir solo el contenido original de la vista
        });

        timeline.play();
    }

    private static class WelcomeViewHolder {
        private static WelcomeView INSTANCE;
    }

    public static WelcomeView getInstance() {
        WelcomeView.WelcomeViewHolder.INSTANCE =
                WelcomeView.WelcomeViewHolder.INSTANCE != null ?
                        WelcomeView.WelcomeViewHolder.INSTANCE : new WelcomeView();
        return WelcomeView.WelcomeViewHolder.INSTANCE;
    }

    public static void deleteInstance() {
        WelcomeViewHolder.INSTANCE.close();
        WelcomeViewHolder.INSTANCE = null;
    }
}
