package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    void loadGame(MouseEvent event) {

    }

    @FXML
    void startGame(MouseEvent event) {
        ViewController.setNameSelectView();
    }

    @FXML
    void initialize() {
        startGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_start.png")));
        startGame.setOnMouseEntered(t -> startGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_start_hover.png"))));
        startGame.setOnMouseExited(t -> startGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_start.png"))));

        loadGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_load.png")));
        loadGame.setOnMouseEntered(t -> loadGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_load_hover.png"))));
        loadGame.setOnMouseExited(t -> loadGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_load.png"))));

        exitGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_exit.png")));
        exitGame.setOnMouseEntered(t -> exitGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_exit_hover.png"))));
        exitGame.setOnMouseExited(t -> exitGame.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_exit.png"))));
    }

}
