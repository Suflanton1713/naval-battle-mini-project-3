package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.model.board.GameData.PlayerDataHandler;
import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;
/**
 * Controller class for the Welcome view.
 * Manages user interactions and initializes game settings.
 * @author Maria Juliana Saavedra, Libardo Alejandro Quintero, Juan David Rincon Lopez
 * @version 1.0
 */
public class WelcomeController {
    @FXML
    private ImageView imageHologram;
    @FXML
    private Button buttonSelectCharacter;
    @FXML
    private TextField txtProfileNewName;
    @FXML
    private ImageView imageCharacter;
    @FXML
    private Button buttonSide;
    private Image selectedCharacter;

    private String newNameProfile = "PlayerNew";

    private String loadNameProfile;

    private int loadingCounter;


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

    /**
     * Handles the exit button click event.
     * Closes the Welcome view and releases its resources.
     * @param event the {@code ActionEvent} triggered by the user.
     * @see WelcomeView#deleteInstance()
     * @version 1.0
     */
    @FXML
    void handleClickExit(ActionEvent event) {
        WelcomeView.deleteInstance();
    }
    /**
     * Handles the play button click event to start a new game.
     * Passes the selected character and initializes the game.
     * @see GameView#getInstance()
     * @see GameView#setPlayerCharacter(Image)
     * @see GameView#setBotCharacter(Image[])
     * @version 1.0
     */
    @FXML
    void handleClickPlay() {
        WelcomeView.deleteInstance();

        // Obtener la instancia de GameView
        GameView gameView = GameView.getInstance();

        // Pasar la imagen seleccionada del jugador
        gameView.setPlayerCharacter(characterImages[currentImageIndex]);

        // Seleccionar y establecer una imagen aleatoria para el bot
        gameView.setBotCharacter(characterImages);

        // Iniciar el juego
        gameView.getGameController().initialize(true, newNameProfile);
    }
    /**
     * Continues a previously saved game.
     * Passes the selected character and loads the saved player profile.
     * @see GameView#getInstance()
     * @see GameView#setPlayerCharacter(Image)
     * @version 1.0
     */
    public void continuePlay() {
        WelcomeView.deleteInstance();

        // Obtener la instancia de GameView
        GameView gameView = GameView.getInstance();

        // Pasar la imagen seleccionada del jugador
        gameView.setPlayerCharacter(characterImages[currentImageIndex]);

        // Seleccionar y establecer una imagen aleatoria para el bot
        gameView.setBotCharacter(characterImages);

        // Iniciar el juego
        gameView.getGameController().initialize(true, loadNameProfile);
    }
    /**
     * Handles the event when a new nickname is accepted.
     * Updates the profile name with the text entered by the user.
     * @param event the {@code ActionEvent} triggered by the user.
     * @see #newNameProfile
     * @version 1.0
     */
    @FXML
    void handleAceptNickname(ActionEvent event) {
        String text = txtProfileNewName.getText();
        newNameProfile = text;
    }
    /**
     * Handles the continue button click event.
     * Displays saved profiles and allows the user to load one or start with default settings.
     * @see PlayerDataHandler#getNicknamesData()
     * @see #continuePlay()
     * @version 1.0
     */
    @FXML
    void handleClickContinue() {
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
        box.setSpacing(50);
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

        vbox2.setAlignment(Pos.CENTER);
        ImageView imageView2 = new ImageView();
        TextField textField = new TextField();
        Button button2 = new Button();
        vbox2.setSpacing(20);
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
            loadNameProfile = texto;
            PlayerDataHandler playerDataHandler = new PlayerDataHandler();

            if ((!(Objects.equals(loadNameProfile, "")) && (playerDataHandler.getNicknamesData().contains(loadNameProfile))) || loadingCounter >= 1) {
                if (loadingCounter >= 1) {
                    loadNameProfile = "Player";
                }
                continuePlay();
                instructionStage.close();
            } else {
                loadingCounter++;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ingresa un usuario valido!");
                alert.setHeaderText("Ingresa un usuario valido");
                alert.setContentText("Digita tu usuario para poder cargar el juego. Asegurate de haberlo creado antes. Si vuelves a ingresar sin usuario se cargará la partida por defecto.");

                // Personalizar el estilo del Alert
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/com/example/navalbattleminiproject3/styles/styleWelcome.css").toExternalForm());
                dialogPane.getStyleClass().add("custom-alert");

                // Mostrar el Alert
                alert.showAndWait();
            }

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
    /**
     * Handles the credits button click event.
     * Displays credits information in a new modal window.
     * @param event the {@code ActionEvent} triggered by the user.
     * @see Stage
     * @see VBox
     * @version 1.0
     */
    @FXML
    void handleClickCredits(ActionEvent event) {
        Stage creditsStage = new Stage();

        // Configure the instruction stage as a modal window
        creditsStage.initModality(Modality.WINDOW_MODAL);
        creditsStage.initStyle(StageStyle.UNDECORATED);

        // Create title label for instructions
        Label titleLabel = new Label("JUEGO HECHO POR:");
        titleLabel.setStyle("-fx-text-fill: white;" +  // White color
                "-fx-font-size: 24px;" +   // Large font size
                "-fx-font-weight: bold;" + // Bold font
                "-fx-padding: 10px;");     // Padding around the text

// Create labels for credits information
        Label creditLabel1 = new Label("Desarrollado por: Libardo Alejandro Quintero, Maria Juliana Saavedra y Juan David Rincón");
        Label creditLabel2 = new Label("Códigos: 202342032");
        Label creditLabel3 = new Label("Correo: juan.rincon.lopez@correounivalle.edu.co");
        Label creditLabel4 = new Label("Materia: Programación Orientada a Eventos - 2024-2");

        // Set style for credit labels
        creditLabel1.setStyle("-fx-text-fill: white;");
        creditLabel2.setStyle("-fx-text-fill: white;");
        creditLabel3.setStyle("-fx-text-fill: white;");
        creditLabel4.setStyle("-fx-text-fill: white;");

        // Create close button for the credits stage
        Button closeButton = new Button("Cerrar");
        closeButton.setOnAction(e -> creditsStage.close());
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

        // Create layout for credits and the close button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, creditLabel1, creditLabel2, creditLabel3, creditLabel4, closeButton);
        layout.setStyle("-fx-background-image: url('" + getClass().getResource("/com/example/navalbattleminiproject3/images/backgrounds/fondo_continue.jpg") + "');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");
        layout.setAlignment(Pos.CENTER);

        // Create scene and set it to the instruction stage
        Scene scene = new Scene(layout, 500, 350);
        creditsStage.setScene(scene);

        // Show the instruction stage
        creditsStage.showAndWait();
    }
    /**
     * Displays the game instructions in a modal window.
     * Provides a step-by-step guide to playing the game, accompanied by illustrative images.
     * @param event the {@code ActionEvent} triggered by the user.
     * @see Stage
     * @see VBox
     * @version 1.0
     */
    @FXML
    void handleClickInstruction(ActionEvent event) {
        Stage instructionStage = new Stage();

        // Configure the instruction stage as a modal window
        instructionStage.initModality(Modality.WINDOW_MODAL);
        instructionStage.initStyle(StageStyle.UNDECORATED);

        // Create title label for instructions
        Label titleLabel = new Label("¿Cómo se juega?");
        titleLabel.setStyle("-fx-text-fill: white;" +  // White color
                "-fx-font-size: 24px;" +   // Large font size
                "-fx-font-weight: bold;" + // Bold font
                "-fx-padding: 10px;");     // Padding around the text
        HBox box = new HBox();
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);
        VBox vbox1 = new VBox();
        vbox1.setAlignment(Pos.CENTER);

        ImageView imageView1 = new ImageView();
        Label label1 = new Label();
        label1.setText("1. ¡Debes ingresar tu nombre para iniciar!");
        label1.setStyle("-fx-text-fill: white;");
        Image profileImage1 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/paso1.png"));
        imageView1.setImage(profileImage1);
        imageView1.setFitHeight(150);
        imageView1.setFitWidth(150);
        imageView1.setPreserveRatio(true);
        vbox1.getChildren().addAll(imageView1, label1);

        VBox vbox2 = new VBox();

        vbox2.setAlignment(Pos.CENTER);
        ImageView imageView2 = new ImageView();
        Label label2 = new Label();
        label2.setText("2. Para iniciar la partida, puedes \ncomenzar con una partida o continuarla");
        label2.setStyle("-fx-text-fill: white;");
        Image profileImage2 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/paso2.png"));
        imageView2.setImage(profileImage2);
        imageView2.setFitHeight(150);
        imageView2.setFitWidth(150);
        imageView2.setPreserveRatio(true);
        vbox2.getChildren().addAll(imageView2, label2);


        VBox vbox3 = new VBox();
        vbox3.setAlignment(Pos.CENTER);
        ImageView imageView3 = new ImageView();
        Label label3 = new Label();
        label3.setText("3. Debes ingresar todos los \nbarcos dentro de tu tablero.\nRotalo con la letra R");
        label3.setStyle("-fx-text-fill: white;");
        Image profileImage3 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/paso3.png"));
        imageView3.setImage(profileImage3);
        imageView3.setFitHeight(150);
        imageView3.setFitWidth(150);
        imageView3.setPreserveRatio(true);
        vbox3.getChildren().addAll(imageView3, label3);
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

        HBox box1 = new HBox();
        box1.setSpacing(40);
        box1.setAlignment(Pos.CENTER);

        VBox vbox4 = new VBox();
        vbox4.setAlignment(Pos.CENTER);

        ImageView imageView4 = new ImageView();
        Label label4 = new Label();
        label4.setText("4. Puedes iniciar el juego una vez\n todas tus naves esten listas");
        label4.setStyle("-fx-text-fill: white;");
        Image profileImage4 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/paso4.png"));
        imageView4.setImage(profileImage4);
        imageView4.setFitHeight(150);
        imageView4.setFitWidth(150);
        imageView4.setPreserveRatio(true);
        vbox4.getChildren().addAll(imageView4, label4);

        VBox vbox5 = new VBox();

        vbox5.setAlignment(Pos.CENTER);
        ImageView imageView5 = new ImageView();
        Label label5 = new Label();
        label5.setText("5. Clickeando en el segundo tablero\n puedes dispararle a las naves enemigas");
        label5.setStyle("-fx-text-fill: white;");
        Image profileImage5 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/paso5.png"));
        imageView5.setImage(profileImage5);
        imageView5.setFitHeight(150);
        imageView5.setFitWidth(150);
        imageView5.setPreserveRatio(true);
        vbox5.getChildren().addAll(imageView5, label5);

        VBox vbox6 = new VBox();
        vbox6.setAlignment(Pos.CENTER);
        ImageView imageView6 = new ImageView();
        Label label6 = new Label();
        label6.setText("6. Ganas cuando alguno de los\njugadores de a todas las naves");
        label6.setStyle("-fx-text-fill: white;");
        Image profileImage6 = new Image(getClass().getResourceAsStream("/com/example/navalbattleminiproject3/images/characters/paso6.png"));
        imageView6.setImage(profileImage6);
        imageView6.setFitHeight(150);
        imageView6.setFitWidth(150);
        imageView6.setPreserveRatio(true);
        vbox6.getChildren().addAll(imageView6, label6);
        box1.getChildren().addAll(vbox4, vbox5, vbox6);


        // Create layout for instructions and the close button
        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, box, box1, closeButton);
        layout.setStyle("-fx-background-image: url('" + getClass().getResource("/com/example/navalbattleminiproject3/images/backgrounds/fondo_continue.jpg") + "');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;");

        layout.setAlignment(Pos.CENTER);

        // Create scene and set it to the instruction stage
        Scene scene = new Scene(layout, 700, 550);
        instructionStage.setScene(scene);

        // Show the instruction stage
        instructionStage.showAndWait();
    }



    /**
     * Initializes the Welcome view with the default character and hologram images.
     * Sets the initial state of the UI elements.
     * @see #characterImages
     * @see #hologramImages
     * @version 1.0
     */
    @FXML
    void initialize() {
        imageCharacter.setImage(characterImages[currentImageIndex]);
        imageHologram.setImage(hologramImages[currentImageIndex]);
    }
    /**
     * Handles the event of selecting a new character.
     * Cycles through the available character and hologram images, updating the displayed selection.
     * Saves the currently selected character for further use.
     * @param event the {@code ActionEvent} triggered by the user.
     * @see #selectedCharacter
     * @see #characterImages
     * @see #hologramImages
     * @version 1.0
     */
    @FXML
    void handleSelectCharacter(ActionEvent event) {
        currentImageIndex = (currentImageIndex + 1) % characterImages.length;
        imageCharacter.setImage(characterImages[currentImageIndex]);
        imageHologram.setImage(hologramImages[currentImageIndex]);

        // Guardar la selección actual
        selectedCharacter = characterImages[currentImageIndex];
    }
    /**
     * Retrieves the character image currently selected by the user.
     * @return the {@code Image} representing the selected character.
     * @version 1.0
     */
    public Image getSelectedCharacter() {
        return selectedCharacter;
    }




}
