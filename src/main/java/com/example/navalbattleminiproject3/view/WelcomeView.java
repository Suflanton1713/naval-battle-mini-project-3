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

/**
 * La clase `WelcomeView` se encarga de mostrar la vista de bienvenida al inicio del juego.
 * Incluye una animación de carga con una barra de progreso y una imagen de fondo.
 * Después de completar la animación, la vista de bienvenida se reemplaza por la vista principal del juego.
 *
 * @version 1.0
 * @since 2024
 */
public class WelcomeView extends Stage {
    private Parent root;
    private ImageView loadingImage;
    private Rectangle progressBar;
    private StackPane mainPane;

    /**
     * Constructor de la clase `WelcomeView`. Inicializa los elementos visuales de la vista de bienvenida,
     * como la imagen de carga, la barra de progreso y la escena del juego.
     */
    public WelcomeView() {
        // Cargar el archivo FXML que define la vista de bienvenida
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

        // Añadir la imagen y la barra de progreso a un VBox
        VBox loadingContainer = new VBox(loadingImage, progressBar);
        loadingContainer.setAlignment(Pos.CENTER);

        // Crear el StackPane que contendrá la imagen de carga y la barra de progreso
        mainPane = new StackPane(loadingContainer);

        // Configurar la escena y la ventana
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

    /**
     * Inicia la animación de la pantalla de carga, mostrando una barra de progreso que aumenta su tamaño.
     * Una vez finalizada la animación, la vista de bienvenida se reemplaza por la vista principal.
     */
    private void iniciarPantallaDeCarga() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(30), event -> {
                    // Aumentar el ancho de la barra de progreso
                    progressBar.setWidth(progressBar.getWidth() + 12);
                })
        );
        timeline.setCycleCount(30); // Realiza 100 ciclos de la animación

        // Al finalizar la animación, se reemplaza el StackPane de carga por la vista principal
        timeline.setOnFinished(event -> {
            mainPane.getChildren().clear();
            mainPane.getChildren().add(root);
        });

        timeline.play(); // Inicia la animación
    }

    /**
     * Clase interna que contiene una instancia estática de `WelcomeView`.
     * Utilizada para implementar el patrón Singleton y asegurar que solo haya una instancia de `WelcomeView`.
     */
    private static class WelcomeViewHolder {
        private static WelcomeView INSTANCE;
    }

    /**
     * Obtiene la instancia única de `WelcomeView`. Si aún no existe, se crea una nueva.
     *
     * @return La instancia única de `WelcomeView`.
     */
    public static WelcomeView getInstance() {
        WelcomeView.WelcomeViewHolder.INSTANCE =
                WelcomeView.WelcomeViewHolder.INSTANCE != null ?
                        WelcomeView.WelcomeViewHolder.INSTANCE : new WelcomeView();
        return WelcomeView.WelcomeViewHolder.INSTANCE;
    }

    /**
     * Elimina la instancia actual de `WelcomeView` y cierra la ventana.
     */
    public static void deleteInstance() {
        WelcomeViewHolder.INSTANCE.close();
        WelcomeViewHolder.INSTANCE = null;
    }
}
