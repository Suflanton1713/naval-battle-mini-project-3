package com.example.navalbattleminiproject3.controller;

import com.example.navalbattleminiproject3.view.GameView;
import com.example.navalbattleminiproject3.view.WelcomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomeController {

    @FXML
    void handleClickExit(ActionEvent event) {
        WelcomeView.deleteInstance();
    }

    @FXML
    void handleClickPlay(ActionEvent event) {
        WelcomeView.deleteInstance();
        GameView.getInstance().getGameController().startPlay();

    }

}
