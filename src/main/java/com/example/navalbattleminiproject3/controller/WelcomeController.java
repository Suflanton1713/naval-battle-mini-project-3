package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WelcomeController {
    @FXML
    private ImageView imageHologram;
    @FXML
    private Button buttonSelectCharacter;
    @FXML
    private ImageView imageCharacter;
    @FXML
    private Button buttonSide;
    private Image selectedCharacter;


    private int currentImageIndex = 0;
    private final Image[] characterImages = {
            new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/yoda.jpg")),
            new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/anakin1.png")),
            new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/vader1.png"))
    };

    private final Image[] hologramImages = {
            new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/yoda_holografico.png")),
            new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/anakin.png")),
            new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/vader.png"))
    };



    @FXML
    void handleClickExit(ActionEvent event) {
        WelcomeView.deleteInstance();
    }

    @FXML
    void handleClickPlay(ActionEvent event) {
        WelcomeView.deleteInstance();

        // Obtener la instancia de GameView
        GameView gameView = GameView.getInstance();

        // Pasar la imagen seleccionada del jugador
        gameView.setPlayerCharacter(characterImages[currentImageIndex]);

        // Seleccionar y establecer una imagen aleatoria para el bot
        gameView.setBotCharacter(characterImages);

        // Iniciar el juego
        gameView.getGameController().startPlay();
    }

    @FXML
    void handleClickContinue(ActionEvent event) {
        Stage instructionStage = new Stage();

        // Configure the instruction stage as a modal window
        instructionStage.initModality(Modality.WINDOW_MODAL);
        instructionStage.initStyle(StageStyle.UNDECORATED);

        // Create title label for instructions
        Label titleLabel = new Label("PERFILES GUARDADOS");
        titleLabel.setStyle("-fx-text-fill: white;" +  // White color
                "-fx-font-size: 24px;" +   // Large font size
                "-fx-font-weight: bold;" + // Bold font
                "-fx-padding: 10px;");     // Padding around the text

        HBox box = new HBox();
        box.setSpacing(20);
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);

        ImageView imageView1 = new ImageView();
        Image profileImage1 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/yoda.jpg"));
        imageView1.setImage(profileImage1);
        imageView1.setFitHeight(150);
        imageView1.setFitWidth(150);
        imageView1.setPreserveRatio(true);
        vbox1.getChildren().addAll(imageView1);

        VBox vbox2 = new VBox();
        vbox2.setSpacing(20);
        vbox2.setAlignment(Pos.CENTER);
        ImageView imageView2 = new ImageView();
        TextField textField = new TextField();
        Button button2 = new Button();
        button2.setText("Cargar el perfil");
        Image profileImage2 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/anakin1.png"));
        imageView2.setImage(profileImage2);
        imageView2.setFitHeight(150);
        imageView2.setFitWidth(150);
        imageView2.setPreserveRatio(true);
        vbox2.getChildren().addAll(imageView2, textField, button2);

        button2.setOnAction(e -> {
            // When the button is clicked, get the text from the TextField and print it
            String texto = textField.getText();
            System.out.println(texto);  // Prints the text to the console
        });

        VBox vbox3 = new VBox();
        vbox3.setAlignment(Pos.CENTER);
        ImageView imageView3 = new ImageView();
        Image profileImage3 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/vader1.png"));
        imageView3.setImage(profileImage3);
        imageView3.setFitHeight(150);
        imageView3.setFitWidth(150);
        imageView3.setPreserveRatio(true);
        vbox3.getChildren().addAll(imageView3);
        box.getChildren().addAll(vbox1, vbox2, vbox3);

        // Create close button for the instruction stage
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> instructionStage.close());
        closeButton.setStyle(
                "-fx-background-color: #55ff00;" +         // Red background
                        "-fx-text-fill: white;" +              // White text
                        "-fx-font-weight: bold;" +             // Bold font
                        "-fx-font-size: 14px;" +               // Font size
                        "-fx-border-color: white;" +           // White border
                        "-fx-border-width: 2px;" +             // Border thickness
                        "-fx-border-radius: 10px;" +           // Rounded borders
                        "-fx-background-radius: 10px;" +       // Rounded background
                        "-fx-padding: 5px 10px;"               // Internal padding
        );

        // Create layout for instructions and the close button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, box, closeButton);
        layout.setStyle("-fx-background-image: url('" + getClass().getResource("/com/example/navalbattleminiproject3/images/backgrounds/fondo_continue.jpg") + "');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");

        layout.setAlignment(Pos.CENTER);

        // Create scene and set it to the instruction stage
        Scene scene = new Scene(layout, 500, 350);
        instructionStage.setScene(scene);

        // Show the instruction stage
        instructionStage.showAndWait();
    }


    @FXML
    void initialize() {
        imageCharacter.setImage(characterImages[currentImageIndex]);
        imageHologram.setImage(hologramImages[currentImageIndex]);
    }

    @FXML
    void handleSelectCharacter(ActionEvent event) {
        currentImageIndex = (currentImageIndex + 1) % characterImages.length;
        imageCharacter.setImage(characterImages[currentImageIndex]);
        imageHologram.setImage(hologramImages[currentImageIndex]);

        // Guardar la selección actual
        selectedCharacter = characterImages[currentImageIndex];
    }

    public Image getSelectedCharacter() {
        return selectedCharacter;
    }




}
