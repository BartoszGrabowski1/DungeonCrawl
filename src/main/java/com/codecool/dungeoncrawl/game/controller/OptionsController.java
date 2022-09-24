package com.codecool.dungeoncrawl.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OptionsController {

    @FXML
    void loadGameView(ActionEvent event) {
        ViewController.setGameView();
    }

}
