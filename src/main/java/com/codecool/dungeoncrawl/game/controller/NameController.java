package com.codecool.dungeoncrawl.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NameController {
    @FXML
    private TextField tfName;

    public static String userName;

    public static String getUserName() {
        if (userName == null){
             return userName = "Dzieki";
        }
            return userName;
    }

    @FXML
    void startGame(KeyEvent event) {
        userName = tfName.getText();
        if (event.getCode().equals(KeyCode.ENTER) && userName.equals("DAREK")) {
            ViewController.setConfView();
        } else if (event.getCode().equals(KeyCode.ENTER)) {
            ViewController.setGameView();
        }
    }

}