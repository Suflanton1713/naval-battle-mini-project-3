package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GameController {
    @FXML
    private GridPane positionGridPane;

    @FXML
    private GridPane principalGridPane;

    @FXML
    void handleClickExit(ActionEvent event) {
        GameView.deleteInstance();
        WelcomeView.getInstance();
    }

    @FXML
    public void initialize() {
    }

    public void createPositionTable(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button btn = new Button();
                btn.setPrefHeight(43);
                btn.setPrefWidth(43);
                positionGridPane.add(btn, j, i);
                handleButtonShip(btn);
            }
        }
    }

    private void handleButtonShip(Button button){
        button.setOnAction(event -> {
            button.setDisable(true);
        });
    }

    public void startPlay() {
        createPositionTable();
    }

}
