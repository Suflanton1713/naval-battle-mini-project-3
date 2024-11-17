package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeController {
    @FXML
    private ImageView imageHologram;
    @FXML
    private Button buttonSelectCharacter;
    @FXML
    private ImageView imageCharacter;
    @FXML
    private Button buttonSide;

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
        GameView.getInstance().getGameController().startPlay();

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
    }


}
