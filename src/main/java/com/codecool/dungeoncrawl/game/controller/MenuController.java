package com.codecool.dungeoncrawl.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private Label tfName;

    @FXML
    void exit(ActionEvent event) {
        ViewController.closeView();
    }

    @FXML
    void loadGame(ActionEvent event) {

    }

    @FXML
    void startGame() {
        ViewController.setNameSelectView();
    }

}
