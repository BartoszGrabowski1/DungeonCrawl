package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OptionsController {

    @FXML
    private ImageView newGameBtn;

    @FXML
    private ImageView loadGameBtn;

    @FXML
    private ImageView saveGameBtn;

    @FXML
    private ImageView exitGameBtn;

    @FXML
    private ImageView returnBtn;

    @FXML
    void resumeGame(MouseEvent event) {
        ViewController.setGameView();
    }

    @FXML
    void exitApplication(MouseEvent event) {
        Platform.exit();
    }

    private void initButton(ImageView button, String buttonImage, String buttonImageHover) {
        button.setImage(new Image(Main.class.getResourceAsStream(buttonImage)));
        button.setOnMouseEntered(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImageHover))));
        button.setOnMouseExited(t -> button.setImage(new Image(Main.class.getResourceAsStream(buttonImage))));
    }

    private void initButtons() {
        initButton(newGameBtn, "/com/codecool/dungeoncrawl/img/button_new_game.png", "/com/codecool/dungeoncrawl/img/button_new_game_hover.png");
        initButton(loadGameBtn, "/com/codecool/dungeoncrawl/img/button_load.png", "/com/codecool/dungeoncrawl/img/button_load_hover.png");
        initButton(saveGameBtn, "/com/codecool/dungeoncrawl/img/button_save.png", "/com/codecool/dungeoncrawl/img/button_save_hover.png");
        initButton(exitGameBtn, "/com/codecool/dungeoncrawl/img/button_exit.png", "/com/codecool/dungeoncrawl/img/button_exit_hover.png");
        initButton(returnBtn, "/com/codecool/dungeoncrawl/img/button_return.png", "/com/codecool/dungeoncrawl/img/button_return_hover.png");
    }

    @FXML
    void initialize() {
        initButtons();
    }

}
