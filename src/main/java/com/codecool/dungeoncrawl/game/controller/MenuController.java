package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class MenuController {

    @FXML
    private ImageView exitGame;

    @FXML
    private ImageView loadGame;

    @FXML
    private ImageView startGame;

    @FXML
    void exit(MouseEvent event) {
        ViewController.closeView();
    }

    @FXML
    void loadGame(MouseEvent event) throws SQLException {
        ViewController.setLoadGameView();
    }

    @FXML
    void startGame(MouseEvent event) {
        ViewController.setNameSelectView();
    }

    private void initButton(ImageView button, String buttonImage, String buttonImageHover) {
        button.setImage(new Image(Main.class.getResourceAsStream(buttonImage)));
        button.setOnMouseEntered(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImageHover))));
        button.setOnMouseExited(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImage))));
    }

    private void initButtons() {
        initButton(startGame, "/com/codecool/dungeoncrawl/img/button_start.png", "/com/codecool/dungeoncrawl/img/button_start_hover.png");
        initButton(loadGame, "/com/codecool/dungeoncrawl/img/button_load.png", "/com/codecool/dungeoncrawl/img/button_load_hover.png");
        initButton(exitGame, "/com/codecool/dungeoncrawl/img/button_exit.png", "/com/codecool/dungeoncrawl/img/button_exit_hover.png");
    }

    @FXML
    void initialize() {
        initButtons();
    }

}
