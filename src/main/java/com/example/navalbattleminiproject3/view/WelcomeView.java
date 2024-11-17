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
    private Rectangle progressBar;
    private StackPane mainPane;

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
        progressBar.setHeight(20);
        progressBar.setOpacity(0.8);

        // Añadir imagen y barra de progreso a un VBox
        VBox loadingContainer = new VBox(loadingImage, progressBar);
        loadingContainer.setAlignment(Pos.CENTER);

        // Crear el StackPane para el video
        mainPane = new StackPane(loadingContainer);

        // Configurar la escena y ventana
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
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    // Aumentar el ancho de la barra de progreso
                    progressBar.setWidth(progressBar.getWidth() + 12);
                })
        );
        timeline.setCycleCount(100); // Cambiado a 100 ciclos

        // Al finalizar, eliminar el StackPane y permitir interacción con los elementos
        timeline.setOnFinished(event -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(root);
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
