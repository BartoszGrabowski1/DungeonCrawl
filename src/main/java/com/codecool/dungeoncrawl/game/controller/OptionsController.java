package com.codecool.dungeoncrawl.game.controller;

import com.codecool.dungeoncrawl.Main;
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
    void loadGameView(MouseEvent event) {
        ViewController.setGameView();
    }

    @FXML
    void initialize() {
        newGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_new_game.png")));
        newGameBtn.setOnMouseEntered(t -> newGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_new_game_hover.png"))));
        newGameBtn.setOnMouseExited(t -> newGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_new_game.png"))));

        loadGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_load.png")));
        loadGameBtn.setOnMouseEntered(t -> loadGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_load_hover.png"))));
        loadGameBtn.setOnMouseExited(t -> loadGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_load.png"))));

        saveGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_save.png")));
        saveGameBtn.setOnMouseEntered(t -> saveGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_save_hover.png"))));
        saveGameBtn.setOnMouseExited(t -> saveGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_save.png"))));

        exitGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_exit.png")));
        exitGameBtn.setOnMouseEntered(t -> exitGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_exit_hover.png"))));
        exitGameBtn.setOnMouseExited(t -> exitGameBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_exit.png"))));

        returnBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_return.png")));
        returnBtn.setOnMouseEntered(t -> returnBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_return_hover.png"))));
        returnBtn.setOnMouseExited(t -> returnBtn.setImage(new Image(Main.class.getResourceAsStream("/com/codecool/dungeoncrawl/img/button_return.png"))));
    }

}
