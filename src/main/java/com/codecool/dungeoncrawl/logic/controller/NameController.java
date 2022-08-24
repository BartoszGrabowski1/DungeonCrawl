package com.codecool.dungeoncrawl.logic.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class NameController {

    @FXML
    private Label adviceLabel;

    @FXML
    private TextField tfName;

    @FXML
    private Label titleLabel;


    public static String userName;

    public static String getUserName() {
        return userName;
    }

    public static boolean startGame;

    @FXML
    void startGame(KeyEvent event) {
        userName = tfName.getText();
        if (event.getCode().equals(KeyCode.ENTER)) {
            startGame = true;
            Stage stageToClose = (Stage) tfName.getScene().getWindow();
            stageToClose.close();

        }

    }

}